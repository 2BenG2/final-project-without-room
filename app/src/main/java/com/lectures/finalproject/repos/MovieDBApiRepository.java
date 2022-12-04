package com.lectures.finalproject.repos;

import androidx.annotation.NonNull;

import com.lectures.finalproject.enums.MovieListType;
import com.lectures.finalproject.enums.SeriesListType;
import com.lectures.finalproject.listeners.MovieDBResponseListener;
import com.lectures.finalproject.listeners.SearchDBResponseListener;
import com.lectures.finalproject.listeners.SeriesDBResponseListener;
import com.lectures.finalproject.models.search.SearchResponse;
import com.lectures.finalproject.models.series.*;
import com.lectures.finalproject.models.movies.*;
import com.lectures.finalproject.retrofit.MovieApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDBApiRepository {

    MovieApiService retrofit;

    private MovieDBApiRepository() {
        retrofit = MovieApiService.create();
    }

    private static MovieDBApiRepository instance;

    public synchronized static MovieDBApiRepository getInstance() {
        if (instance == null) {
            instance = new MovieDBApiRepository();
        }

        return instance;
    }



    public void getSeries(SeriesListType listType, SeriesDBResponseListener listener) throws Exception{
        Call<SeriesResponse> call;
        switch (listType) {
            case POPULAR:
                call = retrofit.getPopularSeries();
                break;
            case TOP_RATED:
                call = retrofit.getTopRatedSeries();
                break;
            case ON_THE_AIR:
                call = retrofit.getOnTheAirSeries();
                break;
            default:
                call = retrofit.getPopularSeries();

        }
        setSeriesCall(call,listener);
    }
    public void getSeries(int page,SeriesListType listType, SeriesDBResponseListener listener) throws Exception{
        Call<SeriesResponse> call;
        switch (listType) {
            case POPULAR:
                call = retrofit.getPopularSeries();
                break;
            case TOP_RATED:
                call = retrofit.getTopRatedSeries();
                break;
            case ON_THE_AIR:
                call = retrofit.getOnTheAirSeries();
                break;
            default:
                call = retrofit.getPopularSeries();

        }
        setSeriesCall(call,listener);
    }
    public void getMovies(MovieListType listType, MovieDBResponseListener listener) throws Exception {
        Call<MovieResponse> call;
        switch (listType) {
            case POPULAR:
                call = retrofit.getPopularMovies();
                break;
            case TOP_RATED:
                call = retrofit.getTopRatedMovies();
                break;
            case UP_COMING:
                call = retrofit.getUpcomingMovies();
                break;
            default:
                call = retrofit.getPopularMovies();

        }
        setMovieCall(call,listener);

    }
    public void getMovies(int page,MovieListType listType, MovieDBResponseListener listener) throws Exception {
        Call<MovieResponse> call;
        switch (listType) {
            case POPULAR:
                call = retrofit.getPopularMovies(page);
                break;
            case TOP_RATED:
                call = retrofit.getTopRatedMovies(page);
                break;
            case UP_COMING:
                call = retrofit.getUpcomingMovies(page);
                break;
            default:
                call = retrofit.getPopularMovies();

        }
        setMovieCall(call,listener);

    }
    public void searchByQuery(String query, SearchDBResponseListener listener){

        Call<SearchResponse> call;
        call = retrofit.SearchByQuery(query);

        setSearchCall(call,listener);
    }
    public void searchByQuery(int page,String query, SearchDBResponseListener listener){

        Call<SearchResponse> call;
        call = retrofit.SearchByQuery(query,page);

        setSearchCall(call,listener);
    }





    private void setMovieCall(Call<MovieResponse> call,MovieDBResponseListener listener){
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                assert response.body() != null;
                listener.onMovieArrived(response.body().getResults(),response.body().getTotal_pages());

            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, Throwable t) {

            }
        });
    }
    private void setSeriesCall(Call<SeriesResponse> call, SeriesDBResponseListener listener){
        call.enqueue(new Callback<SeriesResponse>() {
            @Override
            public void onResponse(Call<SeriesResponse> call, @NonNull Response<SeriesResponse> response) {
                assert response.body() != null;
                listener.onSeriesArrived(response.body().getResults(),response.body().getTotal_pages());

            }

            @Override
            public void onFailure(@NonNull Call<SeriesResponse> call, Throwable t) {

            }
        });
    }
    private void setSearchCall(Call<SearchResponse> call, SearchDBResponseListener listener){
        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, @NonNull Response<SearchResponse> response) {
                assert response.body() != null;
                listener.onSearchArrived(response.body().getResults(),response.body().getTotal_pages());

            }


            @Override
            public void onFailure(@NonNull Call<SearchResponse> call, Throwable t) {

            }
        });
    }




}