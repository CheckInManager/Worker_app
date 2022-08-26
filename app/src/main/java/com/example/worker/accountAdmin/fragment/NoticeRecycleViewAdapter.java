package com.example.worker.accountAdmin.fragment;

import android.util.Log;
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

        String noticeTitle = records.get(position).getNoticeName();
        String noticeSiteName = records.get(position).getWorksiteKeyValue();
        String noticeMemo = records.get(position).getMemo();


        holder.tv_notice.setText("Title: " + noticeTitle + " /workSiteName: " + noticeSiteName + " /Memo: " + noticeMemo);
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
