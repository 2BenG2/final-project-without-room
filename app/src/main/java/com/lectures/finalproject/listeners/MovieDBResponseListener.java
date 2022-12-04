package com.lectures.finalproject.listeners;



import com.lectures.finalproject.models.movies.*;


import java.util.List;

public interface MovieDBResponseListener {

    void onMovieArrived(List<Movie> movies,int totalPages);



}
