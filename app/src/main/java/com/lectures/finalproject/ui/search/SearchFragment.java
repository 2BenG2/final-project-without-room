package com.lectures.finalproject.ui.search;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.lectures.finalproject.databinding.FragmentSearchBinding;
import com.lectures.finalproject.enums.ContentType;
import com.lectures.finalproject.enums.MovieListType;
import com.lectures.finalproject.listeners.PickListListener;
import com.lectures.finalproject.models.content.Content;
import com.lectures.finalproject.models.search.SearchContent;
import com.lectures.finalproject.tools.PopupPickList;
import com.lectures.finalproject.tools.RecycleViewService;

import java.util.Objects;


public class SearchFragment extends Fragment implements PickListListener {

    private FragmentSearchBinding binding;
    private SearchViewModel searchViewModel;
    public int page = 1;
    private int totalPages;
    private final MovieListType defaultMovieListType = MovieListType.TOP_RATED;
    private  String queryAfter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        binding = FragmentSearchBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.fabNext.setOnClickListener(v->{
            page++;
            setNewPageView(true);
            binding.fabBack.show();
            if(totalPages == page){
                binding.fabBack.hide();
            }
        });
        binding.fabBack.setOnClickListener(v->{
            page--;
            setNewPageView(false);
            binding.fabBack.show();
            if(page == 1){
                binding.fabBack.hide();
            }
        });

            SearchView searchView = binding.swSearchBar;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

        @Override
        public boolean onQueryTextSubmit(String s) {
            if(!s.isEmpty()){
                setRV(s);
            }else {
                binding.rvSearchResults.setAdapter(null);
            }

            return true;
        }


        @Override
        public boolean onQueryTextChange(String s) {
            if(!s.isEmpty()){
                setRV(s);
            }else {
                binding.rvSearchResults.setAdapter(null);
            }
            return true;
        }
    });


    }
    public void setNewPageView(boolean isNext){
        if(page >= 1 || page <= searchViewModel.getTotalPages()){
            searchViewModel.getSearchByPage(queryAfter,page).observe(getViewLifecycleOwner(), searchResults -> {
                RecycleViewService.setSearchRecycleView(searchResults,binding.rvSearchResults,this);
            });
        }}


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void setRV(String query){
        queryAfter = query;
        searchViewModel.searchByQuery(query).observe(getViewLifecycleOwner(), searchResults -> {
            totalPages = searchViewModel.getTotalPages();
            RecycleViewService.setSearchRecycleView( searchResults,binding.rvSearchResults,this);
            if (totalPages > 1){
                binding.fabNext.show();
            }
        });
    }

    @Override
    public void onClick(Content content) {
        SearchContent searchContent = (SearchContent) content;
        ContentType contentType;
        if(searchContent.getMediaType().equals("movie")){
            contentType = ContentType.MOVIE;
        }else {
            contentType = ContentType.SERIES;
        }
        PopupPickList popupPickList = new PopupPickList(getActivity(),contentType,content);
        popupPickList.show();
    }
}