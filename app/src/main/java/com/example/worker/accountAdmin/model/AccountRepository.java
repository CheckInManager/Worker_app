package com.example.worker.accountAdmin.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
    private boolean checkSignIn = false;

    //sign up
    public void writeAccount(User user) {
        Map<String, Object> users = new HashMap<>();
        //user account
        users.put("phoneNumber", user.phoneNumber);
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
                        Log.v("account Repository", ": 데이터 입력 통신 성공, 전화번호 : " + user.phoneNumber);
                    }

                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.v("account Repository", ": 데이터 입력 통신 실패");
                    }
                });
    }


    public void setSignInRecord(SignInRecord signInRecord) {
        searchAccount(signInRecord);
    }

    public boolean getCheckSignIn() {
        return checkSignIn;
    }


    //sign in
    private void searchAccount(SignInRecord signInRecord) {
        String[] passwordInDB = {""};
        accountStore.collection("users")
                .whereEqualTo("phoneNumber", signInRecord.getPhoneNumber())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                passwordInDB[0] = String.valueOf(documentSnapshot.getData().get("password"));
                            }
                            if (passwordInDB[0].equals(signInRecord.getPassword())) {
                                checkSignIn = checkAccount();
                                Log.v("됨", "" + checkSignIn);
                            }
                            else {
                                // 로그인 실패
                                Log.v("로그인 실패", "");
                            }
                        } else {
                            Log.v("account Repostiory", " :로그인 전화번호 검색 통신 실패" + task.getException());
                        }

                    }
                });

    }


    public boolean checkAccount() {
        return true;
    }

}
