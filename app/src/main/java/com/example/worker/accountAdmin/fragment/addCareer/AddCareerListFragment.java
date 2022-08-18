package com.example.worker.accountAdmin.fragment.addCareer;

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

import com.example.worker.databinding.FragmentAddcareerBinding;

public class AddCareerListFragment extends Fragment {

    private FragmentAddcareerBinding binding;
    private AddCareerViewModel addCareerViewModel;
    private AddCareerRecycleViewAdapter addCareerRecycleViewAdapter;

    private RecyclerView rv_records;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentAddcareerBinding.inflate(inflater, container, false);
        addCareerViewModel = new ViewModelProvider(this).get(AddCareerViewModel.class);

        rv_records = binding.addCareerRvCareerList;

        if(addCareerRecycleViewAdapter == null){
            addCareerRecycleViewAdapter = new AddCareerRecycleViewAdapter(addCareerViewModel.getRecordList());
        }


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_records.setLayoutManager(new LinearLayoutManager(requireContext()));
        rv_records.setAdapter(addCareerRecycleViewAdapter);

        addCareerRecycleViewAdapter.notifyDataSetChanged();

    }
}