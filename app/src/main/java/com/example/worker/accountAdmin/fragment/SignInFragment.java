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
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.worker.R;
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
    private Button bt_findPassword;

    private Context context;
    private boolean signInSuccess = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        signInViewModel = new ViewModelProvider(this).get(SignInViewModel.class);
        binding = FragmentSigninBinding.inflate(inflater,container, false);

        et_phoneNumber = binding.SignInEtPhoneNumber;
        et_password = binding.SignInEtPasswrod;

        bt_signUp = binding.SignInBtSignUp;
        bt_signIn = binding.SignInBtSignIn;
        bt_findPassword = binding.SignInBtFindPassword;

        context = container.getContext();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        navController = NavHostFragment.findNavController(SignInFragment.this);
        //sign up
        bt_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_navigation_logIn_to_navigation_signUp);
            }
        });

        //sign in
        bt_signIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                signInViewModel.setInputPhoneNumber(Integer.parseInt(et_phoneNumber.getText().toString()));
                signInViewModel.setInputPassword(String.valueOf(et_password));
                signInViewModel.addSignInRecord();

                //Success sign in
                if(true){
                    navController.navigate(R.id.action_navigation_logIn_to_navigation_inputInformation);
                }
                //Failed sign in
                else{
                    Toast.makeText(context, "전화번호가 등록되어 있지 않거나 비밀번호가 맞지 않습니다.", Toast.LENGTH_LONG).show();
                }

            }
        });


        //find password
    }
}
