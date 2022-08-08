package com.example.worker.accountAdmin.viewModel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.worker.accountAdmin.model.AccountRepository;
import com.example.worker.accountAdmin.model.SignInRecord;

public class SignInViewModel extends ViewModel {

    private AccountRepository accountRepository = AccountRepository.getInstance();

    private boolean checkingSignIn = false;
    private int inputPhoneNumber;
    private String inputPassword;

    SignInRecord signInRecord = new SignInRecord(getInputPhoneNumber(), getInputPassword());

    public void addSignInRecord() {
        this.signInRecord.phoneNumber = getInputPhoneNumber();
        this.signInRecord.password = getInputPassword();
    }

    public SignInRecord getSignInRecord() {
        return signInRecord;
    }

    public int getInputPhoneNumber() {
        return inputPhoneNumber;
    }

    public void setInputPhoneNumber(int inputPhoneNumber) {
        this.inputPhoneNumber = inputPhoneNumber;
    }

    public String getInputPassword() {return inputPassword;}

    public void setInputPassword(String inputPassword) {
        this.inputPassword = inputPassword;
    }

    public void setSingleRecord() {
        accountRepository.setSignInRecord(getSignInRecord());
    }

    public void setCheckingSingIn(){
        this.checkingSignIn =  accountRepository.getCheckSignIn();
    }

    public boolean getCheckingSignIn(){
        setCheckingSingIn();
        return checkingSignIn;
    }



}
