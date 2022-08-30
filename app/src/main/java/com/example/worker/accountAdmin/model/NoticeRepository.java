package com.example.worker.accountAdmin.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        noticeRef.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            List<Notice> tmpNotice = new ArrayList<>();
                            for(DocumentSnapshot documentSnapshot : task.getResult()){
                                Map<String, String> worksiteMap = new HashMap<>();
                                if(worksiteMap.get("id").equals(worksite)){

                                }
                            }
                        }
                    }
                })
//        noticeRef.whereEqualTo("worksite", worksite)
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            ArrayList<Notice> tmpNotice = new ArrayList<>();
//
//                            //여기서 중복 저장 됨
//                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
//                                Notice foundNotice = documentSnapshot.toObject(Notice.class);
//
//                                tmpNotice.add(foundNotice);
//                            }
//
//                            noticeList = tmpNotice ;
//                            callback.onComplete(new Result.Success<ArrayList<Notice>>(noticeList));
//
//                        }
//                        else{
//                            callback.onComplete(new Result.Error(new Exception("Network call failed: get notice")));
//                        }
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//
//                    }
//                });
    }



}
