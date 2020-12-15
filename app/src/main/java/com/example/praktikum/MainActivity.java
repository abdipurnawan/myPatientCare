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

import com.example.praktikum.Admin.AdminHomeActivity;
import com.example.praktikum.AuthAndUser.LoginActivity;
import com.example.praktikum.AuthAndUser.RegistsakitActivity;
import com.example.praktikum.Database.RoomDB;
import com.example.praktikum.Model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    RoomDB database;
    int adminCheck;
    TextView welcomeMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        //CHECK ADMIN IF EXIST
        database = RoomDB.getInstance(getApplicationContext());
        adminCheck = database.userDao().checkAdmin("admin@pacar.com");
        if (adminCheck<1){
            User user = new User();
            user.setName("Admin Patient Care");
            user.setEmail("admin@pacar.com");
            user.setPassword("12345678");
            user.setAddress("Tembuku, Bangli, Bali");
            user.setBirthdate("2000-09-13");
            user.setGender("Male");
            user.setMobile("0366123456");
            user.setRole("1");
            database.userDao().insert(user);
        }


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences userPref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
                boolean isLoggedIn = userPref.getBoolean("isLoggedIn", false);

                if(!isLoggedIn){
                    isFirstTime();
                }else if(isLoggedIn){
                    if (userPref.getString("role", null).equals("2")){
                    }else if (userPref.getString("role", null).equals("1")){
                        startActivity(new Intent(MainActivity.this, AdminHomeActivity.class));
                    }
                }

            }
        }, 0);

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

        }else{
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
    }

    public void regist(View view) {
        Intent intent = new Intent(this, RegistsakitActivity.class);
        startActivity(intent);
    }

}
