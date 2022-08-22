package com.example.worker.accountAdmin.viewModel;

import android.graphics.Bitmap;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.worker.accountAdmin.model.AccountRepository;
import com.example.worker.accountAdmin.model.Result;
import com.example.worker.accountAdmin.model.SingleCallback;
import com.example.worker.accountAdmin.model.User;

import java.util.ArrayList;
import java.util.List;
public class InputInformationViewModel extends ViewModel {

    private AccountRepository accountRepository = AccountRepository.getInstance();

    private MutableLiveData<Boolean> updateSuccessful = new MutableLiveData<>(false);
    private MutableLiveData<Boolean> updateTmpCareer = new MutableLiveData<>(false);

    //user information
    private User user = accountRepository.getCurrUser();
    private Bitmap currUserBitmap;

    //career
    private AddCareerListItem addCareerItem;

    private List<AddCareerListItem> careerListItems = new ArrayList<>();



    //user information input
    public void updateUserInformation(String name, List<AddCareerListItem> addCareerListItems) {

        String[] array = new String[addCareerListItems.size()];
        String tmpCareer ="";
        for(int i =0; i<addCareerListItems.size(); i++){
            array[i] = String.valueOf(addCareerListItems.get(i).getCareer());
        }

        for(int i =0; i < array.length; i++){
            if(i == array.length-1){
                tmpCareer += array[i].trim();
            }
            else{
                tmpCareer += (array[i].trim()+", ");
            }

        }

        user.setCareer(tmpCareer);
        user.setName(name);
        accountRepository.addUserInformation(user, new SingleCallback<Result<User>>() {
                    @Override
                    public void onComplete(Result<User> result) {
                        if(result instanceof Result.Success){
                            if(currUserBitmap != null)
                            {
                                accountRepository.uploadUserImage(user.getPhoneNumber(), currUserBitmap);
                            }
                            user = ((Result.Success<User>)result).getData();
                            updateSuccessful.postValue(true);
                        }
                        else{
                            Log.v("InputInformationViewModel", " : 오류");
                        }
                    }
                }
        );
    }

    public User getCurrUser() {
        return user;
    }

    public void setCurrUserBitmap(Bitmap bm)
    {
        currUserBitmap = bm;
    }

    public LiveData<Boolean> isUpdateSuccessful() {
        return updateSuccessful;
    }

    //career
    public List<AddCareerListItem> addCareerList(String career) {
        addCareerItem = new AddCareerListItem(career);
        careerListItems.add(addCareerItem);

        //recycle view 용 item 삽입..
        setCareerListItems(addCareerItem);

        return careerListItems;
    }

    public void setCareerListItems(AddCareerListItem item){
        accountRepository.setCareerRecords(item);
    }

    public List<AddCareerListItem> getCareerListItems() {
        return careerListItems;
    }

}
