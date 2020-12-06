package com.example.praktikum.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.praktikum.Adapter.AdminAdapterListAdmin;
import com.example.praktikum.Adapter.AdminAdapterListPoli;
import com.example.praktikum.Database.RoomDB;
import com.example.praktikum.Model.Poli;
import com.example.praktikum.Model.User;
import com.example.praktikum.R;

import java.util.List;



public class AdminListPoli extends AppCompatActivity {

    EditText namaPoli;
    Button btnAddPoli;
    RecyclerView recyclerView;
    AdminAdapterListPoli adminAdapterListPoli;
    RoomDB database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list_poli);

        recyclerView = findViewById(R.id.recyclerPoli);
        getPoli();
    }

    private void getPoli(){
        database = RoomDB.getInstance(getApplicationContext());
        List<Poli> polis = database.poliDao().getAllPoli();
        adminAdapterListPoli = new AdminAdapterListPoli(polis, AdminListPoli.this);
        recyclerView.setAdapter(adminAdapterListPoli);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPoli();
    }

    public void addPoli(View view){
        database = RoomDB.getInstance(getApplicationContext());
        Dialog dialog = new Dialog(AdminListPoli.this);
        dialog.setContentView(R.layout.dialog_add_poli);
        int width = WindowManager.LayoutParams.MATCH_PARENT;
        int height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width,height);
        dialog.show();

        namaPoli = dialog.findViewById(R.id.editTextAddPoli);
        btnAddPoli = dialog.findViewById(R.id.btnAddPoli);

        btnAddPoli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String poliName = namaPoli.getText().toString().trim();
                Poli poli = new Poli();
                poli.setPoli(poliName);
                database.poliDao().insert(poli);
                dialog.dismiss();
                recyclerView.getAdapter().notifyDataSetChanged();
                Intent intent = new Intent(AdminListPoli.this, AdminListPoli.class);
                startActivity(intent);
                finish();
                Toast.makeText(getApplicationContext(), "Add Poli Success", Toast.LENGTH_SHORT).show();
            }
        });
    }
}