package com.example.worker.accountAdmin.fragment.addCareer;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.worker.databinding.FragmentAddcareeritemBinding;


import java.util.List;

public class addCareerRecycleViewAdapter extends RecyclerView.Adapter<addCareerRecycleViewAdapter.ViewHolder> {

    private  List<addCareerListItem> recordList;

    public addCareerRecycleViewAdapter(List<addCareerListItem> items) {
        this.recordList = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentAddcareeritemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        addCareerListItem currRecord = recordList.get(position);


    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected  TextView tv_career;

        public ViewHolder(@NonNull FragmentAddcareeritemBinding binding) {
            super(binding.getRoot());
            tv_career = binding.addCareerTvCareer;
        }


        protected void onBind(addCareerListItem items){
            tv_career.setText(items.getCareer().toString());
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tv_career.getText() + "'";
        }
    }
}