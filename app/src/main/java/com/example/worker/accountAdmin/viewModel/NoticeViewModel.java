package com.example.worker.accountAdmin.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.worker.accountAdmin.model.Notice;
import com.example.worker.accountAdmin.model.NoticeRepository;
import com.example.worker.accountAdmin.model.Result;
import com.example.worker.accountAdmin.model.SingleCallback;

import java.util.ArrayList;

public class NoticeViewModel extends ViewModel {
    MutableLiveData<Boolean> getDBNotice = new MutableLiveData<>(false);

    private NoticeRepository noticeRepository = NoticeRepository.getInstance();

    private ArrayList<Notice> noticeArrayList = new ArrayList<>();

    public void getNotice() {
        noticeRepository.getNotice(new SingleCallback<Result<ArrayList>>() {
            @Override
            public void onComplete(Result<ArrayList> result) {
                if (result instanceof Result.Success) {
                    noticeArrayList = ((Result.Success<ArrayList<Notice>>) result).getData();
                    getDBNotice.postValue(true);
                }
                else{
                    //error
                }
            }
        });

    }

    public LiveData<Boolean> getGetDBNotice() {
        return getDBNotice;
    }

    public ArrayList<Notice> getNoticeArrayList() {

        //test
        Notice notice = new Notice();
        notice.setNoticeName("ff");

        noticeArrayList.add(notice);
        return noticeArrayList;
    }
}
