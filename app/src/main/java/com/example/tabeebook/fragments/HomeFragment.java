package com.example.tabeebook.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tabeebook.FirebaseViewHolder;
import com.example.tabeebook.Main2Activity;
import com.example.tabeebook.R;
import com.example.tabeebook.adapters.PostAdapter;
import com.example.tabeebook.models.Post;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    RecyclerView re;
    View v;
    private ArrayList<Post> arrayList;
    ProgressDialog progress;
    private FirebaseRecyclerAdapter<Post, FirebaseViewHolder> adapter;
    private FirebaseRecyclerOptions<Post> options;
    private DatabaseReference databaseReference;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home,container,false);
        progress = new ProgressDialog(getActivity());
        progress.setTitle("Loading");
        progress.setMessage("Syncing");
        progress.setCancelable(false);
        progress.show();
        re= view.findViewById(R.id.postRV);
        re.setHasFixedSize(true);
        re.setLayoutManager(new LinearLayoutManager(getContext()));
        arrayList = new ArrayList<Post>();
        options = new FirebaseRecyclerOptions.Builder<Post>().setQuery(databaseReference,Post.class).build();


        databaseReference = FirebaseDatabase.getInstance().getReference().child("Posts");
        databaseReference.keepSynced(true);
        adapter = new FirebaseRecyclerAdapter<Post, FirebaseViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseViewHolder holder, int position, @NonNull final Post model) {
                holder.title.setText(model.getTitle());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), Main2Activity.class);
                        intent.putExtra("title",model.getTitle());
                        startActivity(intent);

                    }
                });

            }

            @NonNull
            @Override
            public FirebaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new FirebaseViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.row_post_item,parent,false));
            }
        };
        re.setAdapter(adapter);
        progress.dismiss();
        return view;

    }







}

