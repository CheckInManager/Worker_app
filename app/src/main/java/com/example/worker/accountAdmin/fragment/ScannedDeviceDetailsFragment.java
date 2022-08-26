package com.example.worker.accountAdmin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worker.R;
import com.example.worker.accountAdmin.viewModel.ScannedDeviceDetailsViewModel;
import com.example.worker.databinding.FragmentScanneddevicedetailsBinding;

public class ScannedDeviceDetailsFragment extends Fragment {
    private FragmentScanneddevicedetailsBinding binding;
    private ScannedDeviceDetailsViewModel scannedDeviceDetailsViewModel;
    private NavController navController;

    private FrameLayout frameLayout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     scannedDeviceDetailsViewModel = new ViewModelProvider(this).get(ScannedDeviceDetailsViewModel.class);
     binding = FragmentScanneddevicedetailsBinding.inflate(inflater, container, false);
     navController = NavHostFragment.findNavController(ScannedDeviceDetailsFragment.this);

     frameLayout = binding.scannedDeviiceDetailsFramelayout;


        return binding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        frameLayout.setVisibility(View.VISIBLE) ;


        FragmentTransaction fragmentTransaction =  getChildFragmentManager().beginTransaction();
        NoticeFragment noticeFragment = new NoticeFragment();
        fragmentTransaction.replace(R.id.scannedDeviiceDetails_framelayout , noticeFragment).commit();


    }
}
