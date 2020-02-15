package com.example.tabeebook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tabeebook.models.Clinic;
import com.example.tabeebook.models.Laboratory;
import com.example.tabeebook.models.Pharmacy;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    final static String placehoder = "https://firebasestorage.googleapis.com/v0/b/tabeebook-c548d.appspot.com/o/doctrouser.png?alt=media&token=170b3af4-709c-4a3c-8317-2eed8479da6d";

    //widgets:
    EditText regUsername, regEmail, regPassword;
    Button regRegister;
    ProgressBar progressBar;
    Spinner chooseUser;
    ImageView imgUserPhoto;
    ImageView addProfileImage;

    static int REQUESCODE = 1;
    Uri pickedImageUri;

    //vars:
    FirebaseAuth mAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference clinicsRef = db.collection("clinics");
    private CollectionReference pharmaciesRef = db.collection("pharmacies");
    private CollectionReference LaboratoryRef = db.collection("laboratory");
    String selectedUser;
    private Clinic mClinic;
    private Pharmacy mPharmacy;
    private Laboratory mLaboratory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        pickedImageUri = Uri.parse(placehoder);

        regUsername = findViewById(R.id.register_username);
        regEmail = findViewById(R.id.register_email);
        regPassword = findViewById(R.id.register_password);
        regRegister = findViewById(R.id.register_btn);
        progressBar = findViewById(R.id.register_pb);
        chooseUser = findViewById(R.id.choose_user);
        addProfileImage = findViewById(R.id.user_profile_img);
        //imgUserPhoto = findViewById(R.id.user_profile_img);

        selectedUser = (String) chooseUser.getSelectedItem();
        progressBar.setVisibility(View.INVISIBLE);

        mAuth = FirebaseAuth.getInstance();
        mClinic = new Clinic();
        mPharmacy = new Pharmacy();
        regRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                regRegister.setVisibility(View.INVISIBLE);

                final String Username = regUsername.getText().toString();
                final String Email = regEmail.getText().toString();
                final String Password = regPassword.getText().toString();


                try {
                    if (Username.length() == 0 || Email.length() == 0 || Password.length() == 0) {
                        showMessage("please fill the fields");
                        regRegister.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.INVISIBLE);
                    } else {
                        createUserAccount(Username, Email, Password);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        addProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openGallery();

            }
        });
    }

    private void openGallery() {
        Intent gallaryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        gallaryIntent.setType("image/*");
        startActivityForResult(gallaryIntent, REQUESCODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUESCODE && data != null) {


            pickedImageUri = data.getData();
            addProfileImage.setImageURI(pickedImageUri);
        }
    }

    private void createUserAccount(final String name, final String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    showMessage("Authentication Failed");
                } else {
                    if (chooseUser.getSelectedItemPosition() == 0) {
                        final FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        String clinicID = firebaseUser.getUid();
                        mClinic.setName(name);
                        mClinic.setClinicEmailID(email);
                        mClinic.setImage(pickedImageUri.toString());

                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("name", name);
                        hashMap.put("clinicEmailID", email);
                        hashMap.put("address", " ");
                        hashMap.put("phoneNumber", " ");
                        hashMap.put("opeiningHours", " ");
                        hashMap.put("type", " ");
                        hashMap.put("owner", " ");
                        hashMap.put("image", pickedImageUri.toString());

                        clinicsRef.document(clinicID).set(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {

                                    startActivity(new Intent(RegisterActivity.this, EditDataActivity.class));
                                    finish();
                                    updateUserInfo(name, pickedImageUri, mAuth.getCurrentUser());
                                    showMessage("Registration Complete");
                                } else {
                                    showMessage(task.getException().getMessage());
                                }
                            }
                        });
                    } else {
                        final FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        String pharmacyID = firebaseUser.getUid();
                        mPharmacy.setName(name);
                        mPharmacy.setPharmacyEmail(email);
                        mPharmacy.setImage(pickedImageUri.toString());


                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("name", name);
                        hashMap.put("pharmacyEmail", email);
                        hashMap.put("address", " ");
                        hashMap.put("phoneNumber", " ");
                        hashMap.put("opeiningHours", " ");
                        hashMap.put("type", " ");
                        hashMap.put("owner", " ");
                        hashMap.put("image", pickedImageUri.toString());

                        pharmaciesRef.document(pharmacyID).set(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(RegisterActivity.this, MainActivityPharmacy.class));
                                    finish();
                                    updateUserInfo(name, pickedImageUri, mAuth.getCurrentUser());
                                    showMessage("Registration Complete");
                                } else {
                                    showMessage(task.getException().getMessage());
                                }

                            }
                        });

                    }
                }
            }
        });
    }


    private void updateUserInfo(final String name, Uri pickedImageUri, final FirebaseUser currentUser) {


        StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("users_pictures");
        final StorageReference imageFilePath = mStorage.child(pickedImageUri.getLastPathSegment());
        imageFilePath.putFile(pickedImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {


                        UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder().setDisplayName(name)
                                .setPhotoUri(uri).build();
                        currentUser.updateProfile(profileUpdate)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            showMessage("Registration Complete");

                                        }
                                    }
                                });
                    }
                });
            }
        });
    }

    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}

