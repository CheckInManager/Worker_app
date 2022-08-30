package com.example.worker.accountAdmin.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.worker.accountAdmin.repository.AccountRepository;
import com.example.worker.accountAdmin.model.Result;
import com.example.worker.accountAdmin.model.SingleCallback;
import com.example.worker.accountAdmin.model.User;

public class ScanQrViewModel extends ViewModel {

    private AccountRepository accountRepository = AccountRepository.getInstance();
    private MutableLiveData<Boolean> registeredWorksite = new MutableLiveData<>(false);

    private User user = accountRepository.getCurrUser();

    public void registerWorksite(String worksite) {
        user.setworksite(worksite);
        accountRepository.addUserWorkSite(user, new SingleCallback<Result<User>>() {
            @Override
            public void onComplete(Result<User> result) {
                if (result instanceof Result.Success) {

                } else {
                    Log.v("scanQrCodeViewMode", " 오류");
                }
            }
        });
    }

    public LiveData<Boolean> isRegisteredWorksite() {
        return registeredWorksite;
    }

    public void setCurrDevice(String deviceNum) {
        user.setworksite(deviceNum);
    }
}
