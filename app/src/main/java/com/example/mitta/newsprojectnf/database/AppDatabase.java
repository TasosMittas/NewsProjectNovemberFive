package com.example.mitta.newsprojectnf.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

// The application's database

@Database(entities = {Article.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{

    private static AppDatabase INSTANCE;
    public static final String DATABASE_NAME = "article_db";

    public static AppDatabase getDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = buildDatabase(context.getApplicationContext());
        }
        return INSTANCE;
    }

    private static AppDatabase buildDatabase(Context context){
        return Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).build();
    }

    public abstract ArticleDao articleDao();
}
