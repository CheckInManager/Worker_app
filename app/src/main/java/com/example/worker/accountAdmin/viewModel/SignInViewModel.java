package com.example.worker.accountAdmin.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.worker.accountAdmin.repository.AccountRepository;
import com.example.worker.accountAdmin.model.Result;
import com.example.worker.accountAdmin.model.SingleCallback;
import com.example.worker.accountAdmin.model.User;

public class SignInViewModel extends ViewModel {
    private AccountRepository accountRepository = AccountRepository.getInstance();

    private MutableLiveData<Boolean> loggedIn = new MutableLiveData<>(false);


    private User user = new User();
    String errorMessage = "";

    public void trySignIn(String phoneNumber, String password) {
        accountRepository.trySignIn(phoneNumber, password, new SingleCallback<Result<User>>() {
            @Override
            public void onComplete(Result<User> result) {
                if (result instanceof Result.Success) {
                    User loggedInUser = ((Result.Success<User>) result).getData();
                    loggedIn.postValue(true);
                    user = loggedInUser;
                } else {
                    errorMessage = ((Result.Error) result).getError().getMessage();
                    setErrorMessage(errorMessage);
                }
            }
        });
    }


    public LiveData<Boolean> isLoggedIn() {
        return loggedIn;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getCurrentUser() {
        return accountRepository.getCurrUser();
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
