package com.example.worker.accountAdmin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.worker.R;
import com.example.worker.databinding.FragmentSignupBinding;

public class SignUpFragment extends Fragment {

    private FragmentSignupBinding binding;
    private NavController navController;
    private Button bt_signUp;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       binding = FragmentSignupBinding.inflate(inflater, container, false);
       return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        navController = NavHostFragment.findNavController(SignUpFragment.this);
        bt_signUp = binding.signUpBtSignUp;
        bt_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //전화번호, 비밀번호 입력

                //데이터베이서에서 중복 확인 후 페이지 이동

                navController.navigate(R.id.action_navigation_signUp_to_navigation_logIn);
            }

        });
    }


}
