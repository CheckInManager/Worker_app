package com.example.worker.accountAdmin.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AccountRepository {

    private DatabaseReference accountDatabase = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth accountAuth = FirebaseAuth.getInstance();
    DatabaseReference phone = accountDatabase.child("phoneNumber");
    DatabaseReference password = accountDatabase.child("passwrod");

    //HashMap user = new HashMap<>();

//    private int inputPhoneNumber;
//    private String inputPassword;
//    private String inputConfirmPassword;

    private static final AccountRepository INSTANCE = new AccountRepository();

    public static AccountRepository getInstance() {
        return INSTANCE;
    }

    private AccountRepository() {
        accountDatabase = FirebaseDatabase.getInstance().getReference();
    }

    //write firebase
    public void writeAccountMap(User user) {
        //user.put("phoneNumber", inputPhoneNumber);
        //user.put("password", inputPassword);
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
