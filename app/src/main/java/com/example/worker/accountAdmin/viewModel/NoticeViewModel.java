package com.example.worker.accountAdmin.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.worker.accountAdmin.model.ListenerCallback;
import com.example.worker.accountAdmin.repository.AccountRepository;
import com.example.worker.accountAdmin.model.Notice;
import com.example.worker.accountAdmin.repository.NoticeRepository;
import com.example.worker.accountAdmin.model.Result;
import com.example.worker.accountAdmin.model.SingleCallback;
import com.example.worker.accountAdmin.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NoticeViewModel extends ViewModel {
    MutableLiveData<Boolean> getDBNotice = new MutableLiveData<>(false);

    private NoticeRepository noticeRepository = NoticeRepository.getInstance();
    private AccountRepository accountRepository = AccountRepository.getInstance();

    private List<Notice> noticeArrayList = new ArrayList<>();

    private List<Map<String, Object>> noticeMapList = new ArrayList<>();
    private User user = accountRepository.getCurrUser();

    public void getNotice(){
        noticeRepository.getNotice(getCurrUser().getworksite(), new ListenerCallback<Result<List<Map<String, Object>>>>() {
            @Override
            public void onUpdate(Result<List<Map<String, Object>>> result) {
                if(result instanceof Result.Success){
                    noticeMapList = ((Result.Success<List<Map<String, Object>>>) result).getData();
                    for (int i = 0; i < noticeMapList.size(); i++) {
                        Notice newNotice = new Notice();
                        String time = String.valueOf(noticeMapList.get(i).get("timestamp"));
                        newNotice.setMemo(String.valueOf(noticeMapList.get(i).get("content")));
                        newNotice.setKeyValue(String.valueOf(noticeMapList.get(i).get("id")));
                        newNotice.setTime(Long.parseLong(time));
                        newNotice.setNoticeName(String.valueOf(noticeMapList.get(i).get("title")));
                        noticeArrayList.add(newNotice);
                    }
                    getDBNotice.postValue(true);
                }else{

                }
            }
        });
    }


//    public void getNotice() {
//        noticeRepository.getNotice(getCurrUser().getworksite(), new SingleCallback<Result<List<Map<String, Object>>>>() {
//            @Override
//            public void onComplete(Result<List<Map<String, Object>>> result) {
//                if (result instanceof Result.Success) {
//                    noticeMapList = ((Result.Success<List<Map<String, Object>>>) result).getData();
//                    for (int i = 0; i < noticeMapList.size(); i++) {
//                        Notice newNotice = new Notice();
//                        String time = String.valueOf(noticeMapList.get(i).get("timestamp"));
//                        newNotice.setMemo(String.valueOf(noticeMapList.get(i).get("content")));
//                        newNotice.setKeyValue(String.valueOf(noticeMapList.get(i).get("id")));
//                        newNotice.setTime(Long.parseLong(time));
//                        newNotice.setNoticeName(String.valueOf(noticeMapList.get(i).get("title")));
//                        noticeArrayList.add(newNotice);
//                    }
//                    getDBNotice.postValue(true);
//                } else {
//
//                }
//            }
//        });
//    }

    public LiveData<Boolean> getGetDBNotice() {
        return getDBNotice;
    }

    public List<Notice> getNoticeArrayList() {
        return noticeArrayList;
    }

    public User getCurrUser() {
        return user;
    }
}
