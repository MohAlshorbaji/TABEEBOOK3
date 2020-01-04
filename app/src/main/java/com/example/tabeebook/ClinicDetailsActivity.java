package com.example.tabeebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ClinicDetailsActivity extends AppCompatActivity {

    TextView clinicName, clinicAdderss, clinicPhoneNumber
            , clinicOwner, clinicType, toolbarTitle,clinicOpeningHours;
    ImageView clinicImage, toolbarImg;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_details);

        toolbar = findViewById(R.id.toolbar);
        toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarImg = findViewById(R.id.back_arrow);
        //setSupportActionBar(toolbar);


        clinicName = findViewById(R.id.clinic_name);
        clinicAdderss = findViewById(R.id.clinic_address);
        clinicPhoneNumber = findViewById(R.id.clinic_phone_number);
        clinicOwner = findViewById(R.id.clinic_owner);
        clinicType = findViewById(R.id.clinic_type);
        clinicOpeningHours = findViewById(R.id.clinic_opening_hours);
        clinicImage = findViewById(R.id.clinic_image);




        clinicName.setText(getIntent().getStringExtra("clinicName"));
        clinicAdderss.setText(getIntent().getStringExtra("clinicAddress"));
        clinicPhoneNumber.setText(getIntent().getStringExtra("clinicPhoneNumber"));
        clinicOwner.setText(getIntent().getStringExtra("clinicOwner"));
        clinicType.setText(getIntent().getStringExtra("clinicType"));
        clinicOpeningHours.setText(getIntent().getStringExtra("clinicOpeningHours"));
        toolbarTitle.setText(getIntent().getStringExtra("clinicName"));
        Glide.with(ClinicDetailsActivity.this).load(getIntent().getStringExtra("clinicImage")).into(clinicImage);
        toolbarImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
