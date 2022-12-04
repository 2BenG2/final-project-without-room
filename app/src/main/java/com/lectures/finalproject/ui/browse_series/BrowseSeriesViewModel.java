package com.lectures.finalproject.ui.browse_series;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lectures.finalproject.enums.MovieListType;
import com.lectures.finalproject.enums.SeriesListType;
import com.lectures.finalproject.listeners.MovieDBResponseListener;
import com.lectures.finalproject.listeners.SeriesDBResponseListener;
import com.lectures.finalproject.models.series.*;
import com.lectures.finalproject.models.movies.*;
import com.lectures.finalproject.repos.MovieDBApiRepository;

import java.util.List;

public class BrowseSeriesViewModel extends ViewModel {

    private MutableLiveData<List<Series>> seriesLiveData;
    private List<Movie> seriesList;
    private int totalPages;

    public LiveData<List<Series>> getSeries(SeriesListType listType) {

        if (seriesLiveData == null) {
            seriesLiveData = new MutableLiveData<>();
        }

        try {
            MovieDBApiRepository.getInstance().getSeries(listType,new SeriesDBResponseListener() {
                @Override
                public void onSeriesArrived(List<Series> series,int total) {
                    seriesLiveData.postValue(series);
                    totalPages = total;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return seriesLiveData;
    }
    public LiveData<List<Series>> getSeriesByPage(SeriesListType listType, int page) {

        if (seriesLiveData == null) {
            seriesLiveData = new MutableLiveData<>();
        }

        try {
            MovieDBApiRepository.getInstance().getSeries(page,listType,new SeriesDBResponseListener() {
                @Override
                public void onSeriesArrived(List<Series> series,int totalPages) {
                    seriesLiveData.postValue(series);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return seriesLiveData;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}