package com.example.praktikum.Admin;

import android.os.Bundle;

import com.example.praktikum.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class AdminListUserActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdminAdapterListUser adminAdapterListUser;

    List<String> namausr, emailusr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list_user);

        namausr = new ArrayList<>();
        namausr.add("Abdi");
        namausr.add("Fiqri");
        namausr.add("Fiqri Abdi");
        namausr.add("Abdi Fiqri");
        namausr.add("Ricardo");
        namausr.add("Milos");
        namausr.add("Cicak");
        namausr.add("Kuda");
        namausr.add("Cicak Kuda");

        emailusr = new ArrayList<>();
        emailusr.add("abdi@gmail.com");
        emailusr.add("fiqri@gmail.com");
        emailusr.add("fiqriabdi@gmail.com");
        emailusr.add("abdifiqri@gmail.com");
        emailusr.add("ricardo@gmail.com");
        emailusr.add("milos@gmail.com");
        emailusr.add("cicak@gmail.com");
        emailusr.add("kuda@gmail.com");
        emailusr.add("cicakkuda@gmail.com");


        recyclerView = findViewById(R.id.recycler4);
        adminAdapterListUser = new AdminAdapterListUser(namausr,emailusr,this);
        recyclerView.setAdapter(adminAdapterListUser);

    }
}
