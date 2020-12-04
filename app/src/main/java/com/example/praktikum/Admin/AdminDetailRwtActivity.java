package com.example.praktikum.Admin;

import android.os.Bundle;
import android.widget.TextView;

import com.example.praktikum.R;

import androidx.appcompat.app.AppCompatActivity;

public class AdminDetailRwtActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_detail_rwt);

        textView = findViewById(R.id.keluhanpasiennih);

        getIncomingExtra();

    }

    private void getIncomingExtra() {

        if(getIntent().hasExtra("keluhan")){

            String keluhanPasien = getIntent().getStringExtra("keluhan");

            setDataActivity(keluhanPasien);

        }


    }

    private void setDataActivity(String keluhanPasien) {

        textView.setText(keluhanPasien);
    }

}
