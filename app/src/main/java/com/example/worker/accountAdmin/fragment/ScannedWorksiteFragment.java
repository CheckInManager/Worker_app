package com.example.worker.accountAdmin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.worker.R;
import com.example.worker.databinding.FragmentScannedworksiteBinding;

public class ScannedWorksiteFragment extends Fragment {
    private FragmentScannedworksiteBinding binding;
    private NavController navController;

    private FrameLayout frameLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentScannedworksiteBinding.inflate(inflater, container, false);
        navController = NavHostFragment.findNavController(ScannedWorksiteFragment.this);

        frameLayout = binding.scannedDeviiceDetailsFramelayout;

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        frameLayout.setVisibility(View.VISIBLE);

        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        NoticeFragment noticeFragment = new NoticeFragment();
        fragmentTransaction.replace(R.id.scannedDeviiceDetails_framelayout, noticeFragment).commit();
    }
}
