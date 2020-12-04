package com.example.praktikum.Admin;

import android.os.Bundle;
import android.widget.TextView;

import com.example.praktikum.R;

import androidx.appcompat.app.AppCompatActivity;

public class AdminDetailListadmActivity extends AppCompatActivity {

    TextView textnamaadm, textemailadm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_detail_listadm);

        textnamaadm = findViewById(R.id.Nameadmin1);
        textemailadm = findViewById(R.id.Emailadmin1);

        getIncomingExtra();

    }

    private void getIncomingExtra() {
        if(getIntent().hasExtra("namaadm") && getIntent().hasExtra("emailadm")){


            String namaAdmin = getIntent().getStringExtra("namaadm");
            String emailAdmin = getIntent().getStringExtra("emailadm");

            setDataActivity(namaAdmin,emailAdmin);

        }
    }

    private void setDataActivity(String namaAdmin, String emailAdmin) {
        textnamaadm.setText(namaAdmin);
        textemailadm.setText(emailAdmin);
    }
}
