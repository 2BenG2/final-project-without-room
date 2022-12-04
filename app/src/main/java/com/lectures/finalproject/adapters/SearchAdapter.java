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
import com.lectures.finalproject.models.movies.Movie;
import com.lectures.finalproject.models.search.SearchContent;
import com.lectures.finalproject.tools.DownloadImageTask;
import com.lectures.finalproject.tools.RecycleViewService;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder>{


    private List<SearchContent> searchResults;
    private PickListListener listener;

    public SearchAdapter(List<SearchContent> searchResults, PickListListener listener) {
        this.searchResults = searchResults;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_cube,parent,false);
        return new SearchAdapter.SearchViewHolder(view);
    }



    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.SearchViewHolder holder, int position) {
        SearchContent searchContent = searchResults.get(position);
        new DownloadImageTask(holder.searchImg).execute("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + searchContent.getPosterPath());
        if(searchContent.getMediaType().equals("movie")){
            holder.searchTitle.setText(searchContent.getTitle());
            holder.searchReleaseDate.setText(searchContent.getReleaseDate());
        }else if(searchContent.getMediaType().equals("tv")) {
            holder.searchTitle.setText(searchContent.getName());
            holder.searchReleaseDate.setText(searchContent.getFirst_air_date());
        }

        holder.searchVotes.setText(String.valueOf(searchContent.getVote_average())+"/10");
        holder.searchSummary.setText(searchContent.getOverview());
        holder.searchContent = searchContent;
        holder.btnAddToList.setOnClickListener(v->{
            listener.onClick(searchContent);
        });
        holder.btnMoreDetails.setOnClickListener(v->{
            goToDetails(holder.itemView,searchContent);
        });
    }

    @Override
    public int getItemCount() {
        if (searchResults == null) {
            return 0;
        }

        return searchResults.size();
    }
    static class SearchViewHolder extends RecyclerView.ViewHolder{
        final ImageView searchImg ;
        final TextView searchTitle ;
        final TextView searchVotes;
        final TextView searchReleaseDate ;
        final TextView searchSummary ;
        private SearchContent searchContent;
        final Button btnAddToList;
        final Button btnMoreDetails;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            searchImg = itemView.findViewById(R.id.iv_movie_image);
            searchTitle = itemView.findViewById(R.id.tv_movie_title);
            searchReleaseDate = itemView.findViewById(R.id.tv_release_date);
            searchSummary = itemView.findViewById(R.id.tv_sum);
            searchVotes = itemView.findViewById(R.id.tv_avg_votes);
            btnAddToList = itemView.findViewById(R.id.btn_add_to_list);
            btnMoreDetails = itemView.findViewById(R.id.btn_more_details);
            itemView.setOnClickListener(v->{
                goToDetails(itemView,searchContent);
            });
        }
    }
    public static void goToDetails(View itemView, SearchContent searchContent){
        Intent intent = new Intent(itemView.getContext(), InfoPageActivity.class);
        intent.putExtra(RecycleViewService.CONTENT,searchContent);
        itemView.getContext().startActivity(intent);
    }
}

