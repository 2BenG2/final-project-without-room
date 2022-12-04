package com.lectures.finalproject.ui.info_page;

import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lectures.finalproject.databinding.FragmentInfoPageBinding;
import com.lectures.finalproject.models.content.ContentForView;
import com.lectures.finalproject.models.series.*;
import com.lectures.finalproject.models.movies.*;
import com.lectures.finalproject.tools.DownloadImageTask;

public class InfoPageFragment extends Fragment {

    private InfoPageViewModel infoPageViewModel;
    private FragmentInfoPageBinding binding;
    private static ContentForView contentForView;

    public static InfoPageFragment newInstance(ContentForView content) {
        contentForView = content;
        return new InfoPageFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        infoPageViewModel = new ViewModelProvider(this).get(InfoPageViewModel.class);
        binding = FragmentInfoPageBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.tvDetailTitle.setText(contentForView.getTitle());
        binding.tvDetailReleaseDate.setText(contentForView.getReleaseDate());
        binding.tvDetailOverview.setText(contentForView.getOverView());
        binding.tvDetailAvgVotes.setText(String.valueOf(contentForView.getVoteAvg())+"/10\n\nVotes: " + contentForView.getVoteCount());
        new DownloadImageTask(binding.ivDetailImage).execute("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + contentForView.getPosterPath());

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}