package com.example.apiproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface MyInterface {



    @GET("posts/1/comments")
    Call<List<Comments>> getComments();


    @GET("posts")
    Call<List<Post>> getPosts();

}
