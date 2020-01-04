package com.example.tabeebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditDataActivity extends AppCompatActivity {

    EditText editName, editAddress, editPhoneNumber, editOpeiningHours, editTypeOfClinic, editclinicOwner;
    Spinner choosetype;
    TextView toolbarTitle;
    ImageView toolbarImg;
    private Toolbar toolbar;
    FirebaseAuth mAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference clinicsRef = db.collection("clinics");
    private CollectionReference pharmaciesRef = db.collection("pharmacies");

    Button saveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        toolbar = findViewById(R.id.toolbar);
        toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarImg = findViewById(R.id.back_arrow);
        //setSupportActionBar(toolbar);

        toolbarTitle.setText("تحديث البيانات");

        toolbarImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        editName = findViewById(R.id.edit_name);
        editAddress = findViewById(R.id.edit_address);
        editPhoneNumber = findViewById(R.id.edit_phoneNumber);
        editOpeiningHours = findViewById(R.id.edit_opeiningHours);
        editTypeOfClinic = findViewById(R.id.edit_typeOfClinic);
        editclinicOwner = findViewById(R.id.edit_clinic_owner);
        choosetype = findViewById(R.id.choose_type);
        mAuth = FirebaseAuth.getInstance();

        saveData = findViewById(R.id.save_btn);

        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDate(view);
            }
        });


    }


    private void updateDate(View view) {

        String clinicName = editName.getText().toString();
        String clinicAddress = editAddress.getText().toString();
        String clinicPhoneNumber = editPhoneNumber.getText().toString();
        String clinicOpeiningHours = editOpeiningHours.getText().toString();
        String clinicTypeOfClinic = editTypeOfClinic.getText().toString();
        String clinicOwner = editclinicOwner.getText().toString();


        if (choosetype.getSelectedItemPosition() == 0) {
            Map<String, Object> clinic = new HashMap<>();
            final FirebaseUser firebaseUser = mAuth.getCurrentUser();
            String clinicID = firebaseUser.getUid();
            clinic.put("name", clinicName);
            clinic.put("address", clinicAddress);
            clinic.put("phoneNumber", clinicPhoneNumber);
            clinic.put("opeiningHours", clinicOpeiningHours);
            clinic.put("typeOfClinic", clinicTypeOfClinic);
            clinic.put("clinicOwner", clinicOwner);
            clinic.put("image", firebaseUser.getPhotoUrl().toString());
            clinicsRef.document(clinicID).set(clinic);
        } else {
            Map<String, Object> pharmacy = new HashMap<>();
            final FirebaseUser firebaseUser = mAuth.getCurrentUser();
            String pharmacyID = firebaseUser.getUid();
            pharmacy.put("name", clinicName);
            pharmacy.put("address", clinicAddress);
            pharmacy.put("phoneNumber", clinicPhoneNumber);
            pharmacy.put("opeiningHours", clinicOpeiningHours);
            pharmacy.put("typeOfClinic", clinicTypeOfClinic);
            pharmacy.put("clinicOwner", clinicOwner);
            pharmacy.put("image", firebaseUser.getPhotoUrl().toString());
            pharmaciesRef.document(pharmacyID).set(pharmacy);
        }
    }
}
