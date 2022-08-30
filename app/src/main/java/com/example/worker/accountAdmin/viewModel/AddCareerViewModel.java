package com.example.worker.accountAdmin.viewModel;

import androidx.lifecycle.ViewModel;

import com.example.worker.accountAdmin.repository.AccountRepository;

import java.util.List;

public class AddCareerViewModel extends ViewModel {

    private AccountRepository accountRepository = AccountRepository.getInstance();

    public List<AddCareerListItem> getCareerList() {
        return accountRepository.getCareerList();
    }
}
