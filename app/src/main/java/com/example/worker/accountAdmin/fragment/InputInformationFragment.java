package com.example.worker.accountAdmin.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.worker.R;
import com.example.worker.accountAdmin.viewModel.InputInformationViewModel;
import com.example.worker.accountAdmin.viewModel.SignInViewModel;
import com.example.worker.databinding.FragmentInputinformationBinding;

public class InputInformationFragment extends Fragment {

    private FragmentInputinformationBinding binding;
    private SignInViewModel signInViewModel;
    private InputInformationViewModel inputInformationViewModel;
    private NavController navController;

    private ImageButton imbBt_face;
    private EditText et_phoneNumber;
    private EditText et_name;
    private EditText et_career;
    private Button bt_complete;

    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inputInformationViewModel = new ViewModelProvider(this).get(InputInformationViewModel.class);

        binding = FragmentInputinformationBinding.inflate(inflater, container, false);
        navController = NavHostFragment.findNavController(InputInformationFragment.this);

        imbBt_face = binding.InputInformationBtPicture;
        et_phoneNumber = binding.InputInformationEtPhoneNumber;
        et_name = binding.InputInformationEtName;
        et_career = binding.InputInformationEtCareer;
        bt_complete = binding.InputInformationBtComplete;

        context = container.getContext();


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        et_phoneNumber.setText(inputInformationViewModel.returnPhoneNumber());

        bt_complete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String name = et_name.getText().toString();
                String career = et_career.getText().toString();

                //sign in viewmodel에서 getsignInUser 호출
                //Log.v("", "" +signInViewModel.getSignInUser().phoneNumber);
                inputInformationViewModel.setUser(name, career);

               //inputInformationViewModel.tryInputted(inputInformationViewModel.getUser());


            }
        });

        inputInformationViewModel.getInputted().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                //navController.navigate(R.id.action_navigation_inputInformation_to_navigation_scanQrCode);
            }
        });
    }


}
