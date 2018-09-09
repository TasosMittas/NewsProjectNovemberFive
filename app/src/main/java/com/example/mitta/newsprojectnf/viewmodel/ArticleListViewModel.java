package com.example.mitta.newsprojectnf.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;

import com.example.mitta.newsprojectnf.ArticleRepository;
import com.example.mitta.newsprojectnf.BasicApp;
import com.example.mitta.newsprojectnf.database.Article;

import java.util.List;

/*
    The viewmodel for the Article List.
 */
public class ArticleListViewModel extends AndroidViewModel{

    private final LiveData<List<Article>> articleList;
    private ArticleRepository repository;
    private final MediatorLiveData<List<Article>> observableArticles;

    public ArticleListViewModel(@NonNull Application application) {
        super(application);

        repository = ( (BasicApp) application).getRepository();

        articleList = repository.getAllArticles();
        observableArticles = new MediatorLiveData<>();
        observableArticles.addSource(articleList, observableArticles::setValue);
    }

    public LiveData<List<Article>> getArticleList(){
        return observableArticles;
    }

    public void loadArticles(){
        repository.loadArticles();
    }


}
