package com.example.tabeebook.fragments;


import android.content.Intent;
import android.os.Bundle;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    ImageView userImg;
    TextView userEmail, username,type,time;
    private Button logoutUser, editData;

    FirebaseAuth mAuth;
    FirebaseUser currentUser;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        userImg = root.findViewById(R.id.user_image_profile);
        userEmail = root.findViewById(R.id.email_show_user);
        username = root.findViewById(R.id.show_user_name);
        type = root.findViewById(R.id.clinc_type_desc);
        time = root.findViewById(R.id.time_from_to_desc);


       // logoutUser = root.findViewById(R.id.logout_user);
        //editData = root.findViewById(R.id.editDataBtn);
      //  logoutUser.setOnClickListener(new View.OnClickListener() {
         //   @Override
         //   public void onClick(View view) {
           //     FirebaseAuth.getInstance().signOut();
            //    getActivity().finish();
             //   startActivity(new Intent(getActivity(), LoginActivity.class));
           // }
      //  });


        updateProfileDoctor();
        updateProfilePharmacist();


        return root;
    }

    private void updateProfileDoctor() {
        userEmail.setText(currentUser.getEmail());
        username.setText(currentUser.getDisplayName());
        type.setText(currentUser.getPhoneNumber());
        username.setText(currentUser.getDisplayName());

        Glide.with(this).load(currentUser.getPhotoUrl()).into(userImg);

    }

    private void updateProfilePharmacist() {
        userEmail.setText(currentUser.getEmail());
        username.setText(currentUser.getDisplayName());
        type.setText(currentUser.getPhoneNumber());

        Glide.with(this).load(currentUser.getPhotoUrl()).into(userImg);

    }

}
