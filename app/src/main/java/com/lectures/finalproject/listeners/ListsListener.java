package com.lectures.finalproject.listeners;

import com.lectures.finalproject.models.lists.MyList;

import java.util.Map;

public interface ListsListener {
    void onListUpdate(Map<String, MyList> listOfLists);
}
