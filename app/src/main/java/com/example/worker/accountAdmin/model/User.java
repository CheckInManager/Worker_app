package com.example.worker.accountAdmin.model;

import androidx.annotation.NonNull;

public class User
{

    //release 1
    private String phoneNumber;
    private String password ;

    //release 2
    private String name;
    private String career;

    //release 3
    private String worksite;

    public User()
    {
    }

    public User(String phoneNumber, String password, String name, String career, String worksite)
    {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.name = name;
        this.career = career;
        this.worksite = worksite;
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
