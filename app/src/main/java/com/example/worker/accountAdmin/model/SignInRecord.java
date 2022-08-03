package com.example.worker.accountAdmin.model;

public class SignInRecord {

    public int phoneNumber;
    public String password;

    public SignInRecord(int phoneNumber, String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

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
