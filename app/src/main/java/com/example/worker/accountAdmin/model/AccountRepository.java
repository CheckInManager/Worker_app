package com.example.worker.accountAdmin.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class AccountRepository
{

    private static final AccountRepository INSTANCE = new AccountRepository();
    private FirebaseFirestore accountStore = FirebaseFirestore.getInstance();
    private CollectionReference usersRef = accountStore.collection("users");
    //private boolean checkSignUp = false;
    private boolean checkSignIn = false;

    private AccountRepository()
    {
    }

    public static AccountRepository getInstance()
    {
        return INSTANCE;
    }

    //sign up
    //Create
    //Add

    public void createAccount(User user)
    {
        //1. user 등록 시 전화번호, 비밀번호, 비밀번호 확인 후 공백시 블로킹
        //2. account store 에서 document (id?)로 같은 거 검색 후에 중복 체크

        accountStore.collection("users")
                .document(String.valueOf(user.phoneNumber))
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>()
                {
                    @Override
                    public void onSuccess(Void unused)
                    {
                        Log.v("account Repository", ": 데이터 입력 통신 성공, 전화번호 : " + user.phoneNumber);
                    }

                })
                .addOnFailureListener(new OnFailureListener()
                {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Log.v("account Repository", ": 데이터 입력 통신 실패");
                    }
                });
    }

    //sign in
    //검색하고 패스워드 검색하서
    //입력한 정보랑 같으면 true

    //SOLID
    //S - Single Responsibility Principle
    //
    public void trySignIn(String phoneNumber, String password, SingleCallback<Result<User>> callback)
    {
        usersRef.whereEqualTo("phoneNumber", phoneNumber)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
                {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {
                        if (task.isSuccessful())
                        {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult())
                            {
                                User foundUser = documentSnapshot.toObject(User.class);
                                if (foundUser.password.equals(password))
                                    callback.onComplete(new Result.Success<User>(foundUser));
                                else
                                    callback.onComplete(new Result.Error(new Exception("Password is incorrect")));
                            }
                        }
                        else
                        {
                            callback.onComplete(new Result.Error(new Exception("Network call failed")));
                        }
                    }
                });
    }
}
