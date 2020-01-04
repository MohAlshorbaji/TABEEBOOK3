package com.example.tabeebook.fragments;


import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tabeebook.R;
import com.example.tabeebook.adapters.ClinicAdapter;
import com.example.tabeebook.models.Clinic;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClinicsFragment extends Fragment {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference clinicRef = db.collection("clinics");

    ClinicAdapter clinicAdapter;
    List<Clinic> clinicList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    EditText edSearch;
    Button citySearchFilter;
    Button clinicTypeSearchFilter;

    TextView toolbarTitle;
    ImageView toolbarImg;
    private Toolbar toolbar;


    public ClinicsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_clinics, container, false);


        toolbar = root.findViewById(R.id.toolbar);
        toolbarTitle = root.findViewById(R.id.toolbar_title);
        toolbarImg = root.findViewById(R.id.back_arrow);

        toolbarTitle.setText("العيادات");

        toolbarImg.setVisibility(View.GONE);


        recyclerView = root.findViewById(R.id.rv_clinics);
        recyclerView.hasFixedSize();
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        edSearch = root.findViewById(R.id.ed_search);
        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                clinicAdapter.getFilter().filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        citySearchFilter = root.findViewById(R.id.city_searchFilter);
        citySearchFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle("فلترت البحث حسب المكان");
                builder.setItems(new CharSequence[]
                                {"غزة", "خانيونس", "رفح", "دير البلح"},
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // The 'which' argument contains the index position
                                // of the selected item
                                switch (which) {
                                    case 0:
                                        edSearch.setText("غزة");
                                        break;
                                    case 1:
                                        edSearch.setText("خانيونس");
                                        break;
                                    case 2:
                                        edSearch.setText("رفح");
                                        break;
                                    case 3:
                                        edSearch.setText("دير البلح");
                                        break;
                                }
                            }
                        });
                builder.setNegativeButton("اغلاق", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create().show();
            }
        });
        clinicTypeSearchFilter = root.findViewById(R.id.clinic_type_searchFilter);
        clinicTypeSearchFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle("فلترت البحث حسب المكان");
                builder.setItems(new CharSequence[]
                                {"اسنان", "عام", "باطنة", "جراحة"},
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // The 'which' argument contains the index position
                                // of the selected item
                                switch (which) {
                                    case 0:
                                        edSearch.setText(edSearch.getText() + " اسنان");
                                        break;
                                    case 1:
                                        edSearch.setText(edSearch.getText() + " عام");
                                        break;
                                    case 2:
                                        edSearch.setText(edSearch.getText() + " باطنة");
                                        break;
                                    case 3:
                                        edSearch.setText(edSearch.getText() + " جراحة");
                                        break;
                                }
                            }
                        });
                builder.setNegativeButton("اغلاق", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create().show();
            }
        });

        showData();


        return root;
    }

    private void showData() {
        clinicRef.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot documentSnapshot : task.getResult()) {
                            Clinic clinic = new Clinic(
                                    documentSnapshot.getString("name"),
                                    documentSnapshot.getString("clinicUserID"),
                                    documentSnapshot.getString("clinicEmailID"),
                                    documentSnapshot.getString("address"),
                                    documentSnapshot.getString("phoneNumber"),
                                    documentSnapshot.getString("opeiningHours"),
                                    documentSnapshot.getString("type"),
                                    documentSnapshot.getString("owner"),
                                    documentSnapshot.getString("image"));
                            clinicList.add(clinic);
                        }
                        clinicAdapter = new ClinicAdapter(getContext(), clinicList);
                        recyclerView.setAdapter(clinicAdapter);

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

}
