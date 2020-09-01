package com.example.apiproject;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    static String BASE_URL = "https://jsonplaceholder.typicode.com/";



    static Retrofit getClient() {

        //Set Retrofit
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
