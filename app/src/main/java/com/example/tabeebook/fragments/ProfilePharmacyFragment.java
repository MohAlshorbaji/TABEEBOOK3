package com.example.tabeebook.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tabeebook.EditDataActivity;
import com.example.tabeebook.LoginActivity;
import com.example.tabeebook.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class ProfilePharmacyFragment extends Fragment {

    ImageView userImg;
    TextView userEmail, username;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference pharmacyRef = db.collection("pharmacies");

    public ProfilePharmacyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile_pharmacy, container, false);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        userImg = root.findViewById(R.id.user_image_profile);
        userEmail = root.findViewById(R.id.email_show_user);
        username = root.findViewById(R.id.show_user_name);

        updateProfilePharmacist();


        return root;
    }

    private void updateProfilePharmacist() {

        DocumentReference pharmacyDocRef = pharmacyRef.document(currentUser.getUid());
        pharmacyDocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot document = task.getResult();
                if (document != null) {
                    username.setText(document.getString("name"));

                        Glide.with(getActivity()).load(document.getString("image")).into(userImg);
                    }
                }

        });
        userEmail.setText(currentUser.getEmail());

    }


}
