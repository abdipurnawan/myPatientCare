package com.example.praktikum.Admin;

import android.os.Bundle;

import com.example.praktikum.Adapter.AdminAdapterListUser;
import com.example.praktikum.Database.RoomDB;
import com.example.praktikum.Model.User;
import com.example.praktikum.R;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class AdminListUserActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdminAdapterListUser adminAdapterListUser;
    RoomDB database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list_user);

        recyclerView = findViewById(R.id.recycler4);
        getUser();


    }

    private void getUser(){
        database = RoomDB.getInstance(getApplicationContext());
        List<User> users = database.userDao().getAllUser("2");
        adminAdapterListUser = new AdminAdapterListUser(users, getApplicationContext());
        recyclerView.setAdapter(adminAdapterListUser);
    }
}
