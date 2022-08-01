package com.example.worker.accountAdmin.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AccountRepository {

    private DatabaseReference accountDatabase = FirebaseDatabase.getInstance().getReference();
    DatabaseReference phone = accountDatabase.child("phoneNumber");
    DatabaseReference password = accountDatabase.child("passwrod");

    private int inputPhoneNumber;
    private String inputPassword;

    private static final AccountRepository INSTANCE = new AccountRepository();

    private static AccountRepository getInstance() { return INSTANCE; }

    private AccountRepository() {
        accountDatabase = FirebaseDatabase.getInstance().getReference();
    }

    //firebase








}
