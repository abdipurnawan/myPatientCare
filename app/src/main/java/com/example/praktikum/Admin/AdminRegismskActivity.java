package com.example.praktikum.Admin;

import android.os.Bundle;

import com.example.praktikum.Adapter.AdminAdapterRegismsk;
import com.example.praktikum.Database.RoomDB;
import com.example.praktikum.Model.PendaftaranWithUsers;
import com.example.praktikum.R;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class AdminRegismskActivity extends AppCompatActivity {

    public static RecyclerView recyclerViewRegisMasuk;
    AdminAdapterRegismsk adminAdapterRegismsk;
    RoomDB database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_regismsk);
        recyclerViewRegisMasuk = findViewById(R.id.recycler3);

        getPendaftaran();
    }

    private void getPendaftaran(){
        database = RoomDB.getInstance(getApplicationContext());
        List<PendaftaranWithUsers> pendaftaranWithUsers = database.pendaftaranDao().loadPendaftaranMasuk("pending");
        adminAdapterRegismsk = new AdminAdapterRegismsk(pendaftaranWithUsers, getApplicationContext());
        recyclerViewRegisMasuk.setAdapter(adminAdapterRegismsk);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPendaftaran();
    }
}
