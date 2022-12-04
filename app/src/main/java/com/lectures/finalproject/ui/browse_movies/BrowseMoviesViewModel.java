package com.lectures.finalproject.ui.browse_movies;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lectures.finalproject.enums.MovieListType;
import com.lectures.finalproject.listeners.MovieDBResponseListener;

import com.lectures.finalproject.models.movies.*;
import com.lectures.finalproject.repos.MovieDBApiRepository;

import java.util.List;

public class BrowseMoviesViewModel extends ViewModel {

    private MutableLiveData<List<Movie>> moviesLiveData;
    private List<Movie> movieList;
    private int totalPages;

    public LiveData<List<Movie>> getMovies(MovieListType listType) {

        if (moviesLiveData == null) {
            moviesLiveData = new MutableLiveData<>();
        }

        try {
            MovieDBApiRepository.getInstance().getMovies(listType,new MovieDBResponseListener() {
                @Override
                public void onMovieArrived(List<Movie> Movies,int total) {
                    moviesLiveData.postValue(Movies);
                    totalPages = total;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return moviesLiveData;
    }
    public LiveData<List<Movie>> getMoviesByPage(MovieListType listType,int page) {

        if (moviesLiveData == null) {
            moviesLiveData = new MutableLiveData<>();
        }

        try {
            MovieDBApiRepository.getInstance().getMovies(page,listType,new MovieDBResponseListener() {
                @Override
                public void onMovieArrived(List<Movie> Movies,int totalPages) {
                    moviesLiveData.postValue(Movies);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return moviesLiveData;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}