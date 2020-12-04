package com.example.praktikum.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.praktikum.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class AdminRiwayatActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdminAdapterRiwayat adminAdapterRiwayat;

    List<String> nopdftar, pyktList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_riwayat);


        nopdftar = new ArrayList<>();
        nopdftar.add("No Pendaftaran 01");
        nopdftar.add("No Pendaftaran 02");
        nopdftar.add("No Pendaftaran 03");
        nopdftar.add("No Pendaftaran 04");
        nopdftar.add("No Pendaftaran 05");
        nopdftar.add("No Pendaftaran 06");
        nopdftar.add("No Pendaftaran 07");
        nopdftar.add("No Pendaftaran 08");
        nopdftar.add("No Pendaftaran 09");

        pyktList = new ArrayList<>();
        pyktList.add("Banyak Ngeluh");
        pyktList.add("Nugas Mulu");
        pyktList.add("Maen Teros Nilai Pengen Gede");
        pyktList.add("3SKS Gak Ngapa2in");
        pyktList.add("Haha Hehe UTS");
        pyktList.add("Sok Sakit Aja");
        pyktList.add("Abdi Mantap");
        pyktList.add("Kuliah Enggan DO Pun Tak Mau");
        pyktList.add("GAS BLAR");


        recyclerView = findViewById(R.id.recycler2);
        adminAdapterRiwayat = new AdminAdapterRiwayat(nopdftar,pyktList,this);
        recyclerView.setAdapter(adminAdapterRiwayat);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_recent);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), AdminHomeActivity.class));
                        overridePendingTransition(0,0);
                        break;


                    case R.id.nav_recent:
                        overridePendingTransition(0,0);

                        break;


                    case R.id.nav_user:
                        startActivity(new Intent(getApplicationContext(), AdminProfileActivity.class));
                        overridePendingTransition(0,0);

                        break;

                }

                return false;
            }
        });


    }
}
