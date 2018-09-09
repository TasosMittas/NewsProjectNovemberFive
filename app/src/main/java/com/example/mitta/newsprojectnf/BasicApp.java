package com.example.mitta.newsprojectnf;

import android.app.Application;

import com.example.mitta.newsprojectnf.api.VtmNewsService;
import com.example.mitta.newsprojectnf.api.VtmNewsServiceHolder;
import com.example.mitta.newsprojectnf.database.AppDatabase;

/*
Android Application class. Used for accessing singletons.
*/

public class BasicApp extends Application {

    private AppExecutors appExecutors;

    public void onCreate() {
        super.onCreate();
        appExecutors = new AppExecutors();
    }

    public AppDatabase getAppDatabase(){
        return AppDatabase.getDatabase(this);
    }

    public VtmNewsService getVtmNewsService(){
        return VtmNewsServiceHolder.getInstance().getService();
    }

    public ArticleRepository getRepository(){
        return ArticleRepository.getInstance(getAppDatabase(), getVtmNewsService(), appExecutors);
    }
}
