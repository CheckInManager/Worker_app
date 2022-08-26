package com.example.worker.accountAdmin.fragment;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worker.accountAdmin.model.Notice;
import com.example.worker.databinding.ObjectNoticeitemBinding;

import java.util.ArrayList;

public class NoticeRecycleViewAdapter extends RecyclerView.Adapter<NoticeRecycleViewAdapter.ViewHolder> {


    //private ArrayList<Notice> records;
    private ArrayList<String> records;

//    public NoticeRecycleViewAdapter(ArrayList<Notice> items) {
//        this.records = items;
//    }

    public NoticeRecycleViewAdapter(ArrayList<String> items) {
        this.records = items;
    }



    @Override
    public NoticeRecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NoticeRecycleViewAdapter.ViewHolder(ObjectNoticeitemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //String noticeTitle  =records.get(position).getNoticeName();
        //String noticeTitle = records.get(position);
        //holder.tv_notice.setText(noticeTitle);

        holder.tv_notice.setText("234");


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
