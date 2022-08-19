package com.example.worker.accountAdmin.fragment.addCareer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worker.databinding.FragmentAddcareerBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AddCareerListFragment extends Fragment {

    private FragmentAddcareerBinding binding;
    private AddCareerViewModel addCareerViewModel;
    private AddCareerRecycleViewAdapter addCareerRecycleViewAdapter;

    private RecyclerView rv_records;
    private ArrayList<AddCareerListItem> careerList;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentAddcareerBinding.inflate(inflater, container, false);
        addCareerViewModel = new ViewModelProvider(this).get(AddCareerViewModel.class);

        rv_records = binding.addCareerRvCareerList;

        addCareerViewModel.getData();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

         careerList = (ArrayList<AddCareerListItem>)addCareerViewModel.getRecordList();
         addCareerRecycleViewAdapter = new AddCareerRecycleViewAdapter(careerList);


        addCareerViewModel.careerCheck().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

                careerList.add(addCareerViewModel.getAddCareerListItem());
                addCareerRecycleViewAdapter = new AddCareerRecycleViewAdapter(careerList);
            }
        });

        rv_records.setLayoutManager(new LinearLayoutManager(requireContext()));
        rv_records.setAdapter(addCareerRecycleViewAdapter);



    }

}