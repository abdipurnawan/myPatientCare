package com.example.praktikum.Template;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.praktikum.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetailRiwayatrgsActivity extends AppCompatActivity {

    TextView keluhan, penyakit, poli, tinggi, berat, status, tanggal, tanggalLayout;
    Button btnEdit, btnDelete;
    String keluhanPasien, penyakitPasien, poliPasien, tinggiPasien, beratPasien, statusPasien, tanggalPasien, idRegis, tokenLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_riwayatrgs);
        SharedPreferences userPref = getApplicationContext().getSharedPreferences("user", getApplicationContext().MODE_PRIVATE);
        tokenLogin = userPref.getString("token", null);
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
        getDaftarDetail();
        setDaftarDetail();

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailRiwayatrgsActivity.this, EditRegistrasiaActivity.class);
                intent.putExtra("keluhan", keluhanPasien);
                intent.putExtra("penyakit_bawaan", penyakitPasien);
                intent.putExtra("poli", poliPasien);
                intent.putExtra("tinggi", tinggiPasien);
                intent.putExtra("berat", beratPasien);
                intent.putExtra("id", idRegis);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

    }

    private void getIncomingExtra() {

        if(getIntent().hasExtra("id")){
            idRegis = getIntent().getStringExtra("id");
        }
    }

    private void getDaftarDetail(){
        StringRequest request = new StringRequest(Request.Method.POST, Constant.GET_DETAIL_SAKIT, response -> {
            try {
                JSONObject object1 = new JSONObject(response);
                if (object1.getBoolean("success")){
                    JSONObject pend = object1.getJSONObject("pendaftaran");
                    SharedPreferences pendaftaranPref = getApplicationContext().getSharedPreferences("pendaftaran", getApplicationContext().MODE_PRIVATE);
                    SharedPreferences.Editor editor = pendaftaranPref.edit();
                    editor.putString("keluhan", pend.getString("keluhan"));
                    editor.putString("penyakit_bawaan", pend.getString("penyakit_bawaan"));
                    editor.putString("poli", pend.getString("poli"));
                    editor.putString("tinggi_badan", pend.getString("tinggi_badan"));
                    editor.putString("berat_badan", pend.getString("berat_badan"));
                    editor.putString("status", pend.getString("status"));
                    editor.putString("tgl_regis", pend.getString("tgl_regis"));
                    editor.apply();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Get Data Failed", Toast.LENGTH_SHORT).show();
            }
        },error -> {
            error.printStackTrace();
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                // Basic Authentication
                //String auth = "Basic " + Base64.encodeToString(CONSUMER_KEY_AND_SECRET.getBytes(), Base64.NO_WRAP);

                headers.put("Authorization", "Bearer " + tokenLogin);
                return headers;
            }

            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map = new HashMap<>();
                map.put("id",idRegis);
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }

    private void setDaftarDetail(){
        SharedPreferences pendaftaranPref = getApplicationContext().getSharedPreferences("pendaftaran", getApplicationContext().MODE_PRIVATE);
        keluhanPasien = pendaftaranPref.getString("keluhan", null);
        penyakitPasien = pendaftaranPref.getString("penyakit_bawaan", null);
        poliPasien = pendaftaranPref.getString("poli", null);
        tinggiPasien = pendaftaranPref.getString("tinggi_badan", null);
        beratPasien = pendaftaranPref.getString("berat_badan", null);
        statusPasien = pendaftaranPref.getString("status", null);
        tanggalPasien = pendaftaranPref.getString("tgl_regis", null);

        keluhan.setText(keluhanPasien);
        penyakit.setText(penyakitPasien);
        poli.setText(poliPasien);
        tinggi.setText(tinggiPasien);
        berat.setText(beratPasien);
        status.setText(statusPasien);
        tanggal.setText(tanggalPasien);
    }

}
