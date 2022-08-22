package com.example.worker.accountAdmin.model;

import androidx.annotation.NonNull;

public class User
{


    private String phoneNumber;
    private String password;
    private String name;
    private String career;
    private String worksite;
    private boolean accidentHistory =false;
    private boolean picture = false;

    public User()
    {
    }

    public User(String phoneNumber, String password, String name, String career, boolean picture, String worksite, String accidentHistory)
    {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.name = name;
        this.career = career;
        this.worksite = worksite;
        this.accidentHistory = false;
        this.picture =false;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCareer()
    {
        return career;
    }

    public void setCareer(String career)
    {
        this.career = career;
    }

    public boolean getPicture() {
        return picture;
    }

    public void  setPicture(boolean picture) {
        this.picture = picture;
    }

    public String getworksite()
    {
        return worksite;
    }

    public void setworksite(String worksite)
    {
        this.worksite = worksite;
    }

    public boolean getAccidentHistory() {
        return accidentHistory;
    }

    public void setAccidentHistory(boolean accidentHistory) {
        this.accidentHistory = accidentHistory;
    }


    @NonNull
    @Override
    public String toString()
    {
        return "User{ " +
               "phoneNumber=" + phoneNumber + "\"" + "," +
               "name=" + name + "\"" + "," +
               "career=" + career + "\""+
                "worksite=" + worksite
               + "}";

    }
}
