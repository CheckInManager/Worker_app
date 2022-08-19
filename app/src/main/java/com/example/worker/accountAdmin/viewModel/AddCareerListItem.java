package com.example.worker.accountAdmin.viewModel;

public class AddCareerListItem  {
    private String career;

    public AddCareerListItem(String career){
        this.career = career;
    }

    public AddCareerListItem() {

    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

}
