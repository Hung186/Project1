package com.firstapp.duan1.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.firstapp.duan1.R;
import com.firstapp.duan1.firebase.controller.ControllerBase;
import com.firstapp.duan1.firebase.controller.ControllerNguoiDung;
import com.firstapp.duan1.model.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class AuthFirebaseActivity extends AppCompatActivity {
    public static Resources resources;

    private boolean registerView = false;
    private final int REQ_CODE = 72;
    private final ControllerNguoiDung controllerNguoiDung = new ControllerNguoiDung();
    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private GoogleSignInClient googleSignInClient;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // for use by Firebase
        resources = getResources();

        // check if user is already logged in
        // note: firebase and facebook keeps account state even after app exit so there's no need
        // for a "Remember me" checkbox, user can manually logout when they need to
        // with FirebaseAuth.getInstance().signOut() or LoginManager.getInstance().logOut()
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (firebaseAuth.getCurrentUser() != null || // google auth status
                (Profile.getCurrentProfile() != null // facebook auth status
                        && accessToken != null
                        && !accessToken.isExpired())
        ) {
            startProfileActivity(getSharedPreferences("ACCOUNT", Context.MODE_PRIVATE).getString("email", null));
            finish();
        }

        // proceed with the activity
        setContentView(R.layout.activity_dang_nhap);
        Window window = this.getWindow();
        window.setNavigationBarColor(ContextCompat.getColor(this, R.color.white));
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.white));

        // switching between login and signup btn
        // because why create another activity that's basically a clone when you can
        // modify the current one
        TextView tvTitle = findViewById(R.id.auth_tv_title);
        TextView tvSwitch = findViewById(R.id.auth_tv_switch);
        AppCompatButton btnSubmit = findViewById(R.id.auth_btn_submit);
        tvSwitch.setOnClickListener(v -> {
            registerView = !registerView;
            if (registerView) {
                tvTitle.setText(getString(R.string.auth_title_sign_up));
                tvSwitch.setText(getString(R.string.auth_switch_sign_up));
                btnSubmit.setText(getString(R.string.auth_btn_sign_up));
            } else {
                tvTitle.setText(getString(R.string.auth_title_sign_in));
                tvSwitch.setText(getString(R.string.auth_switch_sign_in));
                btnSubmit.setText(getString(R.string.auth_btn_sign_in));
            }
        });

        // ============================ GOOGLE AUTHENTICATION ============================
        // firebase related
        googleSignInClient = GoogleSignIn.getClient(
                this,
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.firebase_web_client_id))
                        .requestEmail()
                        .requestProfile()
                        .build()
        );

        // login via google button
        findViewById(R.id.auth_btn_google)
                .setOnClickListener(v -> startActivityForResult(googleSignInClient.getSignInIntent(), REQ_CODE));

        // login via email and password
        EditText etEmail = findViewById(R.id.auth_et_email);
        EditText etPass = findViewById(R.id.auth_et_password);
        findViewById(R.id.auth_btn_submit)
                .setOnClickListener(v -> {
                    boolean hasError = false;

                    if (etEmail.getText().toString().isEmpty()) {
                        etEmail.setError("Email must not be empty");
                        hasError = true;
                    }

                    if (etPass.getText().toString().isEmpty()) {
                        etPass.setError("Password must not be empty");
                        hasError = true;
                    }

                    if (!hasError) {
                        if (registerView)
                            // create user with email and password
                            FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                                    etEmail.getText().toString(),
                                    etPass.getText().toString()
                            ).addOnCompleteListener(this::googleCompleteListener);
                        else
                            // sigin with email and password
                            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                                    etEmail.getText().toString(),
                                    etPass.getText().toString()
                            ).addOnCompleteListener(this::googleCompleteListener);
                    }
                });

        // ============================ FACEBOOK AUTHENTICATION ============================
        // facebook CallbackManager Factory
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                facebookCompleteListener(loginResult);
            }

            @Override
            public void onCancel() {
                Toast.makeText(AuthActivity.this, "User cancelled action", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(@NonNull FacebookException e) {
                Toast.makeText(AuthActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                e.printStackTrace();
            }
        });

        // login via facebook button
        findViewById(R.id.auth_btn_facebook)
                .setOnClickListener(v -> {
                    LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));
                });
    }

    // Listen for activity result from Google auth popup and Facebook auth screen
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // facebook auth
        callbackManager.onActivityResult(requestCode, resultCode, data);

        // google auth
        if (requestCode != REQ_CODE) return;

        try {
            GoogleSignInAccount googleSignInAccount = GoogleSignIn.getSignedInAccountFromIntent(data).getResult(ApiException.class);
            if (googleSignInAccount == null) {
                Toast.makeText(this, "Received Null while getting account", Toast.LENGTH_SHORT).show();
                return;
            }

            firebaseAuth
                    .signInWithCredential(GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null))
                    .addOnCompleteListener(this::googleCompleteListener);
        } catch (ApiException apiException) {
            Toast.makeText(AuthActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

            apiException.printStackTrace();
        }
    }

    // Google auth complete listener
    private void googleCompleteListener(Task<AuthResult> task) {
        if (task.isSuccessful()) {
            controllerNguoiDung.getAllAsync(
                    new ControllerBase.SuccessListener() {
                        @Override
                        public void run(DataSnapshot dataSnapshot) {
                            List<User> userList = new ArrayList<>();

                            // null snapshot
                            if (dataSnapshot.getValue() == null) {
                                Toast.makeText(AuthActivity.this, "Snapshot does not exist", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            // add list of customers
                            // on a large scale, this will have a performance hit
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                userList.add(ds.getValue(User.class));
                            }
                            // we will log user in via firebase since it include both
                            // password-based auth and google auth,
                            // here we're just creating a customer object linked to
                            // the firebase account
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            assert user != null;

                            // get matching account
                            Object[] matchingCustomer = userList.stream().filter(
                                    c -> c.userEmailAddress.equals(user.getEmail()) || c.__googleID.equals(user.getUid())
                            ).toArray();

                            // if there isn't any matching user, we create one
                            if (matchingCustomer.length == 0) {
                                User nguoiDung =
                                        new User(
                                                null,
                                                user.getUid(),
                                                null,
                                                user.getDisplayName(),
                                                user.getEmail(),
                                                user.getPhotoUrl().toString(),
                                                -1,
                                                null
                                        );

                                // add the user to firebase
                                controllerCustomer.setCustomer(customerAccount,
                                        false,
                                        new ControllerBase.SuccessListener() {
                                            @Override
                                            public void run() {
                                                Log.i("LoginActivity::Google", "Added account to Firebase");

                                                startProfileActivity(customerAccount.emailAddress);
                                            }
                                        },
                                        new ControllerBase.FailureListener() {
                                            @Override
                                            public void run(Exception error) {
                                                Toast.makeText(AuthActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                                                Log.e("LoginActivity::Google", "Failed to add account to Firebase");
                                                error.printStackTrace();
                                            }
                                        });
                            } else {
                                // if user is already exist
                                Log.i("LoginActivity::Google", "Got account from Firebase");

                                startProfileActivity(((Customer) matchingCustomer[0]).emailAddress);
                            }
                        }
                    },
                    new ControllerBase.FailureListener() {
                        @Override
                        public void run(Exception error) {
                            Toast.makeText(AuthActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                            Log.e("LoginActivity::Google", "Failed to get account from Firebase");
                            error.printStackTrace();
                        }
                    });
        } else {
            Objects.requireNonNull(task.getException()).printStackTrace();

            Toast.makeText(
                    this,
                    "Auth failed: " + Objects.requireNonNull(task.getException()).getMessage(),
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    // Facebook auth complete listener
    private void facebookCompleteListener(LoginResult loginResult) {
        controllerCustomer.getAllCustomer(
                new ControllerBase.SuccessListener() {
                    @Override
                    public void run(DataSnapshot dataSnapshot) {
                        List<Customer> customers = new ArrayList<>();

                        if (dataSnapshot.getValue() == null) {
                            Toast.makeText(AuthActivity.this, "Snapshot does not exist", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            customers.add(ds.getValue(Customer.class));
                        }

                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                (jsonObject, graphResponse) -> {
                                    Log.i("LoginActivity::Facebook", graphResponse.toString());

                                    try {
                                        Profile profile = Profile.getCurrentProfile();
                                        String email = jsonObject.getString("email");

                                        Object[] matchingCustomer = customers.stream().filter(
                                                c -> c.emailAddress.equals(email) || c.fid.equals(profile.getId())
                                        ).toArray();

                                        if (matchingCustomer.length == 0) {
                                            Customer customerAccount =
                                                    new Customer(
                                                            null,
                                                            null,
                                                            profile.getId(),
                                                            profile.getProfilePictureUri(500, 500).toString(),
                                                            profile.getName(),
                                                            null,
                                                            email,
                                                            null
                                                    );

                                            // add the user to firebase
                                            controllerCustomer.setCustomer(customerAccount,
                                                    false,
                                                    new ControllerBase.SuccessListener() {
                                                        @Override
                                                        public void run() {
                                                            Log.i("LoginActivity::Facebook", "Added account to Firebase");

                                                            startProfileActivity(customerAccount.emailAddress);
                                                        }
                                                    },
                                                    new ControllerBase.FailureListener() {
                                                        @Override
                                                        public void run(Exception error) {
                                                            Toast.makeText(AuthActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                                                            Log.e("LoginActivity::Facebook", "Failed to add account to Firebase");
                                                            error.printStackTrace();
                                                        }
                                                    });
                                        } else {
                                            // if user is already exist
                                            Log.i("LoginActivity::Facebook", "Got account from Firebase");

                                            startProfileActivity(((Customer) matchingCustomer[0]).emailAddress);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                });
                        Bundle bundle = new Bundle();
                        bundle.putString("fields", "id,name,email");
                        request.setParameters(bundle);
                        request.executeAsync();
                    }
                },
                new ControllerBase.FailureListener() {
                    @Override
                    public void run(Exception error) {
                        Toast.makeText(AuthActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                        Log.e("LoginActivity::Facebook", "Failed to get account from Firebase");
                        error.printStackTrace();
                    }
                });
    }

    // TODO replace the target activity with HomePage
    private void startProfileActivity(String email) {
        Intent intent = new Intent(AuthActivity.this, TestProfileActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("email", email);
        intent.putExtras(bundle);

        SharedPreferences.Editor editor = getSharedPreferences("cheetah", Context.MODE_PRIVATE).edit();
        editor.putString("email", email);
        editor.apply();

        startActivity(intent);
    }
}