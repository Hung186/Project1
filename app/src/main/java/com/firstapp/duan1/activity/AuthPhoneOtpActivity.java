package com.firstapp.duan1.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firstapp.duan1.R;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class AuthPhoneOtpActivity extends AppCompatActivity {
    public static final String TAG = AuthPhoneOtpActivity.class.getName();
    private EditText etEditOtp;
    private Button btnSendOtpCode;
    private TextView tvSendOtpAgain;

    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private String mPhoneNumber;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mForceResendingToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_otp);

        getDataIntent();
        setTitleBar();
        initUi();

        btnSendOtpCode.setOnClickListener(v -> {
            String strOtpCode = etEditOtp.getText().toString().trim();
            onClickSendOtpCode(strOtpCode);
        });

        tvSendOtpAgain.setOnClickListener(v -> onClickSendOtpAgain());
    }

    private void getDataIntent() {
        mPhoneNumber = getIntent().getStringExtra("phone_number");
        mVerificationId = getIntent().getStringExtra("verification_id");
    }

    private void setTitleBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Enter Otp");
        }
    }

    private void initUi() {
        etEditOtp = findViewById(R.id.edtOtp);
        btnSendOtpCode = findViewById(R.id.btnSendOTPCode);
        tvSendOtpAgain = findViewById(R.id.tvSendOtpAgain);
    }


    private void onClickSendOtpAgain() {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(mPhoneNumber)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setForceResendingToken(mForceResendingToken)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signInWithPhoneAuthCredential(phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(AuthPhoneOtpActivity.this, "verification failed", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(verificationId, forceResendingToken);
                                mVerificationId = verificationId;
                                mForceResendingToken = forceResendingToken;
                            }
                        })
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void onClickSendOtpCode(String strOtpCode) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, strOtpCode);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.i(TAG, "signInWithCredential:success");

                        FirebaseUser user = task.getResult().getUser();

                        goToMainActivity(user.getPhoneNumber());
                    } else {
                        Log.w(TAG, "signInWithCredential:failure", task.getException());

                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(AuthPhoneOtpActivity.this, " The verification code entered was invalid", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void goToMainActivity(String phoneNumber) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("phone_number", phoneNumber);

        startActivity(intent);
    }
}