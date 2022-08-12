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
    private SignInViewModel signInViewModel= new SignInViewModel();

    private User user = new User();
    //user information 입력
    public void tryInputted(User user) {

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

    public String returnPhoneNumber(){
        //get loggedIn User
        Log.v("returnPhoneNumber", " : " + signInViewModel.getSignInUser().getPhoneNumber());
        user = signInViewModel.getSignInUser();
        return user.phoneNumber;
    }

    public void setUser(String name, String career){
        //this.user = signInViewModel.getSignInUser();
       // Log.v("ignInViewModel.getS", " : 오류" + user.getPhoneNumber());
        this.user.name = name;
        this.user.career = career;

    }

    public User getUser() {
        return user;
    }

    public LiveData<Boolean> getInputted() {
        return inputted;
    }

}
