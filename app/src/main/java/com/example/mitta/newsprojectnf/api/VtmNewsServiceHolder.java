package com.example.mitta.newsprojectnf.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VtmNewsServiceHolder {

    private static VtmNewsServiceHolder INSTANCE;
    private VtmNewsService service;

    // Base URL
    private static final String BASE_URL = "https://nieuws.vtm.be/feed/";


    private VtmNewsServiceHolder(){
        service = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(VtmNewsService.class);
    }

    public static VtmNewsServiceHolder getInstance(){

        if(INSTANCE == null){
            INSTANCE = new VtmNewsServiceHolder();
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }

    public VtmNewsService getService() {
        return service;
    }
}
