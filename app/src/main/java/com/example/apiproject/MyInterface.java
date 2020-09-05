package com.example.apiproject;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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
    Call<List<Comments>> getComments(@QueryMap Map<String, String> params);

    @GET("comments")
    Call<List<Comments>> getComments(@Query("postId") int postid,
                                     @Query("_sort") String sortBy,
                                     @Query("_order") String orderBy);

//    @POST("posts")
//    Call<Post> createPost(@Body Post post);

//    @FormUrlEncoded
//    @POST("posts")
//    Call<Post> createPost(@Field("userId") int userId,
//                          @Field("title") String title,
//                          @Field("body") String description);
    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost( @FieldMap Map<String,String> postMap);

//    @PUT("posts/{id}")
//        Call<Post> putPost(@Path("id") int Id, @Body Post post);


    @PATCH("posts/{id}")
    Call<Post> patchPost(@Path("id") int Id, @Body Post post);

    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int Id);

}
