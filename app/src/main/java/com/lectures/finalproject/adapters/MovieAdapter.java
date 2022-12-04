package com.lectures.finalproject.adapters;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lectures.finalproject.InfoPageActivity;
import com.lectures.finalproject.R;
import com.lectures.finalproject.controllers.lists.ListManager;
import com.lectures.finalproject.listeners.PickListListener;
import com.lectures.finalproject.models.movies.*;
import com.lectures.finalproject.tools.DownloadImageTask;
import com.lectures.finalproject.tools.PopupNewList;
import com.lectures.finalproject.tools.PopupPickList;
import com.lectures.finalproject.tools.RecycleViewService;


import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {


    private List<Movie> movies;

    private PickListListener listener;

    public MovieAdapter(List<Movie> movies, PickListListener listener) {
        this.movies = movies;
        this.listener = listener;
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_cube,parent,false);
        return new MovieViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        new DownloadImageTask(holder.movieImg).execute("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + movie.getPosterPath());
        holder.movieTitle.setText(movie.getTitle());
        holder.movieReleaseDate.setText(movie.getReleaseDate());
        holder.movieVotes.setText(String.valueOf(movie.getVote_average())+"/10");
        holder.movieSummary.setText(movie.getOverview());
        holder.movie = movie;
        holder.btnAddToList.setOnClickListener(v->{
            listener.onClick(movie);
        });
        holder.btnMoreDetails.setOnClickListener(v->{
            goToDetails(holder.itemView,movie);
        });

    }

    @Override
    public int getItemCount() {
        if (movies == null) {
            return 0;
        }

        return movies.size();
    }


    static class MovieViewHolder extends RecyclerView.ViewHolder {
        final ImageView movieImg ;
        final TextView movieTitle ;
        final TextView movieVotes;
        final TextView movieReleaseDate ;
        final TextView movieSummary ;
        final Button btnAddToList;
        final Button btnMoreDetails;
        private Movie movie;
        private Context context;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImg = itemView.findViewById(R.id.iv_movie_image);
            movieTitle = itemView.findViewById(R.id.tv_movie_title);
            movieReleaseDate = itemView.findViewById(R.id.tv_release_date);
            movieSummary = itemView.findViewById(R.id.tv_sum);
            movieVotes = itemView.findViewById(R.id.tv_avg_votes);
            btnAddToList = itemView.findViewById(R.id.btn_add_to_list);
            btnMoreDetails = itemView.findViewById(R.id.btn_more_details);
            context = itemView.getContext();
            itemView.setOnClickListener(v->{
                goToDetails(itemView,movie);
            });
        }

    }
    public static void goToDetails(View itemView,Movie movie){
        Intent intent = new Intent(itemView.getContext(), InfoPageActivity.class);
        intent.putExtra(RecycleViewService.CONTENT,movie);
        itemView.getContext().startActivity(intent);
    }

}

