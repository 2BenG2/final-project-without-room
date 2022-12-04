package com.lectures.finalproject.controllers.lists;

import android.util.Log;

import com.lectures.finalproject.enums.ContentType;
import com.lectures.finalproject.listeners.ListsListener;
import com.lectures.finalproject.models.content.Content;
import com.lectures.finalproject.models.lists.MyList;
import com.lectures.finalproject.models.movies.Movie;
import com.lectures.finalproject.models.series.Series;
import com.lectures.finalproject.repos.MovieDBApiRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ListManager {

    private Map<String,MyList> listOfLists;
    private ListsListener listener;
    private static ListManager instance;

    public synchronized static ListManager getInstance() {
        if (instance == null) {
            instance = new ListManager();
        }

        return instance;
    }
    public ListManager(){
        if(listOfLists == null){
            listOfLists = new HashMap<>();
        }
    }

    public boolean isNameExist(String name){
        if(listOfLists.containsKey(name)){
            return true;
        }return false;
    }

    public List<MyList> getListAsArray() {
        return new ArrayList<>(listOfLists.values());
    }


    public void getMyLists(ListsListener listener){
        this.listener = listener;
        listsUpdated();

    }

    public Map<String, MyList> getListOfLists() {
        return listOfLists;
    }

    public void addNewList(MyList myList) {
        if(listOfLists.get(myList.getName()) == null){
            listOfLists.put(myList.getName(),myList);
            listsUpdated();

        }else{
            Log.d("ggggg", "list name taken");
        }

    }
    public void deleteList(String listName)  {
        Objects.requireNonNull(listOfLists.remove(listName),"list not found");
        listsUpdated();
    }
    public void updateList(String listName,Content content) throws Exception {
        Objects.requireNonNull(listOfLists.get(listName),"list not found").updateList(content);
        listsUpdated();
    }
    public void listsUpdated(){
        listener.onListUpdate(listOfLists);

    }






}
