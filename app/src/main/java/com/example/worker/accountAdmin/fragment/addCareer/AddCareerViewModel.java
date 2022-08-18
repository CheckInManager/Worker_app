package com.example.worker.accountAdmin.fragment.addCareer;

import androidx.lifecycle.ViewModel;

import com.example.worker.accountAdmin.model.AccountRepository;

import java.util.List;

//view model 따로 만들기 vs 원래 있던 inputInformation viewmodel 쓰기
public class AddCareerViewModel extends ViewModel {

    private AccountRepository accountRepository = AccountRepository.getInstance();

    public List<AddCareerListItem> getRecordList() {
        return accountRepository.getCareerListItems();
    }
}
