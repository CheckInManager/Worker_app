package com.example.worker.accountAdmin.model;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import javax.sql.DataSource;

public class AccountRepository {

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://checkinmanager-b0690-default-rtdb.firebaseio.com/");
    private FirebaseAuth accountAuth = FirebaseAuth.getInstance();

    private static final AccountRepository INSTANCE = new AccountRepository();

    public static AccountRepository getInstance() {
        return INSTANCE;
    }

    private AccountRepository() {}


    public void writeAccountInfo(User user) {
        writeAccount(user);
    }

    //write firebase
    private void writeAccount(User user) {

        //phoneNumber 등록 여부 확인
        databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(String.valueOf(user.phoneNumber))){
                    Log.v("이미 등록된 번호", "");
                    //fragment 에 toast 띄우기
                }
                else{
                    databaseReference.child("users").child(String.valueOf(user.phoneNumber)).setValue(user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}
