package com.example.worker.accountAdmin.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class AccountRepository {

    private FirebaseFirestore accountStore = FirebaseFirestore.getInstance();
    private static final AccountRepository INSTANCE = new AccountRepository();

    public static AccountRepository getInstance() {
        return INSTANCE;
    }

    private AccountRepository() {
    }

    CollectionReference usersRef = accountStore.collection("users");
    //private boolean checkSignUp = false;
    //private boolean checkSignIn = false;

    //sign up
    public void writeAccount(User user) {
        Map<String, Object> users = new HashMap<>();
        //user account
        users.put("phoneNUmber", user.phoneNumber);
        users.put("password", user.password);

        //user information
        users.put("image", user.getImage());
        users.put("name", user.getName());
        users.put("career", user.getCareer());
        users.put("accidentRecord", user.getAccidentRecord());

        //1. user 등록 시 전화번호, 비밀번호, 비밀번호 확인 후 공백시 블로킹
        //2. account store 에서 document (id?)로 같은 거 검색 후에 중복 체크

        accountStore.collection("users")
                .document(String.valueOf(user.phoneNumber))
                .set(users)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.v("account Repository", ": 데이터 입력 성공, 전화번호 : " + user.phoneNumber);
                    }

                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.v("account Repository", ": 데이터 입력 실패");
                    }
                });
    }


    //sign in
    public void searchAccount(SignInRecord signInRecord){

        boolean checkSignIn = false;

        accountStore.collection("users")
                .whereEqualTo(String.valueOf(signInRecord.phoneNumber), signInRecord.password)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                checkAccount(checkSignIn);
                                Log.v("document1122", ""+ documentSnapshot.getData());
                            }
                        }     
                        else{
                            Log.v("document", "실패");
                        }
                    }
                });
    }



    public boolean checkAccount(boolean checkSignIn) {
        return checkSignIn = true;
    }

}
