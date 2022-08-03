package com.example.worker.accountAdmin.viewModel;

import androidx.lifecycle.ViewModel;

import com.example.worker.accountAdmin.model.AccountRepository;
import com.example.worker.accountAdmin.model.SignInRecord;

public class SignInViewModel extends ViewModel {

    private AccountRepository accountRepository = AccountRepository.getInstance();

    private int inputPhoneNumber;
    private String inputPassword;

    SignInRecord signInRecord = new SignInRecord(getInputPhoneNumber(), getInputPassword());

    private void setSignInRecord(SignInRecord signInRecord){
        this.signInRecord = signInRecord;
        this.signInRecord.phoneNumber = inputPhoneNumber;
        this.signInRecord.password = inputPassword;
    }

    private SignInRecord getSignInRecord(SignInRecord signInRecord){
        return signInRecord;
    }

    public void addSignInRecord(){
        setSignInRecord(signInRecord);
        ///accountRepository.checkSignIn(getSignInRecord(signInRecord));
    }

    public int getInputPhoneNumber() {
        return inputPhoneNumber;
    }

    public void setInputPhoneNumber(int inputPhoneNumber) {
        this.inputPhoneNumber = inputPhoneNumber;
    }

    public String getInputPassword() {
        return inputPassword;
    }

    public void setInputPassword(String inputPassword) {
        this.inputPassword = inputPassword;
    }
}
