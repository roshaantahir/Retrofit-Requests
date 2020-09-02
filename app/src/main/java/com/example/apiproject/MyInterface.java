package com.example.apiproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyInterface {



//    @GET("posts/1/comments")
//    Call<List<Comments>> getComments();


    @GET("posts")
    Call<List<Post>> getPosts();


    @GET("posts/{id}/comments")
    Call<List<Comments>> getComments(@Path("id") int userid );

}
