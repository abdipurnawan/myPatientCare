package com.example.praktikum.Admin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.praktikum.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class AdminHomeActivity extends AppCompatActivity {

    TextView welcomeMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        SharedPreferences userPref = getApplicationContext().getSharedPreferences("user", getApplicationContext().MODE_PRIVATE);
        welcomeMain = (TextView)findViewById(R.id.txtWelcomeMain);
        welcomeMain.setText("Halo "+userPref.getString("name", null)+" \nSelamat Datang \nDi Dashboard Admin");

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        overridePendingTransition(0,0);
                        break;


                    case R.id.nav_recent:
                        startActivity(new Intent(getApplicationContext(), AdminRiwayatActivity.class));
                        overridePendingTransition(0,0);

                        break;


                    case R.id.nav_user:
                        startActivity(new Intent(getApplicationContext(), AdminProfileActivity.class));
                        overridePendingTransition(0,0);

                        break;

                }

                return false;
            }
        });
    }
    public void registmsk(View view) {
        Intent intent = new Intent(this, AdminRegismskActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Registrasi Masuk", Toast.LENGTH_LONG).show();
    }

    public void listuser(View view) {
        Intent intent = new Intent(this, AdminListUserActivity.class);
        startActivity(intent);
        Toast.makeText(this, "List User", Toast.LENGTH_LONG).show();
    }

    public void listadmin(View view) {
        Intent intent = new Intent(this, AdminListAdminActivity.class);
        startActivity(intent);
        Toast.makeText(this, "List Admin", Toast.LENGTH_LONG).show();
    }
}
