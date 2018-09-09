package com.example.mitta.newsprojectnf;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.mitta.newsprojectnf.viewmodel.ArticleListViewModel;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ArticleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("News");

        // Recycler View
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        ArticleListViewModel viewModel = ViewModelProviders.of(this).get(ArticleListViewModel.class);
        viewModel.loadArticles();

        // Adapter
        adapter = new ArticleAdapter(new ArrayList<>(), article -> {
            Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
            intent.putExtra("title",article.getTitle());
            intent.putExtra("text",article.getText());
            intent.putExtra("imageUrl",article.getImageUrl());
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);


        // Observer
        viewModel.getArticleList().observe(MainActivity.this, articles -> adapter.addItems(articles));


    }



}
