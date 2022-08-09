package com.example.worker.accountAdmin.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.worker.R;
import com.example.worker.accountAdmin.viewModel.InputInformationViewModel;
import com.example.worker.databinding.FragmentInputinformationBinding;

public class InputInformationFragment extends Fragment {

    private FragmentInputinformationBinding binding;
    private InputInformationViewModel inputInformationViewModel;
    private NavController navController;

    private ImageButton imbBt_face;
    private EditText et_name;
    private EditText et_career;
    private Button bt_complete;

    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inputInformationViewModel = new ViewModelProvider(this).get(InputInformationViewModel.class);
        binding = FragmentInputinformationBinding.inflate(inflater, container, false);
        navController = NavHostFragment.findNavController(InputInformationFragment.this);

        imbBt_face = binding.InputInformationBtPicture;
        et_name = binding.InputInformationEtName;
        et_career = binding.InputInformationEtCareer;
        bt_complete = binding.InputInformationBtComplete;

        context = container.getContext();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        bt_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imbBt_face != null && et_name != null){
                    Toast.makeText(context, "이름 또는 전화번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                navController.navigate(R.id.action_navigation_inputInformation_to_navigation_scanQrCode);
            }
        });
    }
}
