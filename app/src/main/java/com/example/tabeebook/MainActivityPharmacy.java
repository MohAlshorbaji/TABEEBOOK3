package com.example.tabeebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.tabeebook.fragments.ClinicsFragment;
import com.example.tabeebook.fragments.HomeFragment;
import com.example.tabeebook.fragments.LaboratoryFragment;
import com.example.tabeebook.fragments.PharmaciesFragment;
import com.example.tabeebook.fragments.ProfileFragment;
import com.example.tabeebook.fragments.ProfilePharmacyFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivityPharmacy extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Fragment selectedFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pharmacy);


        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();


    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.main:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.clinics:
                    selectedFragment = new ClinicsFragment();
                    break;
                case R.id.pharmacies:
                    selectedFragment = new PharmaciesFragment();
                    break;
                case R.id.profile:
                    selectedFragment = new ProfilePharmacyFragment();
                    break;
                case R.id.laboratories:
                    selectedFragment = new LaboratoryFragment();
                    break;
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            }
            return true;
        }
    };
}
