package com.example.worker.accountAdmin.fragment.addCareer;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.worker.databinding.FragmentAddcareeritemBinding;


import java.util.ArrayList;
import java.util.List;

public class AddCareerRecycleViewAdapter extends RecyclerView.Adapter<AddCareerRecycleViewAdapter.ViewHolder> {

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

        AddCareerListItem addCareerListItem = records.get(position);
        holder.tv_career.setText(addCareerListItem.getCareer());

        //add item 하는 대로 recycle view 갱신
//        for(int i =0; i<records.size(); i++) {
//            AddCareerListItem currRecord = records.get(i);
//            holder.tv_career.setText(currRecord.getCareer());
//        }


        //AddCareerListItem currRecord = records.get(position);
        //holder.tv_career.setText(currRecord.getCareer());


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


        protected void onBind(AddCareerListItem items){
            tv_career.setText(items.getCareer().toString());
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tv_career.getText() + "'";
        }
    }
}