package com.example.worker.accountAdmin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.worker.accountAdmin.viewModel.FindPasswordViewModel;
import com.example.worker.accountAdmin.viewModel.ReSettingPasswordViewModel;
import com.example.worker.databinding.FragmentResettingpasswordBinding;


public class ReSettingPasswordFragment extends Fragment {


    private ReSettingPasswordViewModel reSettingPasswordViewModel;
    private FragmentResettingpasswordBinding binding;
    private NavController navController;

    private EditText et_password;
    private EditText et_confirmPassword;
    private Button bt_confirm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentResettingpasswordBinding.inflate(inflater, container, false);
        navController = NavHostFragment.findNavController(ReSettingPasswordFragment.this);
        reSettingPasswordViewModel = new ViewModelProvider(this).get(ReSettingPasswordViewModel.class);

        et_password = binding.reSettingPasswordEtPassword;
        et_confirmPassword = binding.reSettingPasswordEtConfirmpassword;


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        reSettingPasswordViewModel.updatePassword(reSettingPasswordViewModel.getCurrUser());

        //버튼 클릭시 -> framelayout으로 넣어서 findpasswordfragment에 있는 버튼을 써야함
        String password = et_password.getText().toString();
        String confirmPassword = et_password.getText().toString();




    }
}
