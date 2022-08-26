package com.example.worker.accountAdmin.model;

import android.util.Log;

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
    private CollectionReference noticeRef = noticeStore.collection("notice");


    private ArrayList<Notice> noticeList;

    private NoticeRepository() {
        noticeList = new ArrayList<>();
    }

    public static NoticeRepository getInstance() {
        return INSTANCE;
    }

    public void getNotice(String worksite, SingleCallback<Result<ArrayList>> callback) {
        noticeRef.whereEqualTo("worksiteKeyValue", worksite)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                Notice foundNotice = documentSnapshot.toObject(Notice.class);
                                noticeList.add(foundNotice);
                                Log.v("repository test", "" + noticeList.get(0).getMemo());
                            }
                            callback.onComplete(new Result.Success<ArrayList<Notice>>(noticeList));

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
