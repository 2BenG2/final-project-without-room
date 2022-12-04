package com.lectures.finalproject.adapters;

import android.annotation.SuppressLint;
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
import com.lectures.finalproject.listeners.PickListListener;
import com.lectures.finalproject.models.search.SearchContent;
import com.lectures.finalproject.models.series.*;
import com.lectures.finalproject.tools.DownloadImageTask;
import com.lectures.finalproject.tools.RecycleViewService;

import java.util.List;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder> {

    private List<Series> series;
    private PickListListener listener;

    public SeriesAdapter(List<Series> series, PickListListener listener) {
        this.series = series;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SeriesAdapter.SeriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_cube,parent,false);
        return new SeriesAdapter.SeriesViewHolder(view);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull SeriesViewHolder holder, int position) {
        Series serie = series.get(position);
        new DownloadImageTask(holder.seriesImg).execute("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + serie.getPosterPath());
        holder.seriesTitle.setText(serie.getName());
        holder.seriesReleaseDate.setText(serie.getFirst_air_date());
        holder.seriesVotes.setText(String.valueOf(serie.getVote_average())+"/10");
        holder.seriesSummary.setText(serie.getOverview());
        holder.series = serie;
        holder.btnAddToList.setOnClickListener(v->{
            listener.onClick(serie);
        });
        holder.btnMoreDetails.setOnClickListener(v->{
            goToDetails(holder.itemView,serie);
        });

    }

    @Override
    public int getItemCount() {
        if (series == null) {
            return 0;
        }

        return series.size();
    }


    static class SeriesViewHolder extends RecyclerView.ViewHolder {
        final ImageView seriesImg;
        final TextView seriesTitle;
        final TextView seriesVotes;
        final TextView seriesReleaseDate;
        final TextView seriesSummary;
        private Series series;
        final Button btnAddToList;
        final Button btnMoreDetails;

        public SeriesViewHolder(@NonNull View itemView) {
            super(itemView);
            seriesImg = itemView.findViewById(R.id.iv_movie_image);
            seriesTitle = itemView.findViewById(R.id.tv_movie_title);
            seriesReleaseDate = itemView.findViewById(R.id.tv_release_date);
            seriesSummary = itemView.findViewById(R.id.tv_sum);
            seriesVotes = itemView.findViewById(R.id.tv_avg_votes);
            btnAddToList = itemView.findViewById(R.id.btn_add_to_list);
            btnMoreDetails = itemView.findViewById(R.id.btn_more_details);
            itemView.setOnClickListener(v->{
                goToDetails(itemView,series);
            });
        }

    }
    public static void goToDetails(View itemView, Series series){
        Intent intent = new Intent(itemView.getContext(), InfoPageActivity.class);
        intent.putExtra(RecycleViewService.CONTENT,series);
        itemView.getContext().startActivity(intent);
    }

}