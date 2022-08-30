package com.example.worker.accountAdmin.fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worker.BuildConfig;
import com.example.worker.accountAdmin.model.Notice;
import com.example.worker.accountAdmin.util.DateFormatter;
import com.example.worker.databinding.ObjectNoticeitemBinding;

import java.util.ArrayList;
import java.util.List;

public class NoticeRecycleViewAdapter extends RecyclerView.Adapter<NoticeRecycleViewAdapter.ViewHolder> {


    //private ArrayList<Notice> records;
    private List<Notice> records;


    public NoticeRecycleViewAdapter(List<Notice> items) {
        this.records = items;
    }



    @Override
    public NoticeRecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NoticeRecycleViewAdapter.ViewHolder(ObjectNoticeitemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notice currNotice = records.get(position);
        holder.tv_noticeName.setText(currNotice.getNoticeName());
        holder.tv_memo.setText(currNotice.getMemo());
        holder.tv_time.setText(DateFormatter.formatTimestampToDate(currNotice.getTime()));
    }



    @Override
    public int getItemCount() {
        return records.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final CardView cv_card;
        public final TextView tv_noticeName;
        public final TextView tv_memo;
        public final TextView tv_time;

        public ViewHolder(@NonNull ObjectNoticeitemBinding binding) {
            super(binding.getRoot());
            cv_card = binding.objNoticeCard;
            tv_noticeName = binding.objNoticeTvNoticeName;
            tv_memo = binding.objNoticeTvMemo;
            tv_time = binding.objNoticeTvTime;
        }

        @Override
        public String toString() {
            return super.toString() + "" ;
        }
    }

}
