package com.example.praktikum.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.praktikum.Database.RoomDB;
import com.example.praktikum.Model.PendaftaranWithUsers;
import com.example.praktikum.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class AdminRiwayatActivity extends AppCompatActivity {

    RecyclerView recyclerViewRiwayatAdmin;
    AdminAdapterRiwayat adminAdapterRiwayat;
    RoomDB database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_riwayat);
        recyclerViewRiwayatAdmin = findViewById(R.id.recycler2);
        getPendaftaran();

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

    private void getPendaftaran(){
        database = RoomDB.getInstance(getApplicationContext());
        List<PendaftaranWithUsers> pendaftaranWithUsers = database.pendaftaranDao().loadPendaftaranResponed("accepted", "rejected");
        adminAdapterRiwayat = new AdminAdapterRiwayat(pendaftaranWithUsers, getApplicationContext());
        recyclerViewRiwayatAdmin.setAdapter(adminAdapterRiwayat);
    }
}
