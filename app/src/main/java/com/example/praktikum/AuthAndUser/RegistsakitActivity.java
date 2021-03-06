package com.example.praktikum.AuthAndUser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.praktikum.Database.RoomDB;
import com.example.praktikum.MainActivity;
import com.example.praktikum.Model.Pendaftaran;
import com.example.praktikum.Model.Poli;
import com.example.praktikum.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class RegistsakitActivity extends AppCompatActivity {

    private Button button, btnRegis, btnPoli;
    private AutoCompleteTextView poli;
    EditText keluhan, penyakit, tinggi, berat;
    TextInputLayout layoutKeluhan, layoutPenyakit, layoutPoli, layoutTinggi, layoutBerat;
    ProgressDialog dialog;
    RoomDB database;
    List<String>poliDisplay;
    List<Integer>poliValue;
    ArrayAdapter<String> adapterPoli;
    Spinner poliSpinner;
    private static final String[] poliList = new String[] {"Umum", "Anak", "Gigi", "Urologi", "THT"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registsakit);
        init();
    }


    public void openActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void init(){
        setSpinners();
        layoutKeluhan = (TextInputLayout)findViewById(R.id.txtRegKeluhanLayout);
        layoutPenyakit = (TextInputLayout)findViewById(R.id.txtRegPenyakitLayout);
        layoutTinggi = (TextInputLayout)findViewById(R.id.txtRegTinggiLayout);
        layoutBerat = (TextInputLayout)findViewById(R.id.txtRegBeratLayout);

        keluhan = (EditText)findViewById(R.id.txtRegKeluhan);
        penyakit = (EditText)findViewById(R.id.txtRegPenyakit);
        tinggi = (EditText)findViewById(R.id.txtRegTinggi);
        berat = (EditText)findViewById(R.id.txtRegBerat);
        poliSpinner = (Spinner) findViewById(R.id.spnPoli);

        dialog = new ProgressDialog(RegistsakitActivity.this);
        dialog.setCancelable(false);

        button = (Button) findViewById(R.id.Regcancel);
        btnRegis = (Button)findViewById(R.id.btnRegRegis);

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    registrasi();
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });

        keluhan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!keluhan.getText().toString().isEmpty()){
                    layoutKeluhan.setErrorEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        penyakit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!penyakit.getText().toString().isEmpty()){
                    layoutPenyakit.setErrorEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        tinggi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!tinggi.getText().toString().isEmpty()){
                    layoutTinggi.setErrorEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        berat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!berat.getText().toString().isEmpty()){
                    layoutBerat.setErrorEnabled(true);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private boolean validate(){
        if(keluhan.getText().toString().isEmpty()){
            layoutKeluhan.setErrorEnabled(true);
            layoutKeluhan.setError("Keluhan is Required");
            return false;
        }
        if(penyakit.getText().toString().isEmpty()){
            layoutPenyakit.setErrorEnabled(true);
            layoutPenyakit.setError("Penyakit Bawaan is Required");
            return false;
        }
        if(tinggi.getText().toString().isEmpty()){
            layoutTinggi.setErrorEnabled(true);
            layoutTinggi.setError("Tinggi is Required");
            return false;
        }
        if(berat.getText().toString().isEmpty()){
            layoutBerat.setErrorEnabled(true);
            layoutBerat.setError("Berat is Required");
            return false;
        }
        return true;

    }

    private void registrasi(){
        dialog.setMessage("Registering");
        dialog.show();
        SharedPreferences userPref = getApplicationContext().getSharedPreferences("user", getApplicationContext().MODE_PRIVATE);
        int user_id = userPref.getInt("id", 0);
        database = RoomDB.getInstance(getApplicationContext());

        Pendaftaran pendaftaran = new Pendaftaran();
        pendaftaran.setTinggi_badan(tinggi.getText().toString());
        pendaftaran.setTgl_regis(null);
        pendaftaran.setStatus("pending");
        pendaftaran.setPenyakit_bawaan(penyakit.getText().toString());
        pendaftaran.setKeluhan(keluhan.getText().toString());
        pendaftaran.setBerat_badan(berat.getText().toString());
        pendaftaran.setId_user(user_id);
        pendaftaran.setId_poli(poliValue.get(poliSpinner.getSelectedItemPosition()));
        database.pendaftaranDao().insertPendaftaran(pendaftaran);
        dialog.dismiss();
        Intent intent1 = new Intent(RegistsakitActivity.this, MainActivity.class);
        startActivity(intent1);
        finish();
        Toast.makeText(getApplicationContext(), "Register Success", Toast.LENGTH_SHORT).show();
    }

    private void setSpinners() {
        poliDisplay = new ArrayList<String>();
        poliValue = new ArrayList<Integer>();
        poliSpinner = (Spinner) findViewById(R.id.spnPoli);

        database = RoomDB.getInstance(getApplicationContext());
        List<Poli> poliList = database.poliDao().getAllPoli();
        for (Poli poli : poliList) {
            poliDisplay.add(poli.getPoli());
            poliValue.add(poli.getID());
        }
        adapterPoli = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, poliDisplay);
        adapterPoli.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        poliSpinner.setAdapter(adapterPoli);
        adapterPoli.notifyDataSetChanged();
    }

}
