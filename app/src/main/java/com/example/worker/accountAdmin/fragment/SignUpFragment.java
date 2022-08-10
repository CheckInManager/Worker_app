package com.example.worker.accountAdmin.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
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
        navController = NavHostFragment.findNavController(SignUpFragment.this);

        bt_signUp = binding.signUpBtSignUp;
        et_phoneNumber = binding.signUpEtTel;
        et_password = binding.signUpEtPasswrod;
        et_confirmPassword = binding.signUpEtConfirmPassword;

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

                if(password.equals(confirmPassword)){
                    signUpViewModel.trySignUp(phoneNumber, password);
                }
                else{
                    Log.v("signup fragment ", "비밀번호가 동일하지 않습니다.");
                    //Toast.makeText(context, "비밀번호가 동일하지 않습니다. ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signUpViewModel.getSignUpComplete().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean signUp) {
                if(signUp){
                    navController.navigate(R.id.action_navigation_signUp_to_navigation_signIn);
                }
                else
                {

                }
            }
        });


    }


}
