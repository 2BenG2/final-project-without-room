package com.lectures.finalproject.models.lists;

import com.lectures.finalproject.enums.ContentType;
import com.lectures.finalproject.models.content.Content;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyList implements Serializable {
    private float listId;
    private String name;
    private String dateCreated;
    private String dateUpdated;
    private ContentType listType;
    private List<Content> contentList;

    public MyList( String name,ContentType listType, String dateCreated) {
        this.name = name;
        this.listType = listType;
        this.dateCreated = dateCreated;
        this.contentList = new ArrayList<Content>();
    }
    public void updateList(Content content) throws Exception {
        if(getListType() == content.getContentType()){
            contentList.add(content);
        }else {
            throw new Exception("Content does not match list type");
        }
    }





    public ContentType getListType() {
        return listType;
    }

    public void setListType(ContentType listType) {
        this.listType = listType;
    }

    public float getListId() {
        return listId;
    }

    public void setListId(float listId) {
        this.listId = listId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public List<Content> getContentList() {
        return contentList;
    }

    public void setContentList(List<Content> contentList) {
        this.contentList = contentList;
    }
}
