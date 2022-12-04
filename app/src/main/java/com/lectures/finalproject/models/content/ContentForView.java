package com.lectures.finalproject.models.content;


import com.lectures.finalproject.models.search.SearchContent;
import com.lectures.finalproject.models.series.*;
import com.lectures.finalproject.models.movies.*;

public class ContentForView {
    private String title;
    private String releaseDate;
    private String overView;
    private String posterPath;
    private float voteCount;
    private float voteAvg;



    public ContentForView(String title, String releaseDate, String overView, float voteCount, float voteAvg,String posterPath) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.overView = overView;
        this.voteCount = voteCount;
        this.voteAvg = voteAvg;
        this.posterPath = posterPath;
    }
    public ContentForView(Movie movie){
        this.title = movie.getTitle();
        this.releaseDate = movie.getReleaseDate();
        this.overView = movie.getOverview();
        this.voteCount = movie.getVote_count();
        this.voteAvg = movie.getVote_average();
        this.posterPath = movie.getPosterPath();
    }
    public ContentForView(Series series){
        this.title = series.getName();
        this.releaseDate = series.getFirst_air_date();
        this.overView = series.getOverview();
        this.voteCount = series.getVote_count();
        this.voteAvg = series.getVote_average();
        this.posterPath = series.getPosterPath();
    }
    public ContentForView(SearchContent searchContent){
        if(searchContent.getMediaType().equals("movie")){
            this.title = searchContent.getTitle();
            this.releaseDate = searchContent.getReleaseDate();
        }else if(searchContent.getMediaType().equals("tv")) {
            this.title = searchContent.getName();
            this.releaseDate = searchContent.getFirst_air_date();
        }
        this.overView = searchContent.getOverview();
        this.voteCount = searchContent.getVote_count();
        this.voteAvg = searchContent.getVote_average();
        this.posterPath = searchContent.getPosterPath();
    }


    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOverView() {
        return overView;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }

    public float getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(float voteCount) {
        this.voteCount = voteCount;
    }

    public float getVoteAvg() {
        return voteAvg;
    }

    public void setVoteAvg(float voteAvg) {
        this.voteAvg = voteAvg;
    }




}
