package com.example.mitta.newsprojectnf;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.example.mitta.newsprojectnf.api.VtmNewsService;
import com.example.mitta.newsprojectnf.api.model.FeedModel;
import com.example.mitta.newsprojectnf.database.AppDatabase;
import com.example.mitta.newsprojectnf.database.Article;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
    Repository handling the work with articles.
 */
public class ArticleRepository {

    private static ArticleRepository INSTANCE;
    private final AppDatabase appDatabase;
    private VtmNewsService service;
    private final AppExecutors executors;

    public static final int ARTICLES_COUNT = 25;


    private ArticleRepository(final AppDatabase appDatabase, final VtmNewsService service, final AppExecutors executors){
        this.appDatabase = appDatabase;
        this.service = service;
        this.executors = executors;
    }

    public static ArticleRepository getInstance(final AppDatabase appDatabase, final VtmNewsService service, final AppExecutors executors){

        if(INSTANCE == null){
            INSTANCE = new ArticleRepository(appDatabase, service, executors);
        }

        return INSTANCE;
    }


    public LiveData<List<Article>> getAllArticles(){
        return appDatabase.articleDao().getAllArticles();
    }


    // Fetches the articles and inserts them into the database as a list
    public void loadArticles(){

        service.getFeed(ARTICLES_COUNT).enqueue(new Callback<FeedModel>() {
            @Override
            public void onResponse(Call<FeedModel> call, Response<FeedModel> response) {
                final List<Article> articleList = new ArrayList<>();

                for(int i = 0; i < ARTICLES_COUNT ; i++){
                    String title = response.body().response.items.get(i).title;
                    String text = response.body().response.items.get(i).text;
                    String timestamp = response.body().response.items.get(i).created.formatted;
                    String thumbUrl = response.body().response.items.get(i).image.thumb;
                    String image = response.body().response.items.get(i).image.medium;
                    Log.i("FetchLog", "article no" + i +" text: " + title);
                    Article article = new Article(title, timestamp, thumbUrl, image, text);
                    articleList.add(article);
                }

                // If user is online, delete the previous entries and insert the new ones
                if(articleList.size() > 0){
                    executors.diskIO().execute( ()-> appDatabase.clearAllTables());
                }
                executors.diskIO().execute( () -> appDatabase.articleDao().insertArticles(articleList));
            }

            @Override
            public void onFailure(Call<FeedModel> call, Throwable t) {

            }
        });
    }
}
