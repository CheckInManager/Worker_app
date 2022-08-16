package com.example.worker.accountAdmin.viewModel;

import android.graphics.Bitmap;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.worker.accountAdmin.model.AccountRepository;
import com.example.worker.accountAdmin.model.Result;
import com.example.worker.accountAdmin.model.SingleCallback;
import com.example.worker.accountAdmin.model.User;

public class InputInformationViewModel extends ViewModel {

    private AccountRepository accountRepository = AccountRepository.getInstance();

    private MutableLiveData<Boolean> updateSuccessful = new MutableLiveData<>(false);
    private User user = accountRepository.getCurrUser();
    private Bitmap currUserBitmap;

    //user information 입력
    public void updateUserInformation(String name, String career) {
        user.setName(name);
        user.setCareer(career);
        accountRepository.addUserInformation(user, new SingleCallback<Result<User>>() {
                    @Override
                    public void onComplete(Result<User> result) {
                        if(result instanceof Result.Success){
                            if(currUserBitmap != null)
                            {
                                accountRepository.uploadUserImage(user.getPhoneNumber(), currUserBitmap);
                            }
                            user = ((Result.Success<User>)result).getData();
                            updateSuccessful.postValue(true);
                        }
                        else{
                            Log.v("InputInformationViewModel", " : 오류");
                        }
                    }
                }
        );
    }

    public User getCurrUser() {
        return user;
    }

    public void setCurrUserBitmap(Bitmap bm)
    {
        currUserBitmap = bm;
    }

    public LiveData<Boolean> isUpdateSuccessful() {
        return updateSuccessful;
    }

}
