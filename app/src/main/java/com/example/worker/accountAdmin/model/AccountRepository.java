package com.example.worker.accountAdmin.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class AccountRepository {

    private DatabaseReference accountDatabase = FirebaseDatabase.getInstance().getReference();
    //private FirebaseStorage accountStorage = FirebaseStorage.getInstance();

    private FirebaseAuth accountAuth = FirebaseAuth.getInstance();

    private static final AccountRepository INSTANCE = new AccountRepository();

    public static AccountRepository getInstance() {
        return INSTANCE;
    }

    private AccountRepository() {}

    //write firebase
    public void writeAccountInfo(User user) {
        writeAccount(user, String.valueOf(user.getPhoneNumber()), user.getPhoneNumber(), user.getPassword());
    }

    private void writeAccount(User user, String id, int phoneNumber, String password) {

        accountDatabase.child("user").child(id).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.v("회원가입 데이터 작성","성공" + phoneNumber);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.v("회원가입 데이터 작성","실패");
                    }
                });

    }


}
