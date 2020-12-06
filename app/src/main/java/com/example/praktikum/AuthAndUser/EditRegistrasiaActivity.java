package com.example.praktikum.AuthAndUser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.praktikum.Database.RoomDB;
import com.example.praktikum.MainActivity;
import com.example.praktikum.Model.Poli;
import com.example.praktikum.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class EditRegistrasiaActivity extends AppCompatActivity {

    private Button button, btnEdit;
    private AutoCompleteTextView poli;
    EditText keluhan, penyakit, tinggi, berat;
    TextInputLayout layoutKeluhan, layoutPenyakit, layoutPoli, layoutTinggi, layoutBerat;
    ProgressDialog dialog;
    int idRegis;
    List<String> poliDisplay;
    List<Integer>poliValue;
    ArrayAdapter<String> adapterPoli;
    Spinner poliSpinner;
    RoomDB database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_registrasi);
        init();
    }


    public void openActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void init(){
        layoutKeluhan = (TextInputLayout)findViewById(R.id.txtRegKeluhanLayout);
        layoutPenyakit = (TextInputLayout)findViewById(R.id.txtRegPenyakitLayout);
        layoutTinggi = (TextInputLayout)findViewById(R.id.txtRegTinggiLayout);
        layoutBerat = (TextInputLayout)findViewById(R.id.txtRegBeratLayout);

        keluhan = (EditText)findViewById(R.id.txtRegKeluhan);
        penyakit = (EditText)findViewById(R.id.txtRegPenyakit);
        tinggi = (EditText)findViewById(R.id.txtRegTinggi);
        poliSpinner = (Spinner)findViewById(R.id.spnPoli);
        berat = (EditText)findViewById(R.id.txtRegBerat);

        setSpinners();
        getIncomingExtra();

        dialog = new ProgressDialog(EditRegistrasiaActivity.this);
        dialog.setCancelable(false);

        button = (Button) findViewById(R.id.Regcancel);
        btnEdit = (Button)findViewById(R.id.btnRegRegis);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    edit();
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

    private void getIncomingExtra() {

        if (getIntent().hasExtra("id")) {
            keluhan.setText(getIntent().getStringExtra("keluhan"));
            penyakit.setText(getIntent().getStringExtra("penyakit_bawaan"));
            poliSpinner.setSelection(poliValue.indexOf(getIntent().getIntExtra("id_poli", 0)));
            tinggi.setText(getIntent().getStringExtra("tinggi"));
            berat.setText(getIntent().getStringExtra("berat"));
            idRegis = getIntent().getIntExtra("id", 0);
        }
    }

    private void edit(){
        database = RoomDB.getInstance(getApplicationContext());
        database.pendaftaranDao().updatePendaftaran(idRegis, keluhan.getText().toString(), penyakit.getText().toString(), poliValue.get(poliSpinner.getSelectedItemPosition()), tinggi.getText().toString(), berat.getText().toString());
        RiwayatPendingActivity.recyclerView.getAdapter().notifyDataSetChanged();
        Intent intent1 = new Intent(EditRegistrasiaActivity.this, DetailRiwayatrgsActivity.class);
        intent1.putExtra("id", idRegis);
        startActivity(intent1);
        finish();
        Toast.makeText(getApplicationContext(), "Edit Registrasi Success", Toast.LENGTH_SHORT).show();
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