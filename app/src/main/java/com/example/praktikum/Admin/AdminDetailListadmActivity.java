package com.example.praktikum.Admin;

import android.os.Bundle;
import android.widget.TextView;

import com.example.praktikum.Database.RoomDB;
import com.example.praktikum.Model.PendaftaranWithUsers;
import com.example.praktikum.Model.User;
import com.example.praktikum.R;

import androidx.appcompat.app.AppCompatActivity;

public class AdminDetailListadmActivity extends AppCompatActivity {

    TextView adminName, adminEmail, adminMobile, adminGender, adminAddress, adminBirthdate;
    int idUser;
    RoomDB database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_detail_listadm);

        init();
        getIncomingExtra();
        getAdmin();

    }

    private void init(){
        adminName = (TextView)findViewById(R.id.Nameadmin1);
        adminEmail = (TextView)findViewById(R.id.Emailadmin1);
        adminMobile = (TextView)findViewById(R.id.Phoneadmin1);
        adminGender = (TextView)findViewById(R.id.Genderadmin1);
        adminAddress = (TextView)findViewById(R.id.Addressadmin1);
        adminBirthdate = (TextView)findViewById(R.id.birthadmin1);
    }

    private void getIncomingExtra() {
        if(getIntent().hasExtra("id")){

            idUser = getIntent().getIntExtra("id",0);

        }
    }

    private void getAdmin(){
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
