package com.lectures.finalproject.ui.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lectures.finalproject.enums.MovieListType;
import com.lectures.finalproject.listeners.MovieDBResponseListener;
import com.lectures.finalproject.listeners.SearchDBResponseListener;
import com.lectures.finalproject.models.movies.Movie;
import com.lectures.finalproject.models.search.SearchContent;
import com.lectures.finalproject.repos.MovieDBApiRepository;

import java.util.List;

public class SearchViewModel extends ViewModel {

    private MutableLiveData<List<SearchContent>> searchLiveData;
    private List<SearchContent> searchList;
    private int totalPages;

    public LiveData<List<SearchContent>> searchByQuery(String query) {

        if (searchLiveData == null) {
            searchLiveData = new MutableLiveData<>();
        }

        try {
            MovieDBApiRepository.getInstance().searchByQuery(query,new SearchDBResponseListener() {
                @Override
                public void onSearchArrived(List<SearchContent> searchContents,int total) {
                    searchLiveData.postValue(searchContents);
                    totalPages = total;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return searchLiveData;
    }

    public LiveData<List<SearchContent>> getSearchByPage(String query, int page) {

        if (searchLiveData == null) {
            searchLiveData = new MutableLiveData<>();
        }

        try {
            MovieDBApiRepository.getInstance().searchByQuery(page,query,new SearchDBResponseListener() {
                @Override
                public void onSearchArrived(List<SearchContent> searchContents,int total) {
                    searchLiveData.postValue(searchContents);
                    totalPages = total;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return searchLiveData;
    }
    public int getTotalPages() {
        return totalPages;
    }
}