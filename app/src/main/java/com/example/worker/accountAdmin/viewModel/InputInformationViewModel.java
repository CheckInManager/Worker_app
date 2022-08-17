package com.example.worker.accountAdmin.viewModel;

import android.graphics.Bitmap;
import android.text.Editable;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.worker.accountAdmin.fragment.addCareer.addCareerListItem;
import com.example.worker.accountAdmin.model.AccountRepository;
import com.example.worker.accountAdmin.model.Result;
import com.example.worker.accountAdmin.model.SingleCallback;
import com.example.worker.accountAdmin.model.User;

import java.util.ArrayList;
import java.util.List;

public class InputInformationViewModel extends ViewModel {

    private AccountRepository accountRepository = AccountRepository.getInstance();

    private MutableLiveData<Boolean> updateSuccessful = new MutableLiveData<>(false);
    private User user = accountRepository.getCurrUser();
    private Bitmap currUserBitmap;

    addCareerListItem addCareerItem;

    private List<addCareerListItem> careerListItems = new ArrayList<>();

    public List<addCareerListItem> addCareerList(String career) {
        addCareerItem = new addCareerListItem(career);
        careerListItems.add(addCareerItem);

        //recycle view 용 item 삽입..
        setCareerListItems(careerListItems);

        return careerListItems;
    }

    public void setCareerListItems(List<addCareerListItem> item){
        accountRepository.setCareerRecords(item);
    }

    public List<addCareerListItem> getCareerListItems() {
        return careerListItems;
    }

    //user information 입력
    public void updateUserInformation(String name, List<addCareerListItem> addCareerListItems) {

        String[] array = new String[addCareerListItems.size()];
        String tmpCareer ="";
        for(int i =0; i<addCareerListItems.size(); i++){
            array[i] = String.valueOf(addCareerListItems.get(i).getCareer());
        }

        for(int i =0; i < array.length; i++){
            Log.v("", "" + array[i]);
            if(i == array.length-1){
                tmpCareer += array[i];
            }
            else{
                tmpCareer += (array[i]+", ");
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

}
