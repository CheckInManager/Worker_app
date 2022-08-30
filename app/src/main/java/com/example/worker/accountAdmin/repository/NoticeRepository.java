package com.example.worker.accountAdmin.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.worker.accountAdmin.model.ListenerCallback;
import com.example.worker.accountAdmin.model.Notice;
import com.example.worker.accountAdmin.model.Result;
import com.example.worker.accountAdmin.model.SingleCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoticeRepository {

    public static NoticeRepository INSTANCE = new NoticeRepository();

    private FirebaseFirestore noticeStore = FirebaseFirestore.getInstance();
    private CollectionReference noticeRef = noticeStore.collection("notice");


    private NoticeRepository() {
    }

    public static NoticeRepository getInstance() {
        return INSTANCE;
    }

    public void getNotice(String worksite, ListenerCallback<Result<List<Map<String, Object>>>> callback){
        noticeRef.whereEqualTo("worksiteId", Long.valueOf(worksite))
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error ==null){
                            List<Map<String, Object>> noticeMapList = new ArrayList<>();
                            List<DocumentSnapshot> snaps = value.getDocuments();
                            for(int i=0;i<snaps.size();i++){
                                Map<String ,Object> noticeMap = new HashMap<>();
                                noticeMap.put("content", snaps.get(i).getString("content"));
                                noticeMap.put("id", snaps.get(i).getLong("id"));
                                noticeMap.put("timestamp", snaps.get(i).getLong("timestamp"));
                                noticeMap.put("title", snaps.get(i).getString("title"));
                                Log.d("DEBUG", "onEvent: "+noticeMap);
                                noticeMapList.add(noticeMap);
                            }
                            callback.onUpdate(new Result.Success<List<Map<String, Object>>>(noticeMapList));
                        }else{
                            callback.onUpdate(new Result.Error(new Exception("error")));
                        }
                    }
                });
    }

//    public void getNotice(String worksite, SingleCallback<Result<List<Map<String, Object>>>> callback) {
//        noticeRef.whereEqualTo("worksiteId", Long.valueOf(worksite))
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        List<Map<String, Object>> noticeMapList = new ArrayList<>();
//                        Map<String, Object> noticeMap = new HashMap<>();
//                        if (task.isSuccessful()) {
//                            List<DocumentSnapshot> snaps = task.getResult().getDocuments();
//                            for (DocumentSnapshot snap : snaps) {
//                                noticeMap.put("content", snap.getString("content"));
//                                noticeMap.put("id", snap.getLong("id"));
//                                noticeMap.put("timestamp", snap.getLong("timestamp"));
//                                noticeMap.put("title", snap.getString("title"));
//                                noticeMapList.add(noticeMap);
//                            }
//                            callback.onComplete(new Result.Success<List<Map<String, Object>>>(noticeMapList));
//                        }
//                    }
//                });
//    }
}
