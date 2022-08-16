package com.example.worker;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.Nullable;


public class PhotoPick extends Activity {


    private static final int SELECTED_PICTURE = 200;
    private ImageButton bt_imagePick;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.galleryAddPic();


        finish();


    }

    private void galleryAddPic(){
        //get gallery image
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECTED_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_OK && requestCode == SELECTED_PICTURE){
                Uri selectedImageUri = data.getData();
                if(null != selectedImageUri){
                    bt_imagePick.setImageURI(selectedImageUri);
                }


        }
    }


}

