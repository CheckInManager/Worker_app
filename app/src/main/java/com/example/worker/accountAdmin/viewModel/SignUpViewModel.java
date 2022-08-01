package com.example.worker.accountAdmin.viewModel;

import androidx.lifecycle.ViewModel;
public class SignUpViewModel extends ViewModel {
    private int phoneNumber;
    private String password;

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
