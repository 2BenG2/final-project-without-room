package com.lectures.finalproject.listeners;

import com.lectures.finalproject.models.series.*;
import com.lectures.finalproject.models.movies.*;

import java.util.List;

public interface SeriesDBResponseListener {
    void onSeriesArrived(List<Series> series, int totalPages);

}
