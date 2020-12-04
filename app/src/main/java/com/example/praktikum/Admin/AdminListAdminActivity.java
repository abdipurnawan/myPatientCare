package com.example.praktikum.Admin;

import android.os.Bundle;

import com.example.praktikum.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class AdminListAdminActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdminAdapterListAdmin adminAdapterListAdmin;

    List<String> namaadm, emailadm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list_admin);

        namaadm = new ArrayList<>();
        namaadm.add("Admin1");
        namaadm.add("Admin2");
        namaadm.add("Admin3");
        namaadm.add("Admin4");
        namaadm.add("Admin5");
        namaadm.add("Admin6");
        namaadm.add("Admin7");
        namaadm.add("Admin8");
        namaadm.add("Admin9");

        emailadm = new ArrayList<>();
        emailadm.add("admin1@gmail.com");
        emailadm.add("admin2@gmail.com");
        emailadm.add("admin3@gmail.com");
        emailadm.add("admin4@gmail.com");
        emailadm.add("admin5@gmail.com");
        emailadm.add("admin6@gmail.com");
        emailadm.add("admin7@gmail.com");
        emailadm.add("admin8@gmail.com");
        emailadm.add("admin9@gmail.com");


        recyclerView = findViewById(R.id.recycler5);
        adminAdapterListAdmin = new AdminAdapterListAdmin(namaadm,emailadm,this);
        recyclerView.setAdapter(adminAdapterListAdmin);
    }
}
