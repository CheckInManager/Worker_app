package com.example.worker.accountAdmin.viewModel;

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

    private MutableLiveData<Boolean> inputted = new MutableLiveData<>(false);

    //sign up view model 에 있는 user 랑 똑갍이 연결해야함.. .
//    private User user = new User();
    User user;

    public void setUserAccount(String name, String career) {
        user.name = "name";
        user.career = "career";
    }

    //user information 입력
    public void tryInputted(String name, String career) {
        setUserAccount(name, career);
        Log.v("InputInformationViewModel", " : 확인" + name);
        Log.v("InputInformationViewModel", " : 확인" + career);
        accountRepository.addUserInformation(user, new SingleCallback<Result<User>>() {
                    @Override
                    public void onComplete(Result<User> result) {
                        if(result instanceof Result.Success){
                            User inputtedInfoUser = ((Result.Success<User>)result).getData();
                            inputted.postValue(true);
                        }
                        else{
                            Log.v("InputInformationViewModel", " : 오류");
                        }
                    }
                }
        );

    }

    public LiveData<Boolean> getInputted() {
        return inputted;
    }


}
