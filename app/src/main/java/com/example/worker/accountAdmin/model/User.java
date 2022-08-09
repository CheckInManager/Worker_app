package com.example.worker.accountAdmin.model;

import androidx.annotation.NonNull;

public class User
{

    //release 1
    public String phoneNumber;
    public String password;

    //release 2
    private String name;
    private String imageUrl;
    private String career;

    //release 3
    private String accidentRecord;
    private String memo;

    public User()
    {
    }

    public User(String phoneNumber, String password)
    {
        this.phoneNumber = phoneNumber;
        this.password = password;
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

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public String getCareer()
    {
        return career;
    }

    public void setCareer(String career)
    {
        this.career = career;
    }

    public String getAccidentRecord()
    {
        return accidentRecord;
    }

    public void setAccidentRecord(String accidentRecord)
    {
        this.accidentRecord = accidentRecord;
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
               "imageUrl=" + imageUrl + "\"" + "," +
               "career=" + career + "\"" + "," +
               "accidentRecord=" + accidentRecord + "\"" + ","
               + "}";

    }
}
