package com.example.praktikum.AuthAndUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.praktikum.Adapter.AdapterRegisPending;
import com.example.praktikum.Database.RoomDB;
import com.example.praktikum.Model.Pendaftaran;
import com.example.praktikum.Model.PendaftaranWithUsers;
import com.example.praktikum.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RiwayatPendingActivity extends AppCompatActivity {

    public  static RecyclerView recyclerView;
    AdapterRegisPending adapter1;
    RoomDB database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayatregis);
        recyclerView = findViewById(R.id.recycler1);
        getPendaftaran();
    }

    private void getPendaftaran(){
        SharedPreferences userPref = getApplicationContext().getSharedPreferences("user", getApplicationContext().MODE_PRIVATE);
        int id_user = userPref.getInt("id", 1);
        database = RoomDB.getInstance(getApplicationContext());
        List<PendaftaranWithUsers> pendaftaranWithUsers = database.pendaftaranDao().loadPendaftaranByStatusPending("pending", id_user);

        adapter1 = new AdapterRegisPending(pendaftaranWithUsers, getApplicationContext());
        recyclerView.setAdapter(adapter1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPendaftaran();
    }
}
