package com.example.worker.accountAdmin.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import com.example.worker.databinding.FragmentSigninBinding;

public class SignInFragment extends Fragment {

    private FragmentSigninBinding binding;
    private NavController navController;
    private SignInViewModel signInViewModel;

    private EditText et_phoneNumber;
    private EditText et_password;
    private Button bt_signUp;
    private Button bt_signIn;
    private TextView tv_alarmText;

    private Button bt_findPassword;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        signInViewModel = new ViewModelProvider(this).get(SignInViewModel.class);
        binding = FragmentSigninBinding.inflate(inflater, container, false);
        navController = NavHostFragment.findNavController(SignInFragment.this);


        et_phoneNumber = binding.SignInEtPhoneNumber;
        et_password = binding.SignInEtPassword;

        bt_signUp = binding.signInBtSignUp;
        bt_signIn = binding.SignInBtSignIn;
        bt_findPassword = binding.SignInBtFindPassword;

        tv_alarmText = binding.signInTvAlarmText;


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //sign up
        bt_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_navigation_signIn_to_navigation_signUp);
            }
        });

        bt_findPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_navigation_signIn_to_navigation_findPassword);
            }
        });

        //sign in
        bt_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phoneNumber = et_phoneNumber.getText().toString();
                String password = et_password.getText().toString();

                if(phoneNumber.equals("") && password.equals("")){
                    tv_alarmText.setText("핸드폰 번호와 비밀번호를 입력해주세요.");
                }
                else if(phoneNumber.equals("")){
                    tv_alarmText.setText("핸드폰 번호를 입력해주세요.");
                }
                else if(password.equals("")){
                    tv_alarmText.setText("비밀번호를 입력해주세요.");
                }
                else{
                    signInViewModel.trySignIn(phoneNumber, password);
                    }
                }
        });

        signInViewModel.isLoggedIn().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoggedIn) {
                if (isLoggedIn) {
                    //information inputted null 값 정보 확인 후 scan fragment 이동
                    if(signInViewModel.getCurrentUser().getName() == null && !signInViewModel.getCurrentUser().getPicture()){
                        navController.navigate(R.id.action_navigation_signIn_to_navigation_inputInformation);
                    }
                    else{
                        navController.navigate(R.id.action_navigation_signIn_to_navigation_scanQrCode);
                    }


                } else {
                    tv_alarmText.setText(signInViewModel.getErrorMessage());
                }
            }
        });
    }
}
