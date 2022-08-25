package com.example.worker.accountAdmin.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.worker.accountAdmin.model.AccountRepository;
import com.example.worker.accountAdmin.model.Result;
import com.example.worker.accountAdmin.model.SingleCallback;
import com.example.worker.accountAdmin.model.User;

public class SignUpViewModel extends ViewModel {

    private AccountRepository accountRepository = AccountRepository.getInstance();

    private MutableLiveData<Boolean> signUpComplete = new MutableLiveData<>(false);

    private User user = new User();


    public void setUserAccount(String phoneNumber, String password) {
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);
    }

    public void trySignUp(User user) {

        //회원가입
        accountRepository.trySignUp(user, new SingleCallback<Result<User>>() {
            @Override
            public void onComplete(Result<User> result) {
                if (result instanceof Result.Success) {
                    User SignUser = ((Result.Success<User>) result).getData();
                    signUpComplete.postValue(true);
                } else {
                    String errorMessage = ((Result.Error) result).getError().getMessage();
                    Log.v("sign up view model: ", "회원가입 실패");
                }
            }
        });


    }

    public LiveData<Boolean> getSignUpComplete() {
        return signUpComplete;
    }

    public LiveData<Boolean> getOverlapCheck() {
        return signUpComplete;
    }

    public User getUser() {
        return user;
    }

}