package com.example.praktikum.AuthAndUser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.praktikum.Database.RoomDB;
import com.example.praktikum.Model.PendaftaranWithUsers;
import com.example.praktikum.R;

public class DetailRiwayatrgsActivity extends AppCompatActivity {

    TextView keluhan, penyakit, poli, tinggi, berat, status, tanggal, tanggalLayout;
    Button btnEdit, btnDelete;
    int idRegis, position;
    RoomDB database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_riwayatrgs);
        init();
    }

    private void init(){
        keluhan = (TextView)findViewById(R.id.txtKeluhan);
        penyakit = (TextView)findViewById(R.id.txtPenyakit);
        poli = (TextView)findViewById(R.id.txtPoli);
        tinggi = (TextView)findViewById(R.id.txtTinggi);
        berat = (TextView)findViewById(R.id.txtBerat);
        status = (TextView)findViewById(R.id.txtStatus);
        tanggal = (TextView)findViewById(R.id.txtTanggal);
        tanggalLayout = (TextView)findViewById(R.id.txtTanggalDetail);

        btnEdit = (Button)findViewById(R.id.editrwtrgs);
        btnDelete = (Button)findViewById(R.id.delrwtrgs);


        getIncomingExtra();
        getDetail();

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailRiwayatrgsActivity.this);
                builder.setTitle("Confirm");
                builder.setMessage("Delete Registrasi?");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        database = RoomDB.getInstance(getApplicationContext());
                        database.pendaftaranDao().deletePendaftaran(idRegis);
                        RiwayatPendingActivity.recyclerView.getAdapter().notifyItemRemoved(position);
                        RiwayatPendingActivity.recyclerView.getAdapter().notifyDataSetChanged();
                        Intent intent1 = new Intent(DetailRiwayatrgsActivity.this, RiwayatPendingActivity.class);
                        intent1.putExtra("id", idRegis);
                        startActivity(intent1);
                        finish();
                        Toast.makeText(getApplicationContext(), "Delete Registrasi Success", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailRiwayatrgsActivity.this, EditRegistrasiaActivity.class);
                intent.putExtra("keluhan", keluhan.getText().toString());
                intent.putExtra("penyakit_bawaan", penyakit.getText().toString());
                intent.putExtra("poli", poli.getText().toString());
                intent.putExtra("tinggi", tinggi.getText().toString());
                intent.putExtra("berat", berat.getText().toString());
                intent.putExtra("id", idRegis);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }

    private void getIncomingExtra() {

        if (getIntent().hasExtra("id")) {
            idRegis = getIntent().getIntExtra("id", 0);
            position = getIntent().getIntExtra("position", 0);
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
            if (pendaftaranWithUsers.pendaftaran.getStatus().equals("pending")){
                status.setText("Pending");
                tanggalLayout.setVisibility(View.GONE);
                tanggal.setVisibility(View.GONE);
            }else if (pendaftaranWithUsers.pendaftaran.getStatus().equals("accepted")){
                status.setText("Accepted");
                tanggal.setText(pendaftaranWithUsers.pendaftaran.getTgl_regis());
                btnDelete.setVisibility(View.GONE);
                btnEdit.setVisibility(View.GONE);
            }else if (pendaftaranWithUsers.pendaftaran.getStatus().equals("rejected")){
                status.setText("Rejected");
                tanggalLayout.setVisibility(View.GONE);
                tanggal.setVisibility(View.GONE);
                btnDelete.setVisibility(View.GONE);
                btnEdit.setVisibility(View.GONE);
            }

    }
}
