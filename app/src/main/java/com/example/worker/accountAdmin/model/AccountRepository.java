package com.example.worker.accountAdmin.model;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.worker.accountAdmin.viewModel.AddCareerListItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository
{

    private static final AccountRepository INSTANCE = new AccountRepository();
    private FirebaseFirestore accountStore = FirebaseFirestore.getInstance();
    private FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
    private CollectionReference usersRef = accountStore.collection("users");


    private User currUser;
    private List<AddCareerListItem> careerList;

    private AccountRepository()
    {
        careerList = new ArrayList<>();
    }

    public static AccountRepository getInstance()
    {
        return INSTANCE;
    }

    //sign up
    public void trySignUp(User user, SingleCallback<Result<User>> callback)
    {
        usersRef.document(user.getPhoneNumber())
                .set(user)
                .addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            callback.onComplete(new Result.Success<User>(user));
                            //tmpUser.password = user.password;
                            Log.v("accountRepository", "trySignUp" + user.getPhoneNumber() + " " + user.getPassword() + "완료");
                        }
                        else
                        {
                            callback.onComplete(new Result.Error(new Exception("Network call failed: Sign Up")));
                        }

                    }
                });
    }

    //sign in
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
                                if (foundUser.getPassword().equals(password))
                                {
                                    currUser = foundUser;
                                    callback.onComplete(new Result.Success<User>(currUser));
                                }
                                else
                                {
                                    callback.onComplete(new Result.Error(new Exception("Password is incorrect")));
                                }
                            }
                        }
                        else
                        {
                            callback.onComplete(new Result.Error(new Exception("Network call failed: Sign In")));
                        }
                    }
                });
    }

    //user information add
    public void addUserInformation(User user, SingleCallback<Result<User>> callback)
    {
        usersRef.document(user.getPhoneNumber()).set(user).addOnCompleteListener(new OnCompleteListener<Void>()
        {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if (task.isSuccessful())
                {
                    callback.onComplete(new Result.Success<User>(user));
                }
                else
                {
                    callback.onComplete(new Result.Error(task.getException()));
                }
            }
        });
    }


    //user image add
    public void uploadUserImage(String phoneNumber, Bitmap currUserBitmap)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        currUserBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        StorageReference uploadRef = firebaseStorage.getReference().child("userImages/user_" + phoneNumber);
        UploadTask uploadTask = uploadRef.putBytes(data);
        currUser.setPicture(true);
        uploadTask.addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception exception)
            {
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
        {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
            {

            }
        });
    }

    //저장된 이미지 가져오기
    public void downloadUserImage(String phoneNumber){
        StorageReference loadRef = firebaseStorage.getReference();
        loadRef.child("userImages/user_" + phoneNumber).
                getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Log.d("repository image", String.valueOf(uri));

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    public void addUserWorkSite(User user, SingleCallback<Result<User>> callback)
    {
        usersRef.document(user.getPhoneNumber()).set(user).addOnCompleteListener(new OnCompleteListener<Void>()
        {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if (task.isSuccessful())
                {
                    callback.onComplete(new Result.Success<User>(user));
                }
                else
                {
                    callback.onComplete(new Result.Error(task.getException()));
                }
            }
        });
    }


    public User getCurrUser()
    {
        return currUser;
    }

    
    //recycle view 에서 뽑을 item을 careerList에 넣음
    public void setCareerRecords(AddCareerListItem item) {
        careerList.add(item);
    }


    public List<AddCareerListItem> getCareerList(){
        return careerList;
    }
}
