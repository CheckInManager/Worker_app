package com.example.worker.accountAdmin.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;
import java.util.Map;

public class AccountRepository {


    private FirebaseAuth accountAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore accountStore = FirebaseFirestore.getInstance();

    private static final AccountRepository INSTANCE = new AccountRepository();

    public static AccountRepository getInstance() {
        return INSTANCE;
    }

    private AccountRepository() {
    }


    //sign up
    public void writeAccount(User user) {
        Map<String, Object> users = new HashMap<>();
        users.put("phoneNUmber", user.phoneNumber);
        users.put("password", user.password);

        accountStore.collection("users")
                .add(users)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.v("account Repository", ": 데이터 입력 성공" + user.phoneNumber);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.v("account Repository", ": 데이터 입력 실패");
                    }
                });
    }


    private boolean checkingPhoneNumberOverlap(boolean overlap) {
        overlap = false;
        if (overlap == false) {
            overlap = true;
        }
        return overlap;
    }

    private boolean checkingSignIn(boolean checkSignIn) {
        checkSignIn = false;
        if (checkSignIn == false) {
            checkSignIn = true;
        }
        return checkSignIn;
    }

}
