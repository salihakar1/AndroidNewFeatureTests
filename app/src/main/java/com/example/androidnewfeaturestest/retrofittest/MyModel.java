package com.example.androidnewfeaturestest.retrofittest;

import com.google.gson.annotations.SerializedName;

/**
 * Created By salih 8.06.2024
 */


public class MyModel {

    @SerializedName("name")
    private String name;

    public MyModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    // /
}
