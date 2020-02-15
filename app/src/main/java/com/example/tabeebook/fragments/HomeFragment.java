package com.example.tabeebook.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tabeebook.FirebaseViewHolder;
import com.example.tabeebook.Main2Activity;
import com.example.tabeebook.R;
import com.example.tabeebook.models.Post;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class HomeFragment extends Fragment {


    RecyclerView rv;
    DatabaseReference databaseReference;
    FloatingActionButton fb ;



    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Posts");
        rv = root.findViewById(R.id.postRV);
        fb = root.findViewById(R.id.fab);
        loadRecyclePosts();



        return root;
    }

    private void loadRecyclePosts(){
        FirebaseRecyclerOptions<Post> options;
        FirebaseRecyclerAdapter<Post,FirebaseViewHolder> adapter;

        options = new FirebaseRecyclerOptions.Builder<Post>()
                .setQuery(databaseReference, Post.class).build();

        adapter = new FirebaseRecyclerAdapter<Post, FirebaseViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseViewHolder firebaseViewHolder, int i, @NonNull final Post post) {
                firebaseViewHolder.title.setText(post.getTitle());
                firebaseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(),Main2Activity.class);
                        intent.putExtra("Desc",post.getDescription());
                        intent.putExtra("Title",post.getTitle());
                        intent.putExtra("UserImg",post.getPicture());
                        startActivity(intent);

                    }
                });

                //Toast.makeText(getContext(), "" + payments.getPaymentID(), Toast.LENGTH_SHORT).show();
            }

            @NonNull
            @Override
            public FirebaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_post_item, parent, false);
                return new FirebaseViewHolder(view);
            }
        };

        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.startListening();
        rv.setAdapter(adapter);

    }


}
