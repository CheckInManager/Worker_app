package com.example.worker.accountAdmin.model;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class NoticeRepository {

    public static NoticeRepository INSTANCE = new NoticeRepository();

    private FirebaseFirestore noticeStore = FirebaseFirestore.getInstance();
    private FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
    private CollectionReference noticeRef = noticeStore.collection("notice");


    private ArrayList<Object> noticeList;

    private NoticeRepository() {
        noticeList = new ArrayList<>();
    }

    public static NoticeRepository getInstance() {
        return INSTANCE;
    }

    public void getNotice(SingleCallback<Result<ArrayList>> callback) {
        noticeRef.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                Notice foundNotice = documentSnapshot.toObject(Notice.class);
                                noticeList.add(foundNotice);
                            }
                            callback.onComplete(new Result.Success<ArrayList<Object>>(noticeList));

                        }
                        else{
                            callback.onComplete(new Result.Error(new Exception("Network call failed: get notice")));
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }



}
