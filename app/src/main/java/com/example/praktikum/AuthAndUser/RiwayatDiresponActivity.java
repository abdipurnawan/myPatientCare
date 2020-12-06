package com.example.praktikum.AuthAndUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.praktikum.Adapter.AdapterRegisPending;
import com.example.praktikum.Adapter.AdapterRegisResponed;
import com.example.praktikum.Database.RoomDB;
import com.example.praktikum.Model.PendaftaranWithUsers;
import com.example.praktikum.R;

import java.util.List;

public class RiwayatDiresponActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RoomDB database;
    AdapterRegisResponed adapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_direspon);
        recyclerView = findViewById(R.id.recycler2);
        getPendaftaran();
    }

    private void getPendaftaran(){
        SharedPreferences userPref = getApplicationContext().getSharedPreferences("user", getApplicationContext().MODE_PRIVATE);
        int id_user = userPref.getInt("id", 1);
        database = RoomDB.getInstance(getApplicationContext());
        List<PendaftaranWithUsers> pendaftaranWithUsersRespond = database.pendaftaranDao().loadPendaftaranByStatusResponed("accepted", "rejected", id_user);

        adapter2 = new AdapterRegisResponed(pendaftaranWithUsersRespond, getApplicationContext());
        recyclerView.setAdapter(adapter2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPendaftaran();
    }

}