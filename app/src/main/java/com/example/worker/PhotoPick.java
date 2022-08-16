package com.example.worker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;


public class PhotoPick extends AppCompatActivity {

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    String fileName = "tmpUser"+ "tmpPhone" + ".jpg";

    private static final int SELECTED_PICTURE = 200;
    private ImageButton bt_imagePick;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.galleryAddPic();


        finish();


    }

    private void galleryAddPic() {
        //get gallery image
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECTED_PICTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_OK && requestCode == SELECTED_PICTURE) {
            Uri selectedImageUri = data.getData();

            if (null != selectedImageUri) {
                bt_imagePick.setImageURI(selectedImageUri);
            }


        }
    }



}

