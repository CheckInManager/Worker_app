package com.example.worker.accountAdmin.model;

import android.media.Image;

import androidx.annotation.NonNull;

public class User {

    //release 1
    public int phoneNumber;
    public String password;

    //release 2
    private String name;
    private Image image;
    private String career;

    //release 3
    private String accidentRecord;
    private String memo;

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

    public User(int phoneNumber, String name, Image image, String career, String accidentRecord) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.image = image;
        this.career = career;
        this.accidentRecord = accidentRecord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getAccidentRecord() {
        return accidentRecord;
    }

    public void setAccidentRecord(String accidentRecord) {
        this.accidentRecord = accidentRecord;
    }

    @NonNull
    @Override
    public String toString() {
        return "User{ " +
                "phoneNumber=" + phoneNumber + "\"" + ","+
                "name=" + name + "\"" + ","+
                "image=" + image + "\"" + ","+
                "career=" + career + "\"" + ","+
                "accidentRecord=" + accidentRecord + "\"" + ","
                +"}";

    }
}
