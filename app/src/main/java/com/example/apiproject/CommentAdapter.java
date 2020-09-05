package com.example.apiproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private ArrayList<Comments> comments = new ArrayList<>();
    private Context context;

    public CommentAdapter(Context context, ArrayList<Comments> comments) {
        this.comments = comments;
        this.context = context;
    }

    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, int position) {

        holder.tittle.setText(comments.get(position).getEmail());
        holder.id.setText("id : " + comments.get(position).getId().toString());
        holder.postid.setText("postId : " + comments.get(position).getPostId().toString());
        holder.disc.setText(comments.get(position).getBody());


    }

    @Override
    public int getItemCount() {
        return comments.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tittle;
        private TextView id;
        private TextView postid;
        private TextView disc;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tittle = itemView.findViewById(R.id.Tittle);
            id = itemView.findViewById(R.id.Id);
            postid = itemView.findViewById(R.id.PostId);
            disc = itemView.findViewById(R.id.Disc);
        }
    }
}
