package com.firstapp.duan1.firebase.auth;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyPhoneNumber {
    private final String TAG = VerifyPhoneNumber.class.getName();
    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private final Activity activity;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mForceResendingToken;

    public VerifyPhoneNumber(Activity activity) {
        this.activity = activity;
    }

    private void sendOtp(String phoneNumber, VerificationCompletedListener completedListener, VerificationFailedListener failedListener) {
        PhoneAuthProvider.verifyPhoneNumber(
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(phoneNumber)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(activity)
                        .setForceResendingToken(mForceResendingToken)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signInWithPhoneAuthCredential(phoneAuthCredential, completedListener, failedListener);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                failedListener.run(e);
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(verificationId, forceResendingToken);

                                mVerificationId = verificationId;
                                mForceResendingToken = forceResendingToken;
                            }
                        })
                        .build()
        );
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential, VerificationCompletedListener completedListener, VerificationFailedListener failedListener) {
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, task -> {
                    if (task.isSuccessful()) {
                        completedListener.run();
                    } else {
                        Log.w(TAG, "signInWithCredential:failure", task.getException());

                        failedListener.run(task.getException());
                    }
                });
    }

    public abstract static class VerificationCompletedListener {
        public abstract void run();
    }

    public abstract static class VerificationFailedListener {
        public abstract void run(Exception e);
    }
}
