package com.example.tabeebook.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
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
import com.example.tabeebook.adapters.LaboratoryAdapter;
import com.example.tabeebook.adapters.PharmacyAdapter;
import com.example.tabeebook.models.Laboratory;
import com.example.tabeebook.models.Pharmacy;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class LaboratoryFragment extends Fragment {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference labRef = db.collection("laboratory");

    LaboratoryAdapter laboratoryAdapter;
    List<Laboratory> laboratoryList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    EditText edSearch;
    Button citySearchFilter;

    TextView toolbarTitle;
    ImageView toolbarImg;
    private Toolbar toolbar;



    public LaboratoryFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_laboratory, container, false);

        toolbar = root.findViewById(R.id.toolbar);
        toolbarTitle = root.findViewById(R.id.toolbar_title);
        toolbarImg = root.findViewById(R.id.back_arrow);

        toolbarTitle.setText("مختبرات التحاليل الطبية");

        toolbarImg.setVisibility(View.GONE);

        recyclerView = root.findViewById(R.id.rv_lab);
        recyclerView.hasFixedSize();
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        edSearch = root.findViewById(R.id.ed_search_lab);
        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                laboratoryAdapter.getFilter().filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        citySearchFilter = root.findViewById(R.id.city_searchFilter_lab);
        citySearchFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle("فلتر البحث حسب المكان");
                builder.setItems(new CharSequence[]
                                {"غزة", "خانيونس", "رفح", "بيت لاهيا","بيت حانون","دير البلح","جباليا"},
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
                                        edSearch.setText("بيت لاهيا");
                                        break;
                                    case 4:
                                        edSearch.setText("بيت حانون");
                                        break;
                                    case 5:
                                        edSearch.setText("دير البلح");
                                        break;
                                    case 6:
                                        edSearch.setText("جباليا");
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
        labRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot documentSnapshot : task.getResult()) {
                    Laboratory laboratory= new Laboratory(
                            documentSnapshot.getString("name"),
                            documentSnapshot.getString("laboratoryUserID"),
                            documentSnapshot.getString("laboratoryEmail"),
                            documentSnapshot.getString("address"),
                            documentSnapshot.getString("phoneNumber"),
                            documentSnapshot.getString("opeiningHours"),
                            documentSnapshot.getString("image")
                    );
                    laboratoryList.add(laboratory);
                }
               laboratoryAdapter = new LaboratoryAdapter(getContext(),laboratoryList);
                recyclerView.setAdapter(laboratoryAdapter);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }



}
