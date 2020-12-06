package com.example.praktikum.Admin;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.praktikum.Database.RoomDB;
import com.example.praktikum.Model.PendaftaranWithUsers;
import com.example.praktikum.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class AdminDetailRegsmskActivity extends AppCompatActivity {

    TextView nama, keluhan, penyakit, poli, tinggi, berat, status, tanggal, tanggalLayout;
    EditText tanggalpemeriksaan;
    Button btnAccept, btnRefuse, btnAccept1, btnTanggal;
    RoomDB database;
    int idRegis;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_detail_regsmsk);

        init();
        getIncomingExtra();

    }

    private void init(){
        dateFormatter = new SimpleDateFormat("YYYY-MM-dd", Locale.US);
        nama = (TextView)findViewById(R.id.txtNama);
        keluhan = (TextView)findViewById(R.id.txtKeluhan);
        penyakit = (TextView)findViewById(R.id.txtPenyakit);
        poli = (TextView)findViewById(R.id.txtPoli);
        tinggi = (TextView)findViewById(R.id.txtTinggi);
        berat = (TextView)findViewById(R.id.txtBerat);
        status = (TextView)findViewById(R.id.txtStatus);
        tanggal = (TextView)findViewById(R.id.txtTanggal);
        tanggalLayout = (TextView)findViewById(R.id.txtTanggalDetail);

        btnAccept = (Button)findViewById(R.id.accregis);
        btnRefuse = (Button)findViewById(R.id.refuseregis);

        getIncomingExtra();
        getDetail();

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(AdminDetailRegsmskActivity.this);
                dialog.setContentView(R.layout.dialog_accept_regis);
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                int height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setLayout(width,height);
                dialog.show();
                tanggalpemeriksaan = dialog.findViewById(R.id.tanggal_accept);
                btnAccept1 = dialog.findViewById(R.id.btnAccepted);
                btnTanggal = dialog.findViewById(R.id.btnTanggal);

                btnTanggal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showDateDialog();
                    }
                });

                btnAccept1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        database.pendaftaranDao().acceptRegistrasi(tanggalpemeriksaan.getText().toString(), "accepted", idRegis);
                        AdminRegismskActivity.recyclerViewRegisMasuk.getAdapter().notifyDataSetChanged();
                        Toast.makeText(AdminDetailRegsmskActivity.this,"Accepted", Toast.LENGTH_LONG).show();
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });

            }
        });

        btnRefuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AdminDetailRegsmskActivity.this);
                builder.setTitle("Confirm");
                builder.setMessage("Refuse Registrasi?");
                builder.setPositiveButton("Refuse", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        database = RoomDB.getInstance(getApplicationContext());
                        database.pendaftaranDao().refuseRegistrasi("rejected", idRegis);
                        AdminRegismskActivity.recyclerViewRegisMasuk.getAdapter().notifyDataSetChanged();
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Refuse Registrasi Success", Toast.LENGTH_SHORT).show();
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

    private void showDateDialog() {
        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */

                tanggalpemeriksaan.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }
}
