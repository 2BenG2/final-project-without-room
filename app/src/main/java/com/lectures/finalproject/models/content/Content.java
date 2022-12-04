package com.lectures.finalproject.models.content;

import com.lectures.finalproject.enums.ContentType;

import java.io.Serializable;

public class Content implements Serializable {
     private ContentType contentType;

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }
}
