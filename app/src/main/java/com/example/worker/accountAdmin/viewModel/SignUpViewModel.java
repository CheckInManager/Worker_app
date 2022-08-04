package com.example.worker.accountAdmin.viewModel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.worker.accountAdmin.model.AccountRepository;
import com.example.worker.accountAdmin.model.User;

public class SignUpViewModel extends ViewModel {

    private AccountRepository accountRepository = AccountRepository.getInstance();

    private int phoneNumber;
    private String password;
    private String confirmPassword;

    private boolean phoneNumberOverlap = false;

    private User user = new User(getPhoneNumber(), getPassword());

    public void addUserRecord() {
        user.phoneNumber = getPhoneNumber();
        user.password = getPassword();
        accountRepository.writeAccount(getUser());
    }

    public User getUser() {
        return user;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        try {
            this.phoneNumber = Integer.parseInt(String.valueOf(phoneNumber));
        }
        catch (NumberFormatException e) {
        }

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean getPhoneNumberOverlap() {
        setPhoneNumberOverlap(phoneNumberOverlap);
        Log.v("전화번호 중복 (sign up view model)", "" + phoneNumberOverlap);
        return phoneNumberOverlap;
    }

    public void setPhoneNumberOverlap(boolean phoneNumberOverlap) {
        //this.phoneNumberOverlap = accountRepository.isPhoneNumberOverlap();
        Log.v("이미 등록된 번호 view model", "" + phoneNumberOverlap);
    }
}
