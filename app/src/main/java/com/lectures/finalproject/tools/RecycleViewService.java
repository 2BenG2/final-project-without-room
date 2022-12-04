package com.lectures.finalproject.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lectures.finalproject.InfoPageActivity;
import com.lectures.finalproject.adapters.MovieAdapter;
import com.lectures.finalproject.adapters.SearchAdapter;
import com.lectures.finalproject.adapters.SeriesAdapter;
import com.lectures.finalproject.enums.ContentType;
import com.lectures.finalproject.listeners.PickListListener;
import com.lectures.finalproject.models.content.Content;
import com.lectures.finalproject.models.search.SearchContent;
import com.lectures.finalproject.models.series.*;
import com.lectures.finalproject.models.movies.*;


import java.util.List;

public interface RecycleViewService {
    public static String CONTENT = "CONTENT";
    public static String LIST = "LIST";

    static void setRecycleView(RecyclerView recyclerView, List<Movie> movieList, PickListListener listener){
        recyclerView.setAdapter(new MovieAdapter(movieList,listener));
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 1));
    }
    static void setRecycleView( List<Series> seriesList,RecyclerView recyclerView, PickListListener listener){
        recyclerView.setAdapter(new SeriesAdapter(seriesList,listener));
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 1));
    }
    static void setSearchRecycleView(List<SearchContent> searchContents, RecyclerView recyclerView, PickListListener listener){
        recyclerView.setAdapter(new SearchAdapter(searchContents,listener));
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 1));
    }








}
