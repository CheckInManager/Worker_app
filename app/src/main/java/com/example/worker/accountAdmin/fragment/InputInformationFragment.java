package com.example.worker.accountAdmin.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worker.R;
import com.example.worker.accountAdmin.model.User;
import com.example.worker.accountAdmin.viewModel.AddCareerListItem;
import com.example.worker.accountAdmin.viewModel.InputInformationViewModel;
import com.example.worker.databinding.FragmentInputinformationBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class InputInformationFragment extends Fragment {

    private FragmentInputinformationBinding binding;
    private InputInformationViewModel inputInformationViewModel;
    private NavController navController;
    private AddCareerRecycleViewAdapter addCareerRecycleViewAdapter;

    private EditText et_phoneNumber;
    private EditText et_name;
    private EditText et_career;
    private ImageButton bt_img;
    private Button bt_complete;
    private Button bt_addCareer;



    private ArrayList<AddCareerListItem> careerList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inputInformationViewModel = new ViewModelProvider(this).get(InputInformationViewModel.class);

        binding = FragmentInputinformationBinding.inflate(inflater, container, false);
        navController = NavHostFragment.findNavController(InputInformationFragment.this);

        bt_img = binding.InputInformationBtPicture;
        et_phoneNumber = binding.InputInformationEtPhoneNumber;
        et_name = binding.InputInformationEtName;
        et_career = binding.InputInformationEtCareer;
        bt_complete = binding.InputInformationBtComplete;
        bt_addCareer = binding.inputInformationBtAddCareer;



        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        User currUser = inputInformationViewModel.getCurrUser();
        et_phoneNumber.setText(currUser.getPhoneNumber());


        ActivityResultLauncher<Intent> launchGallery = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>()
                {
                    @Override
                    public void onActivityResult(ActivityResult result)
                    {
                        if(result.getResultCode() == Activity.RESULT_OK)
                        {
                            Uri selectedImage = result.getData().getData();
                            bt_img.setImageURI(selectedImage);
                            inputInformationViewModel.setCurrUserBitmap(((BitmapDrawable)bt_img.getDrawable()).getBitmap());
                            bt_img.invalidate();
                        }
                    }
                });

        //add picture
        bt_img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                launchGallery.launch(intent);
            }
        });

        //add career
        bt_addCareer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputInformationViewModel.addCareerList((et_career.getText()).toString());
                et_career.setText(" ");

                FragmentTransaction fragmentTransaction =  getChildFragmentManager().beginTransaction();
                AddCareerFragment addCareerFragment = new AddCareerFragment();
                fragmentTransaction.replace(R.id.inputInformation_frameLayout , addCareerFragment).commit();
            }
        });

        //complete
        bt_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = et_name.getText().toString();
                String career = et_career.getText().toString();

                inputInformationViewModel.updateUserInformation(name, inputInformationViewModel.getCareerListItems());
                navController.navigate(R.id.action_navigation_inputInformation_to_navigation_scanQrCode);
            }
        });

        inputInformationViewModel.isUpdateSuccessful().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

            }
        });
    }
///
}

