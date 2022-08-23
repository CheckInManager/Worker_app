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
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.worker.R;
import com.example.worker.accountAdmin.viewModel.SignUpViewModel;
import com.example.worker.databinding.FragmentSignupBinding;

public class SignUpFragment extends Fragment {

    private FragmentSignupBinding binding;
    private SignUpViewModel signUpViewModel;
    private NavController navController;

    private Button bt_signUp;
    private EditText et_phoneNumber;
    private EditText et_password;
    private EditText et_confirmPassword;

    private TextView tv_alarmText;

    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        signUpViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
        binding = FragmentSignupBinding.inflate(inflater, container, false);
        navController = NavHostFragment.findNavController(SignUpFragment.this);

        bt_signUp = binding.signUpBtSignUp;
        et_phoneNumber = binding.signUpEtTel;
        et_password = binding.signUpEtPasswrod;
        et_confirmPassword = binding.signUpEtConfirmPassword;
        tv_alarmText = binding.signUpTvAlarmText;

        context = container.getContext();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        bt_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phoneNumber = et_phoneNumber.getText().toString();
                String password = et_password.getText().toString();
                String confirmPassword = et_confirmPassword.getText().toString();



                //blocking edit text - insert to blank
                if(!phoneNumber.equals("") && !password.equals("") && !confirmPassword.equals("")){

                    //blocking edit text - not incorrect password & password confirm
                    if (password.equals(confirmPassword)) {
                        signUpViewModel.setUserAccount(phoneNumber, password);
                        signUpViewModel.trySignUp(signUpViewModel.getUser());
                    } else {
                        tv_alarmText.setText("비밀번호가 동일하지 않습니다.");
                    }
                }
                else if(phoneNumber.equals("")){
                    tv_alarmText.setText("전화번호를 입력해주세요");
                }
                else if(password.equals("")){
                    tv_alarmText.setText("비밀번호를 입력해주세요.");
                }
                else if(confirmPassword.equals("")){
                    tv_alarmText.setText("비밀번호를 다시 입력해주세요.");
                }

            }
        });


        signUpViewModel.getSignUpComplete().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean signUp) {
                if (signUp) {
                    navController.navigate(R.id.action_navigation_signUp_to_navigation_signIn);

                } else {
                    
                }
            }
        });


    }


}
