package com.example.apiproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView postrecyclerView;
    private PostAdapter postAdapter;
    private CommentAdapter commentAdapter;
    ArrayList<Post> posts = new ArrayList<>();
    ArrayList<Comments> comments = new ArrayList<>();
    Button postButton;
    Button commentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        postrecyclerView = findViewById(R.id.postRecyclerView);
        postrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        postButton = findViewById(R.id.PostButton);
        commentButton = findViewById(R.id.CommentButton);

postButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        MyInterface myInterface = APIClient.getClient().create(MyInterface.class);
        myInterface.getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                posts = new ArrayList<>(response.body());
                postAdapter= new PostAdapter(MainActivity.this,posts);
                postrecyclerView.setAdapter(postAdapter);
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
});
commentButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        MyInterface myInterface = APIClient.getClient().create(MyInterface.class);
        myInterface.getComments(5).enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                comments = new ArrayList<>(response.body());
                commentAdapter = new CommentAdapter(MainActivity.this,comments);
                postrecyclerView.setAdapter(commentAdapter);

                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
});


    }






}