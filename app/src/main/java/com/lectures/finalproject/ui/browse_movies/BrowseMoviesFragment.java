package com.lectures.finalproject.ui.browse_movies;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.lectures.finalproject.enums.ContentType;
import com.lectures.finalproject.listeners.PickListListener;
import com.lectures.finalproject.models.content.Content;
import com.lectures.finalproject.models.movies.*;
import com.lectures.finalproject.R;
import com.lectures.finalproject.databinding.FragmentBrowseMoviesBinding;
import com.lectures.finalproject.enums.MovieListType;
import com.lectures.finalproject.tools.PopupPickList;
import com.lectures.finalproject.tools.RecycleViewService;


public class BrowseMoviesFragment extends Fragment implements PickListListener {

    private FragmentBrowseMoviesBinding binding;
    private BrowseMoviesViewModel browseMoviesViewModel;
    public int page = 1;
    private int totalPages;
    private final MovieListType defaultMovieListType = MovieListType.TOP_RATED;
    private  MovieListType currentMovieListType;
    private Button topRatedBtn ;
    private Button popularBtn ;
    private Button upComingBtn ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        browseMoviesViewModel = new ViewModelProvider(this).get(BrowseMoviesViewModel.class);
        binding = FragmentBrowseMoviesBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        topRatedBtn = binding.btnTopRated;
        setOnClickBtn(topRatedBtn,MovieListType.TOP_RATED);

        popularBtn = binding.btnPopular;
        setOnClickBtn(popularBtn,MovieListType.POPULAR);

        upComingBtn = binding.btnUpComing;
        setOnClickBtn(upComingBtn,MovieListType.UP_COMING);

        setRecycleByMovieListType(defaultMovieListType);
        setBtnsColors(defaultMovieListType);
        currentMovieListType = defaultMovieListType;
        binding.fabNext.setOnClickListener(v->{
                page++;
                setNewPageView(true);
                binding.fabBack.show();
                if(totalPages == page){
                    binding.fabBack.hide();
                }
        });
        binding.fabBack.setOnClickListener(v->{
                page--;
                setNewPageView(false);
                binding.fabBack.show();
                if(page == 1){
                    binding.fabBack.hide();
                }
        });
    }
    public void setNewPageView(boolean isNext){
        if(page >= 1 || page <= browseMoviesViewModel.getTotalPages()){
            browseMoviesViewModel.getMoviesByPage(currentMovieListType,page).observe(getViewLifecycleOwner(), movies -> {
                RecycleViewService.setRecycleView(binding.rvMoviesBrowse, movies,this);
        });
    }}
    public void setRecycleByMovieListType(MovieListType movieListType){
        browseMoviesViewModel.getMovies(movieListType).observe(getViewLifecycleOwner(), movies -> {
            totalPages = browseMoviesViewModel.getTotalPages();
            RecycleViewService.setRecycleView(binding.rvMoviesBrowse, movies,this);
            if (totalPages > 1){
                binding.fabNext.show();
            }
        });
    }
    public void setBtnsColors(MovieListType movieListType){
        currentMovieListType = movieListType;
        switch (movieListType){
            case POPULAR:
                setBtnStyle(popularBtn,topRatedBtn,upComingBtn);
                break;
            case TOP_RATED:
                setBtnStyle(topRatedBtn,popularBtn,upComingBtn);
                break;
            case UP_COMING:
                setBtnStyle(upComingBtn,popularBtn,topRatedBtn);
                break;

            default:
                topRatedBtn.setBackgroundColor(requireContext().getColor(R.color.myPressed));
                popularBtn.setBackgroundColor(requireContext().getColor(R.color.myYellow));
                upComingBtn.setBackgroundColor(requireContext().getColor(R.color.myYellow));

        }


    }
    public void setBtnStyle(Button pressedBtn,Button...buttons){
        pressedBtn.setEnabled(false);
        pressedBtn.setBackgroundColor(requireContext().getColor(R.color.myPressed));
        for(Button button:buttons){
            button.setEnabled(true);
            button.setBackgroundColor(requireContext().getColor(R.color.myYellow));
        }
    }

    public void setOnClickBtn(Button pressedBtn, MovieListType movieListType){

        pressedBtn.setOnClickListener(v->{
            setBtnsColors(movieListType);
            page = 1;
            setRecycleByMovieListType(movieListType);
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(Content content) {
        PopupPickList popupPickList = new PopupPickList(getActivity(), ContentType.MOVIE,content);
        popupPickList.show();
    }
}