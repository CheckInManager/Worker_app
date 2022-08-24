package com.example.worker.accountAdmin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worker.accountAdmin.viewModel.NoticeViewModel;
import com.example.worker.databinding.FragmentNoticelistBinding;

public class NoticeFragment extends Fragment {

    FragmentNoticelistBinding binding;
    NoticeViewModel noticeViewModel;

    private NoticeRecycleViewAdapter noticeRecycleViewAdapter;
    private RecyclerView rv_noticeView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        noticeViewModel = new ViewModelProvider(this).get(NoticeViewModel.class);
        binding = FragmentNoticelistBinding.inflate(inflater, container, false);
        rv_noticeView = binding.getNoticeListRecycleViewNoticeList;

        //noticeRecycleViewAdapter = new NoticeRecycleViewAdapter();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rv_noticeView.setAdapter(noticeRecycleViewAdapter);
        rv_noticeView.setLayoutManager(new LinearLayoutManager(requireContext()));
        noticeRecycleViewAdapter.notifyDataSetChanged();

    }
}
