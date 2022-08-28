package com.example.worker.accountAdmin.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.worker.accountAdmin.model.AccountRepository;
import com.example.worker.accountAdmin.model.Result;
import com.example.worker.accountAdmin.model.SingleCallback;

import java.util.List;

public class AddCareerViewModel extends ViewModel {

    private AccountRepository accountRepository = AccountRepository.getInstance();

    public List<AddCareerListItem> getCareerList(){
        return accountRepository.getCareerList();
    }
}
