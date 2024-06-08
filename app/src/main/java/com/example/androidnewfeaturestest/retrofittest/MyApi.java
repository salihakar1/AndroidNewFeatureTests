package com.example.androidnewfeaturestest.retrofittest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created By salih 8.06.2024
 */


public interface MyApi {

    String base_URL = "https://simplifiedcoding.net/demos/";
    @GET("marvel")
    Call<List<MyModel>> getHeroes();

// /
}
