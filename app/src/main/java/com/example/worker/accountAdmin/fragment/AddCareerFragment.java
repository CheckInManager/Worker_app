package com.example.worker.accountAdmin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worker.accountAdmin.viewModel.AddCareerViewModel;
import com.example.worker.accountAdmin.viewModel.AddCareerListItem;
import com.example.worker.databinding.FragmentAddcareerBinding;

import java.util.ArrayList;

public class AddCareerFragment extends Fragment {

    FragmentAddcareerBinding binding;
    AddCareerViewModel addCareerViewModel;

    private AddCareerRecycleViewAdapter addCareerRecycleViewAdapter;
    private RecyclerView rv_careerRecordView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        addCareerViewModel = new ViewModelProvider(this).get(AddCareerViewModel.class);

        binding = FragmentAddcareerBinding.inflate(inflater, container, false);
        rv_careerRecordView = binding.addCareerRvCareerList;


        addCareerRecycleViewAdapter = new AddCareerRecycleViewAdapter((ArrayList<AddCareerListItem>) addCareerViewModel.getCareerList());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        rv_careerRecordView.setAdapter(addCareerRecycleViewAdapter);
        rv_careerRecordView.setLayoutManager(new LinearLayoutManager(requireContext()));
        addCareerRecycleViewAdapter.notifyDataSetChanged();


    }
}
