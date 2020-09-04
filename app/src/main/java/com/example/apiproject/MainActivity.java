package com.example.apiproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView postrecyclerView;
    private PostAdapter postAdapter;
    private CommentAdapter commentAdapter;

    ArrayList<Post> posts = new ArrayList<>();
    ArrayList<Comments> comments = new ArrayList<>();
    TextView createdPostText;

    Button postButton;
    Button commentButton;
    Button createdPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        postrecyclerView = findViewById(R.id.postRecyclerView);
        postrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        postButton = findViewById(R.id.PostButton);
        commentButton = findViewById(R.id.CommentButton);
        createdPost = findViewById(R.id.CreatedPostButton);


postButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
      PostData();
      createdPostText.setVisibility(View.INVISIBLE);
    }
});


commentButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        createdPostText=findViewById(R.id.createdPostData);
        CommentData();
        createdPostText.setVisibility(View.INVISIBLE);
    }
});
createdPost.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        CreatedPost();
        postrecyclerView.setVisibility(View.INVISIBLE);

    }
});
    }

    private void CreatedPost() {

        Post createdpost = new Post(1, "Hello World", "This is the description of Created Post");
        MyInterface myInterface = APIClient.getClient().create(MyInterface.class);
        myInterface.createPost(createdpost).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()) {
                    createdPostText=findViewById(R.id.createdPostData);
                    createdPostText.setText(String.valueOf(response.code()));
                    showCreatedPost(response.body());
                }

                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void showCreatedPost(Post post){
        createdPostText=findViewById(R.id.createdPostData);
        createdPostText.append("\n"+"user id : "+ post.getUserId()+"\n");
        createdPostText.append("id : "+ post.getId()+"\n");
        createdPostText.append("tittle : "+ post.getTitle()+"\n");
        createdPostText.append("body : "+ post.getBody()+"\n");
    }


    private void PostData(){
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

    private void CommentData(){
        MyInterface myInterface = APIClient.getClient().create(MyInterface.class);
        Map<String,String> parameters = new HashMap<>();
        parameters.put("postId","8");
        parameters.put("_sort","id");
        parameters.put("_order","asc");
        myInterface.getComments(parameters).enqueue(new Callback<List<Comments>>() {
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


}