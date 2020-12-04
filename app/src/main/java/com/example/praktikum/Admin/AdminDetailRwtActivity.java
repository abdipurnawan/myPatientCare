package com.example.praktikum.Admin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.praktikum.Database.RoomDB;
import com.example.praktikum.Model.PendaftaranWithUsers;
import com.example.praktikum.R;

import java.text.SimpleDateFormat;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class AdminDetailRwtActivity extends AppCompatActivity {

    TextView nama, keluhan, penyakit, poli, tinggi, berat, status, tanggal, tanggalLayout;
    RoomDB database;
    int idRegis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_detail_rwt);

        init();
        getIncomingExtra();

    }

    private void init(){
        nama = (TextView)findViewById(R.id.txtNama);
        keluhan = (TextView)findViewById(R.id.txtKeluhan);
        penyakit = (TextView)findViewById(R.id.txtPenyakit);
        poli = (TextView)findViewById(R.id.txtPoli);
        tinggi = (TextView)findViewById(R.id.txtTinggi);
        berat = (TextView)findViewById(R.id.txtBerat);
        status = (TextView)findViewById(R.id.txtStatus);
        tanggal = (TextView)findViewById(R.id.txtTanggal);
        tanggalLayout = (TextView)findViewById(R.id.txtTanggalDetail);

        getIncomingExtra();
        getDetail();


    }

    private void getIncomingExtra() {

        if (getIntent().hasExtra("id")) {
            idRegis = getIntent().getIntExtra("id", 0);
        }
    }

    private void getDetail(){
        database = RoomDB.getInstance(getApplicationContext());
        PendaftaranWithUsers pendaftaranWithUsers = database.pendaftaranDao().loadPendaftaranById(idRegis);
        keluhan.setText(pendaftaranWithUsers.pendaftaran.getKeluhan());
        penyakit.setText(pendaftaranWithUsers.pendaftaran.getPenyakit_bawaan());
        poli.setText(pendaftaranWithUsers.pendaftaran.getPoli());
        tinggi.setText(pendaftaranWithUsers.pendaftaran.getTinggi_badan());
        berat.setText(pendaftaranWithUsers.pendaftaran.getBerat_badan());
        int idUser = pendaftaranWithUsers.pendaftaran.getId_user();
        nama.setText(database.userDao().getUserName(idUser));
        if (pendaftaranWithUsers.pendaftaran.getStatus().equals("pending")){
            status.setText("Pending");
            tanggalLayout.setVisibility(View.GONE);
            tanggal.setVisibility(View.GONE);
        }else if (pendaftaranWithUsers.pendaftaran.getStatus().equals("accepted")){
            status.setText("Accepted");
            tanggal.setText(pendaftaranWithUsers.pendaftaran.getTgl_regis());
        }else if (pendaftaranWithUsers.pendaftaran.getStatus().equals("rejected")){
            status.setText("Rejected");
            tanggalLayout.setVisibility(View.GONE);
            tanggal.setVisibility(View.GONE);
        }

    }

}
