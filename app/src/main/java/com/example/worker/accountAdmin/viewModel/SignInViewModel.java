package com.example.worker.accountAdmin.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.worker.accountAdmin.model.AccountRepository;
import com.example.worker.accountAdmin.model.Result;
import com.example.worker.accountAdmin.model.SingleCallback;
import com.example.worker.accountAdmin.model.User;

public class SignInViewModel extends ViewModel
{
    private AccountRepository accountRepository = AccountRepository.getInstance();

    private MutableLiveData<Boolean> loggedIn = new MutableLiveData<>(false);

    private User user = new User();

    public void trySignIn(String phoneNumber, String password)
    {
        accountRepository.trySignIn(phoneNumber, password, new SingleCallback<Result<User>>()
        {
            @Override
            public void onComplete(Result<User> result)
            {
                if(result instanceof Result.Success)
                {
                    User loggedInUser = ((Result.Success<User>)result).getData();

                    //오류 남
                    setSignUser(loggedInUser);
                    //inputInformationViewModel.sendSignInUser(loggedInUser);

                    loggedIn.postValue(true);
                }
                else
                {
                    String errorMessage = ((Result.Error)result).getError().getMessage();
                }
            }
        });
    }


    public LiveData<Boolean> isLoggedIn()
    {
        return loggedIn;
    }

    public void setSignUser(User user){
        this.user = user;
    }

    public User getSignUser(){
        return user;
    }

}
