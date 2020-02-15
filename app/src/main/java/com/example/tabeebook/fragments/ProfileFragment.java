package com.example.tabeebook.fragments;


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
import com.example.tabeebook.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    ImageView userImg;
    TextView userEmail, username,type,duration,mobile;
    private Button logoutUser,editData;

    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference clinicsRef = db.collection("clinics");


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
        duration = root.findViewById(R.id.duration);
        mobile = root.findViewById(R.id.mobile_desc);




        updateProfileDoctor();


        return root;
    }
    private void updateProfileDoctor() {
        DocumentReference clinicDocRef = clinicsRef.document(currentUser.getUid());
        clinicDocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot document = task.getResult();
                if (document != null) {
                    username.setText(document.getString("name"));
                    Glide.with(getActivity()).load(document.getString("image")).into(userImg);
                    type.setText((document.getString("type")));
                    duration.setText(document.getString("opeiningHours"));
                    mobile.setText(document.getString("phoneNumber"));
                }
            }
        });
        userEmail.setText(currentUser.getEmail());


    }


}
