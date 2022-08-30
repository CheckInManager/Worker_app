package com.example.worker.accountAdmin.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.worker.accountAdmin.repository.AccountRepository;
import com.example.worker.accountAdmin.model.Result;
import com.example.worker.accountAdmin.model.SingleCallback;
import com.example.worker.accountAdmin.model.User;

public class FindPasswordViewModel extends ViewModel {

    private AccountRepository accountRepository = AccountRepository.getInstance();
    private MutableLiveData<Boolean> confirm = new MutableLiveData<>(false);

    private User user = accountRepository.getCurrUser();

    public void tryFindPhoneNumber(String phoneNumber) {
        accountRepository.findPassword(phoneNumber, new SingleCallback<Result<User>>() {
            @Override
            public void onComplete(Result<User> result) {
                if (result instanceof Result.Success) {
                    User findUser = ((Result.Success<User>) result).getData();
                    confirm.postValue(true);
                } else {
                    String errorMessage = ((Result.Error) result).getError().getMessage();
                }
            }
        });
    }


    public MutableLiveData<Boolean> getConfirm() {
        return confirm;
    }

    public User getCurrUser() {
        return user;
    }

}
