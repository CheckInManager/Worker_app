package com.example.worker.accountAdmin.fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worker.BuildConfig;
import com.example.worker.accountAdmin.model.Notice;
import com.example.worker.databinding.ObjectNoticeitemBinding;

import java.util.ArrayList;

public class NoticeRecycleViewAdapter extends RecyclerView.Adapter<NoticeRecycleViewAdapter.ViewHolder> {


    //private ArrayList<Notice> records;
    private ArrayList<Notice> records;


    public NoticeRecycleViewAdapter(ArrayList<Notice> items) {
        this.records = items;
    }



    @Override
    public NoticeRecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NoticeRecycleViewAdapter.ViewHolder(ObjectNoticeitemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String memo = records.get(position).getMemo();
        String name = records.get(position).getNoticeName();
        String time = records.get(position).getTime();
        String worksiteName = records.get(position).getWorksiteName();

        holder.tv_notice.setText(toString(time, worksiteName, name, memo));


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

    public String toString(String time, String worksiteName, String name, String memo){
        return name + " : " + memo + "worksiteName: " + worksiteName + " time: " + time + "\n";
    }

}
