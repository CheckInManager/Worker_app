package com.example.worker.accountAdmin.model;

import androidx.annotation.NonNull;

public class User
{

    //release 1
    public String phoneNumber;
    public String password ;

    //release 2
    public String name;
    public String career;

    //release 3
    private String memo;

    public User()
    {
    }

    public User(String phoneNumber, String password, String name, String career)
    {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.name = name;
        this.career = career;
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

    public String getMemo()
    {
        return memo;
    }

    public void setMemo(String memo)
    {
        this.memo = memo;
    }


    @NonNull
    @Override
    public String toString()
    {
        return "User{ " +
               "phoneNumber=" + phoneNumber + "\"" + "," +
               "name=" + name + "\"" + "," +
               "career=" + career + "\""
               + "}";

    }
}
