package com.lectures.finalproject.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lectures.finalproject.R;
import com.lectures.finalproject.controllers.lists.ListManager;
import com.lectures.finalproject.enums.ContentType;
import com.lectures.finalproject.listeners.PickListListener;
import com.lectures.finalproject.models.content.Content;
import com.lectures.finalproject.models.lists.MyList;
import com.lectures.finalproject.models.movies.Movie;
import com.lectures.finalproject.models.series.Series;
import com.lectures.finalproject.tools.RecycleViewService;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder>  {
    private List<MyList> myLists;
    private RecyclerView mRecyclerView;
    private Button backBtn;
    private PickListListener listener;
    static public ContentType contentTypeForList;

    public ListAdapter(Button backBtn,PickListListener listener){
        this.backBtn = backBtn;
        this.myLists = ListManager.getInstance().getListAsArray();
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cube,parent,false);
        return new ListViewHolder(view);
    }
        @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        mRecyclerView = recyclerView;
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        MyList myList = myLists.get(position);
        holder.listName.setText(myList.getName());
        holder.numOfItems.setText(String.valueOf(myList.getContentList().size()));
        holder.dateCreated.setText(String.valueOf(myList.getDateCreated()));
        holder.imageView.setImageResource(R.drawable.yellow_concrate);
        String temp = myList.getListType().toString();
        String listType = temp.substring(0, 1).toUpperCase() + temp.substring(1);
        holder.contentType.setText(listType);
        holder.myList = myList;
        holder.deleteBtn.setOnClickListener(v->{
            ListManager.getInstance().deleteList(myList.getName());
            ListManager.getInstance().listsUpdated();
        });



        holder.itemView.setOnClickListener(v->{
            if(myList.getListType().equals(ContentType.MOVIE)){
                contentTypeForList = ContentType.MOVIE;
                List<Movie> movies = new ArrayList<>();
                for(Content content:myList.getContentList()){
                    movies.add((Movie) content);
                }
                RecycleViewService.setRecycleView(mRecyclerView,movies,listener);
            }else {
                contentTypeForList = ContentType.SERIES;
                List<Series> series = new ArrayList<>();
                for(Content content:myList.getContentList()){
                    series.add((Series) content);
                }
                RecycleViewService.setRecycleView(series,mRecyclerView,listener);
            }
            backBtn.setVisibility(View.VISIBLE);
        });

    }

    @Override
    public int getItemCount() {
        if (myLists == null) {
            return 0;
        }

        return myLists.size();
    }

    static class ListViewHolder extends RecyclerView.ViewHolder {
        final TextView listName ;
        final TextView numOfItems ;
        final TextView dateCreated ;
        final TextView contentType ;
        final ImageView imageView;
        final ImageView deleteBtn;
        final View view;

        private MyList myList;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            listName = itemView.findViewById(R.id.list_name);
            numOfItems = itemView.findViewById(R.id.tv_my_list_num_of_items);
            dateCreated = itemView.findViewById(R.id.tv_create_date);
            imageView = itemView.findViewById(R.id.im_my_list_img);
            contentType = itemView.findViewById(R.id.tv_content_type);
            deleteBtn = itemView.findViewById(R.id.iv_delete_list);
            view = itemView;

        }
    }
}

