package com.example.worker.accountAdmin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.worker.R;
import com.example.worker.accountAdmin.viewModel.FindPasswordViewModel;
import com.example.worker.databinding.FragmentFindpasswordBinding;

public class FindPasswordFragment extends Fragment {

    private FragmentFindpasswordBinding binding;
    private NavController navController;
    private FindPasswordViewModel findPasswordViewModel;

    private EditText et_phoneNumber;
    private Button bt_confirm;
    private FrameLayout frameLayout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        findPasswordViewModel = new ViewModelProvider(this).get(FindPasswordViewModel.class);
        binding = FragmentFindpasswordBinding.inflate(inflater, container, false);
        navController = NavHostFragment.findNavController(FindPasswordFragment.this);

        et_phoneNumber = binding.findPasswordEtPhoneNumber;
        bt_confirm = binding.findPasswordBtConfirm;
        frameLayout = binding.findPasswordFrameLayout;


        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = et_phoneNumber.getText().toString();
                findPasswordViewModel.tryFindPhoneNumber(phoneNumber);


                findPasswordViewModel.getConfirm().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean found) {
                        if(found){
                            FragmentTransaction fragmentTransaction =  getChildFragmentManager().beginTransaction();
                            ReSettingPasswordFragment reSettingPasswordFragment = new  ReSettingPasswordFragment();
                            fragmentTransaction.replace(R.id.findPassword_frameLayout , reSettingPasswordFragment).commit();
                        }
                    }
                });

            }
        });



    }

}
