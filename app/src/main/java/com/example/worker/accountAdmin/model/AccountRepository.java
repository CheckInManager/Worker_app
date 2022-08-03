package com.example.worker.accountAdmin.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountRepository {

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://checkinmanager-b0690-default-rtdb.firebaseio.com/");
    private FirebaseAuth accountAuth = FirebaseAuth.getInstance();

    private static final AccountRepository INSTANCE = new AccountRepository();

    private boolean phoneNumberOverlap = false;
    private boolean checkSignIn = false;

    public static AccountRepository getInstance() {
        return INSTANCE;
    }

    private AccountRepository() {
    }


    //sign up

    public void writeAccountInfo(User user) {
        writeAccount(user);
    }

    //write firebase
    private void writeAccount(User user) {

        //phoneNumber 등록 여부 확인
        databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(String.valueOf(user.phoneNumber))) {
                    phoneNumberOverlap = checkingPhoneNumberOverlap(phoneNumberOverlap);
                    Log.v("이미 등록된 번호", "");
                    //등록된 번호 -> true

                } else {
                    databaseReference.child("users").child(String.valueOf(user.phoneNumber)).setValue(user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //sign in
//    public void checkSignIn(SignInRecord signInRecord){
//        //firebase 에서 number로 찾아서 읽게 해오고, password 비교 후 true, false 값 리턴
//        databaseReference.child("users").child(String.valueOf(signInRecord.getPhoneNumber())).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if (!task.isSuccessful()) {
//                    Log.e("firebase", "Error getting data", task.getException());
//                    checkingSignIn(checkSignIn);
//
//                    if(signInRecord.password == databaseReference.child("users").child()){
//                        checkSignIn = true;
//                    }
//                }
//                else {
//                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
//                }
//            }
//        });
//    }


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

    public boolean isPhoneNumberOverlap() {
        return phoneNumberOverlap;
    }
}
