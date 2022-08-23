package com.example.worker.accountAdmin.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.worker.accountAdmin.model.AccountRepository;
import com.example.worker.accountAdmin.model.Result;
import com.example.worker.accountAdmin.model.SingleCallback;
import com.example.worker.accountAdmin.model.User;

public class ReSettingPasswordViewModel extends ViewModel {


    private AccountRepository accountRepository = AccountRepository.getInstance();
    MutableLiveData<Boolean> passwordUpdate = new MutableLiveData<>(false);

    private User user = accountRepository.getCurrUser();

    public void updatePassword(User user){
        accountRepository.updatePassword(user, new SingleCallback<Result<User>>() {
            @Override
            public void onComplete(Result<User> result) {
                if(result instanceof Result.Success){

                  passwordUpdate.postValue(true);


                }
                else{
                   String errorMessage = ((Result.Error) result).getError().getMessage();
                }
            }
        });


    }


    public LiveData<Boolean> getPasswordUpdate() {
        return passwordUpdate;
    }

    public User getCurrUser() {
        return user;
    }
}
