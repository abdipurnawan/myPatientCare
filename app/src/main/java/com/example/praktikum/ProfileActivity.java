package com.example.praktikum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.praktikum.Template.Constant;
import com.example.praktikum.Template.EditpassActivity;
import com.example.praktikum.Template.EditprofileActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    private Button button, btnLogout;
    private TextView mainName, mainEmail, name, mobile, email, address, birthdate, gender, photo;
    private String idLogin, tokenLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences userPref = getApplicationContext().getSharedPreferences("user", getApplicationContext().MODE_PRIVATE);
        idLogin = userPref.getString("id",null);
        tokenLogin = userPref.getString("token", null);
        isLogin();
        init();
    }

    private void init(){
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_user);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        break;
                    case R.id.nav_recent:
                        startActivity(new Intent(getApplicationContext(),RiwayatActivity.class));
                        overridePendingTransition(0,0);
                        break;
                    case R.id.nav_user:
                        overridePendingTransition(0,0);
                        break;
                }
                return false;
            }
        });

        mainName = (TextView)findViewById(R.id.txtMainNameProfile);
        mainEmail = (TextView)findViewById(R.id.txtMainEmailProfile);
        name = (TextView)findViewById(R.id.txtNameProfile);
        mobile = (TextView)findViewById(R.id.txtMobileProfile);
        email = (TextView)findViewById(R.id.txtEmailProfile);
        address = (TextView)findViewById(R.id.txtAddressProfile);
        birthdate = (TextView)findViewById(R.id.txtBirthdateProfile);
        gender = (TextView)findViewById(R.id.txtGenderProfile);
        setUserInfo();

        button = (Button) findViewById(R.id.buttonEditProfile);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //openActivity();
            }
        });

        button = (Button) findViewById(R.id.buttonEditPass);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openActivity1();
            }
        });

        btnLogout = (Button) findViewById(R.id.buttonLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences userPref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = userPref.edit();
                editor.clear();
                editor.apply();
                Toast.makeText(getApplicationContext(), "Logout Success", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent1);
            }
        });
    }

    public void openActivity() {
        Intent intent = new Intent(this, EditprofileActivity.class);
        startActivity(intent);
    }

    public void openActivity1() {
        Intent intent = new Intent(this, EditpassActivity.class);
        startActivity(intent);
    }

    private void setUserInfo(){
        SharedPreferences userPref = getApplicationContext().getSharedPreferences("user", getApplicationContext().MODE_PRIVATE);
        String nameLogin = userPref.getString("name",null);
        String emailLogin = userPref.getString("email",null);
        String mobileLogin = userPref.getString("mobile",null);
        String addressLogin = userPref.getString("address",null);
        String genderLogin = userPref.getString("gender",null);
        String birthdateLogin = userPref.getString("birthdate",null);

        mainName.setText(nameLogin);
        mainEmail.setText(emailLogin);
        name.setText(nameLogin);
        mobile.setText(mobileLogin);
        email.setText(emailLogin);
        address.setText(addressLogin);
        birthdate.setText(birthdateLogin);
        gender.setText(genderLogin);
    }

    private void isLogin(){
        SharedPreferences userPref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        boolean isLoggedIn = userPref.getBoolean("isLoggedIn", false);

        if(!isLoggedIn){
            isFirstTime();
        }
    }

    public void isFirstTime(){
        SharedPreferences preferences = getApplication().getSharedPreferences("onBoard", Context.MODE_PRIVATE);
        boolean isFirstTime = preferences.getBoolean("isFirstTime", true);

        if(isFirstTime){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isFirstTime", false);
            editor.apply();

            startActivity(new Intent(ProfileActivity.this,ProfileActivity.class));
        }else{
            startActivity(new Intent(ProfileActivity.this,LoginActivity.class));
            finish();
        }
    }

}
