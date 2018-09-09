package com.example.mitta.newsprojectnf.api;

import com.example.mitta.newsprojectnf.api.model.FeedModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VtmNewsService {

    @GET("articles?count=25&fields=video,text,tags,image,html")
    Call<FeedModel> getFeed(@Query("limit") int articlesCount);

}
