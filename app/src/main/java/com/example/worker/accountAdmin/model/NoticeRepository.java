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

    public void getNotice(String worksite, SingleCallback<Result<List<Map<String, Object>>>> callback) {
        noticeRef.whereEqualTo("worksiteId",Long.valueOf(worksite))
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        List<Map<String, Object>> noticeMapList = new ArrayList<>();
                        Map<String, Object> noticeMap = new HashMap<>();
                        if(task.isSuccessful()){
                            List<DocumentSnapshot> snaps = task.getResult().getDocuments();
                            for(DocumentSnapshot snap: snaps){
                                noticeMap.put("content", snap.getString("content"));
                                noticeMap.put("id", snap.getLong("id"));
                                noticeMap.put("timestamp", snap.getLong("timestamp"));
                                noticeMap.put("title", snap.getString("title"));
                                noticeMapList.add(noticeMap);
                            }
                            callback.onComplete(new Result.Success<List<Map<String,Object>>>(noticeMapList));
                        }
                    }
                });

//        noticeRef.whereEqualTo("worksite.id", worksite)
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
