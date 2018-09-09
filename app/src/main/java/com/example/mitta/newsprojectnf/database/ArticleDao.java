package com.example.mitta.newsprojectnf.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ArticleDao {

    // Return all the articles from the database
    @Query("SELECT * FROM Article")
    LiveData<List<Article>> getAllArticles();

    // Insert a list of articles in the database
    @Insert(onConflict = REPLACE)
    void insertArticles(List<Article> articles);

}
