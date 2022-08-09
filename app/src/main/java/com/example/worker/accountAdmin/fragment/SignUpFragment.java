package com.example.worker.accountAdmin.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.worker.R;
import com.example.worker.accountAdmin.model.User;
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

    public boolean check = false;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        signUpViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
        binding = FragmentSignupBinding.inflate(inflater, container, false);

        bt_signUp = binding.signUpBtSignUp;
        et_phoneNumber = binding.signUpEtTel;
        et_password = binding.signUpEtPasswrod;
        et_confirmPassword = binding.signUpEtConfirmPassword;

        context = container.getContext();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(SignUpFragment.this);

//        check = signUpViewModel.getPhoneNumberOverlap();
//        bt_signUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(check == false) {
//                    //전화번호, 비밀번호 입력
//                    signUpViewModel.setPhoneNumber(et_phoneNumber.getText().toString());
//                    signUpViewModel.setPassword(et_password.getText().toString());
//                    signUpViewModel.setConfirmPassword(et_confirmPassword.getText().toString());
//
//                    signUpViewModel.addUserRecord();
//
//                    navController.navigate(R.id.action_navigation_signUp_to_navigation_logIn);
//                }
//                else{
//                    Toast.makeText(context, "이미 등록된 전화번호입니다.", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//        });
    }


}
