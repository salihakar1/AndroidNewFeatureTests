package com.example.androidnewfeaturestest.retrofittest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created By salih 8.06.2024
 */


public class MyRetrofitClient {
    private static MyRetrofitClient instance = null;
    private final MyApi myApi;

    private MyRetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyApi.base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(MyApi.class);
    }

    public static synchronized MyRetrofitClient getInstance() {
        if (instance == null) {
            instance = new MyRetrofitClient();
        }
        return instance;
    }

    public MyApi getMyApi() {
        return myApi;
    }

// /
}
