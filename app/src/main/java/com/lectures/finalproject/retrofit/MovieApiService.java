package com.lectures.finalproject.retrofit;

import com.lectures.finalproject.models.search.SearchResponse;
import com.lectures.finalproject.models.series.*;
import com.lectures.finalproject.models.movies.*;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiService {

    //movies
    @GET("movie/top_rated?api_key=e0d0db9e00d2b010268781d4e0630df7&language=en-US&")
    Call<MovieResponse> getTopRatedMovies(@Query("page") int pageNum ); //e0d0db9e00d2b010268781d4e0630df7
    @GET("movie/top_rated?api_key=e0d0db9e00d2b010268781d4e0630df7&language=en-US&")
    Call<MovieResponse> getTopRatedMovies(); //e0d0db9e00d2b010268781d4e0630df7

    @GET("movie/popular?api_key=e0d0db9e00d2b010268781d4e0630df7")
    Call<MovieResponse> getPopularMovies(@Query("page") int pageNum);
    @GET("movie/popular?api_key=e0d0db9e00d2b010268781d4e0630df7")
    Call<MovieResponse> getPopularMovies();

    @GET("movie/upcoming?api_key=e0d0db9e00d2b010268781d4e0630df7")
    Call<MovieResponse> getUpcomingMovies(@Query("page") int pageNum);
    @GET("movie/upcoming?api_key=e0d0db9e00d2b010268781d4e0630df7")
    Call<MovieResponse> getUpcomingMovies();

    //series
    @GET("tv/top_rated?api_key=e0d0db9e00d2b010268781d4e0630df7&language=en-US&")
    Call<SeriesResponse> getTopRatedSeries(@Query("page") int pageNum );
    @GET("tv/top_rated?api_key=e0d0db9e00d2b010268781d4e0630df7")
    Call<SeriesResponse> getTopRatedSeries();


    @GET("tv/popular?api_key=e0d0db9e00d2b010268781d4e0630df7")
    Call<SeriesResponse> getPopularSeries(@Query("page") int pageNum);
    @GET("tv/popular?api_key=e0d0db9e00d2b010268781d4e0630df7")
    Call<SeriesResponse> getPopularSeries();

    @GET("tv/on_the_air?api_key=e0d0db9e00d2b010268781d4e0630df7")
    Call<SeriesResponse> getOnTheAirSeries();
    @GET("tv/on_the_air?api_key=e0d0db9e00d2b010268781d4e0630df7&language=en-US&")
    Call<SeriesResponse> getOnTheAirSeries(@Query("page") int pageNum );

    @GET("search/multi?api_key=e0d0db9e00d2b010268781d4e0630df7&language=en-US")
    Call<SearchResponse> SearchByQuery(@Query("query") String searchQuery );
    @GET("search/multi?api_key=e0d0db9e00d2b010268781d4e0630df7&language=en-US")
    Call<SearchResponse> SearchByQuery(@Query("query") String searchQuery ,@Query("page") int pageNum);


    static MovieApiService create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(MovieApiService.class);
    }


}
