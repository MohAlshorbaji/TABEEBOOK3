package com.example.tabeebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class PharmacyDetailsActivity extends AppCompatActivity {

    TextView pharmacyName, pharmacyAdderss, pharmacyPhoneNumber
            , pharmacyOwner, toolbarTitle,pharmacyOpeningHours;
    ImageView pharmacyImage, toolbarImg;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_details);

        toolbar = findViewById(R.id.toolbar);
        toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarImg = findViewById(R.id.back_arrow);

        pharmacyName = findViewById(R.id.pharmacy_name);
        pharmacyAdderss = findViewById(R.id.pharmacy_address);
        pharmacyPhoneNumber = findViewById(R.id.pharmacy_phone_number);
        pharmacyOwner = findViewById(R.id.pharmacy_owner);
        pharmacyOpeningHours = findViewById(R.id.pharmacy_opening_hours);
        pharmacyImage = findViewById(R.id.pharmacy_image);

        pharmacyName.setText(getIntent().getStringExtra("pharmacyName"));
        pharmacyAdderss.setText(getIntent().getStringExtra("pharmacyAddress"));
        pharmacyPhoneNumber.setText(getIntent().getStringExtra("pharmacyPhoneNumber"));
        pharmacyOwner.setText(getIntent().getStringExtra("pharmacyOwner"));
        pharmacyOpeningHours.setText(getIntent().getStringExtra("pharmacyOpeningHours"));
        toolbarTitle.setText(getIntent().getStringExtra("pharmacyName"));
        Glide.with(PharmacyDetailsActivity.this).load(getIntent().getStringExtra("pharmacyImage")).into(pharmacyImage);
        toolbarImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
