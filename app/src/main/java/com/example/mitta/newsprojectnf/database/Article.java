package com.example.mitta.newsprojectnf.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/*
The database table Article. Stores all articles' Unique ID, Title, Timestamp, ThumbURL, Medium Image Url, Text
 */

@Entity
public class Article {

    @PrimaryKey(autoGenerate = true)
    public int id;

    private String title;

    private String timestamp;

    private String thumbUrl;

    private String imageUrl;

    private String text;

    public Article(String title, String timestamp, String thumbUrl, String imageUrl, String text){
        this.title = title;
        this.timestamp = timestamp;
        this.thumbUrl = thumbUrl;
        this.imageUrl = imageUrl;
        this.text = text;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getText() {
        return text;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public void setImageUrl(String mediumImageUrl) {
        this.imageUrl = mediumImageUrl;
    }

    public void setText(String text) {
        this.text = text;
    }
}
