package com.example.tabeebook;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;

public class FirebaseViewHolder  extends RecyclerView.ViewHolder {
    public TextView title;
    ImageView PostImage;
    ImageView UserImage;

    public FirebaseViewHolder(@NonNull View itemView) {
        super(itemView);
        PostImage = itemView.findViewById(R.id.row_post_img);
        UserImage = itemView.findViewById(R.id.row_post_profile_img);
        title = itemView.findViewById(R.id.row_post_title);
    }
}