package com.example.worker.accountAdmin.viewModel;

import androidx.lifecycle.ViewModel;

import com.example.worker.accountAdmin.model.AccountRepository;
import com.example.worker.accountAdmin.model.User;

public class SignUpViewModel extends ViewModel {

    private AccountRepository accountRepository = AccountRepository.getInstance();


    private int phoneNumber;
    private String password;
    private String confirmPassword;

    private User user = new User(getPhoneNumber(), getPassword());

    public void addUserRecord(){
        accountRepository.writeAccountMap(getUser());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = Integer.parseInt(String.valueOf(phoneNumber));
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
}
