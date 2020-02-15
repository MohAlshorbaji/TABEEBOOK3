package com.example.tabeebook;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    TextView De;
    TextView ti;
    ImageView postimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        De = findViewById(R.id.post_detail_desc);
        ti = findViewById(R.id.tv1);
        postimg=findViewById(R.id.post_image);
        String title = getIntent().getStringExtra("Title");
        String Desc = getIntent().getStringExtra("Desc");
        String PostImg = getIntent().getStringExtra("UserImg");
        ti.setText(title);
        De.setText(Desc);
    }
}
