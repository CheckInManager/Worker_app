package com.example.worker.accountAdmin.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worker.R;
import com.example.worker.accountAdmin.model.Notice;
import com.example.worker.accountAdmin.viewModel.NoticeViewModel;
import com.example.worker.databinding.FragmentNoticelistBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class NoticeFragment extends Fragment {

    FragmentNoticelistBinding binding;
    NoticeViewModel noticeViewModel;

    private NoticeRecycleViewAdapter noticeRecycleViewAdapter;
    private RecyclerView rv_noticeView;

    Notice notice = new Notice();

    private ArrayList<Notice> noticeArrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        noticeViewModel = new ViewModelProvider(this).get(NoticeViewModel.class);
        binding = FragmentNoticelistBinding.inflate(inflater, container, false);
        rv_noticeView = binding.getNoticeListRecycleViewNoticeList;


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        noticeViewModel.getNotice();

        noticeViewModel.getGetDBNotice().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {

                    noticeRecycleViewAdapter = new NoticeRecycleViewAdapter(noticeViewModel.getNoticeArrayList());
                    rv_noticeView.setAdapter(noticeRecycleViewAdapter);
                    rv_noticeView.setLayoutManager(new LinearLayoutManager(requireContext()));
                    noticeRecycleViewAdapter.notifyDataSetChanged();

                }

            }
        });




    }
}