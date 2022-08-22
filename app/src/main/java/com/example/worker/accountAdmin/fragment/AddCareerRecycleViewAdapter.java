package com.example.worker.accountAdmin.fragment;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.worker.accountAdmin.viewModel.AddCareerListItem;
import com.example.worker.databinding.FragmentAddcareeritemBinding;


import java.util.ArrayList;

public class AddCareerRecycleViewAdapter extends Adapter<AddCareerRecycleViewAdapter.ViewHolder> {

    private  ArrayList<AddCareerListItem> records;


    public AddCareerRecycleViewAdapter(ArrayList<AddCareerListItem> items) {
        this.records = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentAddcareeritemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        String career = records.get(position).getCareer();
        holder.tv_career.setText(career);

       /*
       for(int i =0; i<records.size(); i++) {
            AddCareerListItem currRecord = records.get(i);
            holder.tv_career.setText(currRecord.getCareer());
        }
      */

    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected  TextView tv_career;

        public ViewHolder(@NonNull FragmentAddcareeritemBinding binding) {
            super(binding.getRoot());
            tv_career = binding.addCareerTvCareer;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tv_career.getText() + "'";
        }
    }
}