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
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.worker.R;
import com.example.worker.databinding.FragmentSigninBinding;

public class SignInFragment extends Fragment {

    private FragmentSigninBinding binding;
    private NavController navController;
    private Button bt_signUp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentSigninBinding.inflate(inflater,container, false);

        bt_signUp = binding.LogInBtLogIn;

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        navController = NavHostFragment.findNavController(SignInFragment.this);
        bt_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_navigation_logIn_to_navigation_signUp);
            }
        });

    }
}
