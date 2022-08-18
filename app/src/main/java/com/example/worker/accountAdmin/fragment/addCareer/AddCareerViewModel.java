package com.example.worker.accountAdmin.fragment.addCareer;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.worker.accountAdmin.model.AccountRepository;

import java.util.List;

public class AddCareerViewModel extends ViewModel {

    private AccountRepository accountRepository = AccountRepository.getInstance();

    public List<AddCareerListItem> getRecordList() {
        return accountRepository.getCareerListItems();
    }
}
