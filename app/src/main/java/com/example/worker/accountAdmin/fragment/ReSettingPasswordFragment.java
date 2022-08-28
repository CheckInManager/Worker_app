package com.example.worker.accountAdmin.fragment;

import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.worker.R;
import com.example.worker.accountAdmin.viewModel.ReSettingPasswordViewModel;
import com.example.worker.databinding.FragmentResettingpasswordBinding;

import org.w3c.dom.Text;

import java.util.Objects;
import java.util.zip.Inflater;


public class ReSettingPasswordFragment extends Fragment {


    private ReSettingPasswordViewModel reSettingPasswordViewModel;
    private FragmentResettingpasswordBinding binding;
    private NavController navController;

    private TextView tv_alarm;
    private EditText et_password;
    private EditText et_confirmPassword;
    private Button bt_confirm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentResettingpasswordBinding.inflate(inflater, container, false);
        navController = NavHostFragment.findNavController(ReSettingPasswordFragment.this);
        reSettingPasswordViewModel = new ViewModelProvider(this).get(ReSettingPasswordViewModel.class);


        tv_alarm = binding.reSettingPasswordTvAlarm;
        et_password = binding.reSettingPasswordEtPassword;
        et_confirmPassword = binding.reSettingPasswordEtConfirmpassword;
        bt_confirm = binding.reSettingPasswordBtConfirm;


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        bt_confirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String password = et_password.getText().toString();
                String confirmPassword = et_confirmPassword.getText().toString();

                if(password.equals(confirmPassword)){
                    tv_alarm.setText("");
                    reSettingPasswordViewModel.updatePassword(reSettingPasswordViewModel.getCurrUser(), password);

                    //changed password
                    reSettingPasswordViewModel.getPasswordUpdate().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
                        @Override
                        public void onChanged(Boolean updatePassword) {
                            if(updatePassword){
                                navController.navigate(R.id.navigation_signIn);
                            }
                            else{
                                tv_alarm.setText("");
                            }
                        }
                    });
                }
               else{
                    tv_alarm.setText("비밀번호가 일치하지 않습니다.");

                    }

             }
        });
    }
}
