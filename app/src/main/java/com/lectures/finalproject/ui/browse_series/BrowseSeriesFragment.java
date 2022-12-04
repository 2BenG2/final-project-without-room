package com.lectures.finalproject.ui.browse_series;

import android.os.Bundle;
import android.os.Handler;
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
import com.lectures.finalproject.models.series.*;
import com.lectures.finalproject.models.movies.*;
import com.lectures.finalproject.R;
import com.lectures.finalproject.databinding.FragmentBrowseSeriesBinding;
import com.lectures.finalproject.enums.SeriesListType;
import com.lectures.finalproject.tools.PopupPickList;
import com.lectures.finalproject.tools.RecycleViewService;


public class BrowseSeriesFragment extends Fragment implements PickListListener {

    private FragmentBrowseSeriesBinding binding;
    private BrowseSeriesViewModel browseSeriesViewModel;
    public int page = 1;
    private int totalPages;
    private final SeriesListType defaultSeriesListType = SeriesListType.TOP_RATED;
    private  SeriesListType currentSeriesListType;
    private Button topRatedBtn ;
    private Button popularBtn ;
    private Button upOnTheAirBtn;
    private boolean isMultiPage;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        browseSeriesViewModel = new ViewModelProvider(this).get(BrowseSeriesViewModel.class);
        binding = FragmentBrowseSeriesBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        topRatedBtn = binding.btnTopRated;
        setOnClickBtn(topRatedBtn,SeriesListType.TOP_RATED);

        popularBtn = binding.btnPopular;
        setOnClickBtn(popularBtn,SeriesListType.POPULAR);

        upOnTheAirBtn = binding.btnOnTheAir;
        setOnClickBtn(upOnTheAirBtn,SeriesListType.ON_THE_AIR);

        setRecycleBySeriesListType(defaultSeriesListType);
        setBtnsColors(defaultSeriesListType);
        currentSeriesListType = defaultSeriesListType;
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
        if(page >= 1 || page <= browseSeriesViewModel.getTotalPages()){
            browseSeriesViewModel.getSeriesByPage(currentSeriesListType,page).observe(getViewLifecycleOwner(), series -> {
                RecycleViewService.setRecycleView(series,binding.rvSeriesBrowse,this);
            });
        }}
    public void setRecycleBySeriesListType(SeriesListType seriesListType){
        browseSeriesViewModel.getSeries(seriesListType).observe(getViewLifecycleOwner(), series -> {
            totalPages = browseSeriesViewModel.getTotalPages();
            RecycleViewService.setRecycleView(series,binding.rvSeriesBrowse,this);
            if (totalPages > 1){
                binding.fabNext.show();
                isMultiPage = true;
            }
        });
    }
    public void setBtnsColors(SeriesListType mListType){
        currentSeriesListType = mListType;
        switch (mListType){
            case POPULAR:
                setBtnStyle(popularBtn,topRatedBtn, upOnTheAirBtn);
                break;
            case TOP_RATED:
                setBtnStyle(topRatedBtn,popularBtn, upOnTheAirBtn);
                break;
            case ON_THE_AIR:
                setBtnStyle(upOnTheAirBtn,popularBtn,topRatedBtn);
                break;

            default:
                topRatedBtn.setBackgroundColor(requireContext().getColor(R.color.myPressed));
                popularBtn.setBackgroundColor(requireContext().getColor(R.color.myYellow));
                upOnTheAirBtn.setBackgroundColor(requireContext().getColor(R.color.myYellow));

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

    public void setOnClickBtn(Button pressedBtn, SeriesListType ListType){

        pressedBtn.setOnClickListener(v->{
            setBtnsColors(ListType);
            page = 1;
            setRecycleBySeriesListType(ListType);
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(Content content) {
        PopupPickList popupPickList = new PopupPickList(getActivity(), ContentType.SERIES,content);
        popupPickList.show();
    }
}