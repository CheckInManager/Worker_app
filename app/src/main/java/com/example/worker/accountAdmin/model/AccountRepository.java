package com.example.worker.accountAdmin.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class AccountRepository {

    private static final AccountRepository INSTANCE = new AccountRepository();
    private FirebaseFirestore accountStore = FirebaseFirestore.getInstance();
    private CollectionReference usersRef = accountStore.collection("users");

    private AccountRepository() {
    }

    public static AccountRepository getInstance() {
        return INSTANCE;
    }

    //회원 가입
    public void trySignUp(User user, SingleCallback<Result<User>> callback) {
        usersRef.document(user.getPhoneNumber())
                .set(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            callback.onComplete(new Result.Success<User>(user));
                            Log.v("accountRepository", "trySignUp" + user.getPhoneNumber() + " " + user.getPassword() + "완료");
                        } else {
                            callback.onComplete(new Result.Error(new Exception("Network call failed: Sign Up")));
                        }

                    }
                });
    }

    //로그인
    public void trySignIn(String phoneNumber, String password, SingleCallback<Result<User>> callback) {
        usersRef.whereEqualTo("phoneNumber", phoneNumber)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                User foundUser = documentSnapshot.toObject(User.class);
                                if (foundUser.password.equals(password))
                                    callback.onComplete(new Result.Success<User>(foundUser));
                                else
                                    callback.onComplete(new Result.Error(new Exception("Password is incorrect")));
                            }
                        } else {
                            callback.onComplete(new Result.Error(new Exception("Network call failed: Sign In")));
                        }
                    }
                });
    }

    //user information 입력
    public void addUserInformation(User user, SingleCallback<Result<User>> callback) {
        //phone number 가 비어있음..
        Log.v("addUserInformation", " : 확인" + user.getPhoneNumber());
        Log.v("addUserInformation", " : 확인" + user.getCareer());
        usersRef.whereEqualTo("phoneNumber", user.getPhoneNumber())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                usersRef.document(documentSnapshot.getId()).set(user);
                            }
                        }
                    }
                });

    }


}
