package com.example.praktikum.Admin;

import android.os.Bundle;
import android.widget.TextView;

import com.example.praktikum.Database.RoomDB;
import com.example.praktikum.Model.User;
import com.example.praktikum.R;

import androidx.appcompat.app.AppCompatActivity;

public class AdminDetailListusrActivity extends AppCompatActivity {

    TextView adminName, adminEmail, adminMobile, adminGender, adminAddress, adminBirthdate;
    int idUser;
    RoomDB database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_detail_listusr);

        init();
        getIncomingExtra();
        getUser();


    }

    private void init(){
        adminName = (TextView)findViewById(R.id.Nameuser1);
        adminEmail = (TextView)findViewById(R.id.Emailuser1);
        adminMobile = (TextView)findViewById(R.id.Phoneuser1);
        adminGender = (TextView)findViewById(R.id.Genderuser1);
        adminAddress = (TextView)findViewById(R.id.Addressuser1);
        adminBirthdate = (TextView)findViewById(R.id.birthuser1);
    }

    private void getIncomingExtra() {
        if(getIntent().hasExtra("id")){

            idUser = getIntent().getIntExtra("id",0);

        }
    }

    private void getUser(){
        database = RoomDB.getInstance(getApplicationContext());
        User user = database.userDao().getUser(idUser);
        adminName.setText(user.getName());
        adminEmail.setText(user.getEmail());
        adminMobile.setText(user.getMobile());
        adminGender.setText(user.getGender());
        adminAddress.setText(user.getAddress());
        adminBirthdate.setText(user.getBirthdate());
    }
}
