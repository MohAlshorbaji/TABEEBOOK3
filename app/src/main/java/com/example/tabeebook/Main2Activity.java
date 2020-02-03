package com.example.tabeebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    TextView De;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        De = findViewById(R.id.tv1);
        String Desc = getIntent().getStringExtra("Desc");
        De.setText(Desc);




    }
}
