package com.example.worker.accountAdmin.model;

import android.media.Image;

public class User {

    public int phoneNumber;
    public String password;

    public User(int phoneNumber, String password) {
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
