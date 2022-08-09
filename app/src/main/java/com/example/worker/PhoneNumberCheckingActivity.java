package com.example.worker;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.worker.accountAdmin.fragment.InputInformationFragment;
import com.example.worker.accountAdmin.fragment.SignInFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class PhoneNumberCheckingActivity extends Activity {

    private FirebaseAuth accountAuth = FirebaseAuth.getInstance();
    private String verificationID;

    public void sendVerification(String phoneNumber, String password){
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(accountAuth)

                        .setPhoneNumber("+91" + String.valueOf(phoneNumber))
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(mCallbacks)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
    mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(@NotNull  PhoneAuthCredential credential) {
            final String code = credential.getSmsCode();
            if (code != null){
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NotNull FirebaseException e) {
            Toast.makeText(PhoneNumberCheckingActivity.this, "Verification Failed", Toast.LENGTH_SHORT);
        }

        @Override
        public void onCodeSent(@NonNull String s,
                @NonNull PhoneAuthProvider.ForceResendingToken token)
        {
            super.onCodeSent(s, token);
            verificationID = s;
        }
    };

    private void verifyCode(String code) {
        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(verificationID, code);
        signInByCredentials(phoneAuthCredential);
    }

    //인증 성공
    private void signInByCredentials(PhoneAuthCredential phoneAuthCredential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(phoneAuthCredential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(PhoneNumberCheckingActivity.this, "Successful", Toast.LENGTH_SHORT);

                            // 밑에 건 수정해야하지 않을까
                            //startActivity(new Intent(SignInActivity.this, SignInActivity.class));
                            startActivity(new Intent(PhoneNumberCheckingActivity.this, SignInFragment.class));
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser != null){
            //startActivity(new Intent(SignInActivity.this, SignInActivity.class));
            startActivity(new Intent(PhoneNumberCheckingActivity.this, SignInFragment.class));
            finish();
        }
    }
}
