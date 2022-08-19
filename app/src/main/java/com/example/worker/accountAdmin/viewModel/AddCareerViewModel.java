package com.example.worker.accountAdmin.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.worker.accountAdmin.model.AccountRepository;
import com.example.worker.accountAdmin.model.Result;
import com.example.worker.accountAdmin.model.SingleCallback;

public class AddCareerViewModel extends ViewModel {

    MutableLiveData<Boolean> updateTmpCareer = new MutableLiveData<>(false);
    private AccountRepository accountRepository = AccountRepository.getInstance();

    public void getTmpCareerItem(){
        accountRepository.getCareerListItems(new SingleCallback<Result<AddCareerListItem>>() {
            @Override
            public void onComplete(Result<AddCareerListItem> result) {

                if(result instanceof Result.Success){
                    AddCareerListItem addCareerListItem = ((Result.Success<AddCareerListItem>)result).getData();
                    updateTmpCareer.postValue(true);

                }
            }
        });
    }



    public MutableLiveData<Boolean> getUpdateTmpCareer() {
        return updateTmpCareer;
    }
}
