package com.example.praktikum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.praktikum.Template.RegistsakitActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    TextView welcomeMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences userPref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
                boolean isLoggedIn = userPref.getBoolean("isLoggedIn", false);

                if(!isLoggedIn){
                    isFirstTime();
                }

            }
        }, 100);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        overridePendingTransition(0,0);
                        break;
                    case R.id.nav_recent:
                        startActivity(new Intent(getApplicationContext(),RiwayatActivity.class));
                        overridePendingTransition(0,0);
                        break;
                    case R.id.nav_user:
                        startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                        overridePendingTransition(0,0);
                        break;
                }
                return false;
            }
        });

        init();
    }

    private void init(){
        SharedPreferences userPref = getApplicationContext().getSharedPreferences("user", getApplicationContext().MODE_PRIVATE);

        welcomeMain = (TextView)findViewById(R.id.txtWelcomeMain);
        welcomeMain.setText("Halo "+userPref.getString("name", null)+"!\nSelamat Datang");
    }

    public void isFirstTime(){
        SharedPreferences preferences = getApplication().getSharedPreferences("onBoard", Context.MODE_PRIVATE);
        boolean isFirstTime = preferences.getBoolean("isFirstTime", true);

        if(isFirstTime){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isFirstTime", false);
            editor.apply();

            startActivity(new Intent(MainActivity.this,MainActivity.class));
        }else{
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
            finish();
        }
    }

    public void regist(View view) {
        Intent intent = new Intent(this, RegistsakitActivity.class);
        startActivity(intent);
        Toast.makeText(this, "MAU NIH GAN", Toast.LENGTH_LONG).show();
    }

}
