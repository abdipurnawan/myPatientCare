package com.example.praktikum.Admin;

import android.os.Bundle;
import android.widget.TextView;

import com.example.praktikum.R;

import androidx.appcompat.app.AppCompatActivity;

public class AdminDetailListusrActivity extends AppCompatActivity {

    TextView textnamauser, textemailusr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_detail_listusr);

        textnamauser = findViewById(R.id.Nameuser1);
        textemailusr = findViewById(R.id.Emailuser1);

        getIncomingExtra();


    }

    private void getIncomingExtra() {
        if(getIntent().hasExtra("namausr") && getIntent().hasExtra("emailusr")){


            String namaUser = getIntent().getStringExtra("namausr");
            String emailUser = getIntent().getStringExtra("emailusr");

            setDataActivity(namaUser,emailUser);

        }
    }

    private void setDataActivity(String namaUser, String emailUser) {

        textnamauser.setText(namaUser);
        textemailusr.setText(emailUser);

    }
}
