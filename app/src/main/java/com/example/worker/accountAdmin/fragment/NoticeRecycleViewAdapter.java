package com.example.worker.accountAdmin.fragment;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worker.accountAdmin.viewModel.AddCareerListItem;
import com.example.worker.databinding.ObjectAddcareeritemBinding;
import com.example.worker.databinding.ObjectNoticeitemBinding;

import java.util.ArrayList;

public class NoticeRecycleViewAdapter extends RecyclerView.Adapter<NoticeRecycleViewAdapter.ViewHolder> {


    private ArrayList<AddCareerListItem> records;


    public NoticeRecycleViewAdapter(ArrayList<AddCareerListItem> items) {
        this.records = items;
    }

    @Override
    public NoticeRecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NoticeRecycleViewAdapter.ViewHolder(ObjectNoticeitemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //
        //

    }



    @Override
    public int getItemCount() {
        return records.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView tv_notice;

        public ViewHolder(@NonNull ObjectNoticeitemBinding binding) {
            super(binding.getRoot());
            tv_notice = binding.ObjectNoticeItemTvTitle;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tv_notice.getText() + "'";
        }
    }



}
