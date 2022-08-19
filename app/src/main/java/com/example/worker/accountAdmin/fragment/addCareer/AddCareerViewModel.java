package com.example.worker.accountAdmin.fragment.addCareer;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.worker.accountAdmin.model.AccountRepository;
import com.example.worker.accountAdmin.model.Result;
import com.example.worker.accountAdmin.model.SingleCallback;

import java.util.List;

public class AddCareerViewModel extends ViewModel {

    private AccountRepository accountRepository = AccountRepository.getInstance();

    private MutableLiveData<Boolean> inputCareerCheck = new MutableLiveData<>(false);

    private AddCareerListItem addCareerListItem = new AddCareerListItem();

    public List<AddCareerListItem> getRecordList() {
        return accountRepository.getTest();
    }

    public List<AddCareerListItem> getData(){
        return accountRepository.getCareerListItems(new SingleCallback<Result<AddCareerListItem>>() {
            @Override
            public void onComplete(Result<AddCareerListItem> result) {
                if (result instanceof Result.Success) {
                    AddCareerListItem careerItem = ((Result.Success<AddCareerListItem>)result).getData();
                    inputCareerCheck.postValue(true);
                    addCareerListItem = careerItem;
                }
                else{
                    Log.v("","Add Career View Model error");}
            }
        });
    }

    public LiveData<Boolean> careerCheck(){
        return inputCareerCheck;
    }

    public AddCareerListItem getAddCareerListItem() {
        return addCareerListItem;
    }
}
