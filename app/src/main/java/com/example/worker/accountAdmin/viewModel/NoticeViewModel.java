package com.example.worker.accountAdmin.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.worker.accountAdmin.model.AccountRepository;
import com.example.worker.accountAdmin.model.Notice;
import com.example.worker.accountAdmin.model.NoticeRepository;
import com.example.worker.accountAdmin.model.Result;
import com.example.worker.accountAdmin.model.SingleCallback;
import com.example.worker.accountAdmin.model.User;

import java.util.ArrayList;

public class NoticeViewModel extends ViewModel {
    MutableLiveData<Boolean> getDBNotice = new MutableLiveData<>(false);

    private NoticeRepository noticeRepository = NoticeRepository.getInstance();
    private AccountRepository accountRepository = AccountRepository.getInstance();

    private ArrayList<Notice> noticeArrayList = new ArrayList<>();

    private User user = accountRepository.getCurrUser();


    public void getNotice() {
        noticeRepository.getNotice(getCurrUser().getworksite(), new SingleCallback<Result<ArrayList>>() {
            @Override
            public void onComplete(Result<ArrayList> result) {
                if (result instanceof Result.Success) {
                    noticeArrayList = ((Result.Success<ArrayList<Notice>>) result).getData();
                    Log.v("viewmodeltest", "" +  noticeArrayList.size());

                    getDBNotice.postValue(true);
                }
                else{

                }
            }
        });

    }

    public LiveData<Boolean> getGetDBNotice() {
        return getDBNotice;
    }

    public ArrayList<Notice> getNoticeArrayList() {
        return noticeArrayList;
    }

    public  User getCurrUser(){return user;}
}
