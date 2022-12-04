package com.lectures.finalproject.listeners;

import com.lectures.finalproject.models.search.SearchContent;

import java.util.List;

public interface SearchDBResponseListener {

    void onSearchArrived(List<SearchContent> series, int totalPages);
}
