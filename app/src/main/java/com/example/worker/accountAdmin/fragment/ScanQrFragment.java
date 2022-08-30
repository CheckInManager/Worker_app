package com.example.worker.accountAdmin.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.worker.R;
import com.example.worker.accountAdmin.viewModel.ScanQrViewModel;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScanQrFragment extends Fragment {
    private ScanQrViewModel scanQrViewModel;

    public ScanQrFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scanQrViewModel = new ViewModelProvider(requireActivity()).get(ScanQrViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scanqr, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        ActivityResultLauncher<Intent> launchScanner = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            IntentResult intentResult = IntentIntegrator.parseActivityResult(result.getResultCode(), result.getData());
                            String content = intentResult.getContents();
                            String format = intentResult.getFormatName();
                            String[] num = content.split("_");
                            String deviceNum = num[1];
                            scanQrViewModel.setCurrDevice(deviceNum);
                            scanQrViewModel.registerWorksite(deviceNum);
                            NavHostFragment.findNavController(ScanQrFragment.this).navigate(R.id.action_navigation_scanQrCode_to_navigation_scannedDeviceDetails);
                        }
                    }
                });
        IntentIntegrator intentIntegrator = IntentIntegrator.forSupportFragment(ScanQrFragment.this);
        intentIntegrator.setPrompt("Scan a barcode or QR code");
        intentIntegrator.setOrientationLocked(true);
        Intent i = intentIntegrator.createScanIntent();
        launchScanner.launch(i);
    }
}