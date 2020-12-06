package com.example.praktikum.Admin;

import android.os.Bundle;

import com.example.praktikum.Adapter.AdminAdapterListAdmin;
import com.example.praktikum.Database.RoomDB;
import com.example.praktikum.Model.User;
import com.example.praktikum.R;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class AdminListAdminActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdminAdapterListAdmin adminAdapterListAdmin;
    RoomDB database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list_admin);

        recyclerView = findViewById(R.id.recycler5);
        getUser();

    }

    private void getUser(){
        database = RoomDB.getInstance(getApplicationContext());
        List<User> users = database.userDao().getAllUser("1");
        adminAdapterListAdmin = new AdminAdapterListAdmin(users, getApplicationContext());
        recyclerView.setAdapter(adminAdapterListAdmin);
    }
}
