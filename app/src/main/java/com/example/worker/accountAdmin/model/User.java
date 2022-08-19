package com.example.worker.accountAdmin.model;

import androidx.annotation.NonNull;

public class User
{


    private String phoneNumber;
    private String password ;
    private String name;
    private String career;
    private String worksite;
    private String accidentHistory;
    private boolean userCheck =false;

    public User()
    {
    }

    public User(String phoneNumber, String password, String name, String career, String worksite, String accidentHistory)
    {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.name = name;
        this.career = career;
        this.worksite = worksite;
        this.accidentHistory = accidentHistory;
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

    public String getworksite()
    {
        return worksite;
    }

    public void setworksite(String worksite)
    {
        this.worksite = worksite;
    }

    public String getAccidentHistory() {
        return accidentHistory;
    }

    public void setAccidentHistory(String accidentHistory) {
        this.accidentHistory = accidentHistory;
    }

    public boolean isUserCheck() {return userCheck;}

    public void setUserCheck(boolean userCheck) {this.userCheck = userCheck;}

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
