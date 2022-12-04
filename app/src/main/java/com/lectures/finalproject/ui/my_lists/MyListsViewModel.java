package com.lectures.finalproject.ui.my_lists;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lectures.finalproject.controllers.lists.ListManager;
import com.lectures.finalproject.listeners.ListsListener;
import com.lectures.finalproject.models.lists.MyList;

import java.util.List;
import java.util.Map;

public class MyListsViewModel extends ViewModel {


    private MutableLiveData<Map<String,MyList>> myListsLiveData;



    public LiveData<Map<String,MyList>> getLists() {

        if (myListsLiveData == null) {
            myListsLiveData = new MutableLiveData<>();
        }
        ListManager.getInstance().getMyLists(new ListsListener() {
            @Override
            public void onListUpdate(Map<String, MyList> listOfLists) {
                myListsLiveData.postValue(listOfLists);
            }
        });
        return myListsLiveData;
    }
}