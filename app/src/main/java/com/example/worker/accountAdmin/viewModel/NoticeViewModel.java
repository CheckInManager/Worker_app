package com.example.worker.accountAdmin.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NoticeViewModel extends ViewModel {
    MutableLiveData<Boolean> getDBNotice = new MutableLiveData<>(false);




    public LiveData<Boolean> getGetDBNotice() {
        return getDBNotice;
    }
}
