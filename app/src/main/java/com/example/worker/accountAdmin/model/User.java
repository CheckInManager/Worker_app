package com.example.worker.accountAdmin.model;

public class User {

    public int phoneNumber;
    private int PhoneNumber;
    public String password;
    private String Password;

    public User(int phoneNumber, String password) {
        PhoneNumber = phoneNumber;
        Password = password;
    }

    public int getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
