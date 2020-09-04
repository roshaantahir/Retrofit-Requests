package com.example.apiproject;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface MyInterface {



    @GET("posts")
    Call<List<Post>> getPosts();

//    @GET("posts/1/comments")
//    Call<List<Comments>> getComments();

//    @GET("posts/{id}/comments")
//    Call<List<Comments>> getComments(@Path("id") int userid );

//    @GET("comments")
//    Call<List<Comments>> getComments(@Query("postId") int postid);

//    @GET("comments")
//    Call<List<Comments>> getComments(@Query("postId") int postid,
//                                     @Query("_sort") String sortBy,
//                                     @Query("_order") String orderBy);

    @GET("comments")
       Call<List<Comments>> getComments(@QueryMap Map<String,String> params);

    @GET("comments")
    Call<List<Comments>> getComments(@Query("postId") int postid,
                                     @Query("_sort") String sortBy,
                                     @Query("_order") String orderBy);

    @POST("posts")
    Call<Post> createPost(@Body Post post);
}
