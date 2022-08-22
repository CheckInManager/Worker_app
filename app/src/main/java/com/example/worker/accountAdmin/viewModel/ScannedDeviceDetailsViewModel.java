package com.example.worker.accountAdmin.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ScannedDeviceDetailsViewModel extends ViewModel {

    MutableLiveData<Boolean> loadInformation = new MutableLiveData<>();



    public LiveData<Boolean> getLoadInformation() {
        return loadInformation;
    }
}
