package com.example.praktikum.AuthAndUser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.praktikum.Admin.AdminHomeActivity;
import com.example.praktikum.Database.RoomDB;
import com.example.praktikum.MainActivity;
import com.example.praktikum.Model.User;
import com.example.praktikum.R;
import com.google.android.material.textfield.TextInputLayout;


public class LoginActivity extends AppCompatActivity {

    TextView textView;
    EditText name, email,password, passwordConfirm;
    TextInputLayout layoutEmail, layoutPassword;
    Button btnlogin;
    ProgressDialog dialog;
    RoomDB database;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

    }

    private void init(){
        layoutEmail = (TextInputLayout)findViewById(R.id.txtLayoutEmailSignIn);
        layoutPassword = (TextInputLayout)findViewById(R.id.txtLayoutPasswordSignIn);



        email = (EditText)findViewById(R.id.txtEmailSignIn);
        password = (EditText)findViewById(R.id.txtPasswordSignIn);
        btnlogin = (Button)findViewById(R.id.btnSignIn);
        dialog = new ProgressDialog(LoginActivity.this);
        dialog.setCancelable(false);

        textView = (TextView)findViewById(R.id.registernih);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    login();
                }
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!email.getText().toString().isEmpty()){
                    layoutEmail.setErrorEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (password.getText().length()>7){
                    layoutPassword.setErrorEnabled(false);
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
        if(email.getText().toString().isEmpty()){
            layoutEmail.setErrorEnabled(true);
            layoutEmail.setError("Email is Required");
            return false;
        }
        if(password.getText().toString().length()<8){
            layoutPassword.setErrorEnabled(true);
            layoutPassword.setError("Password at least 8 characters");
            return false;
        }
        return true;

    }

    private void login(){
        database = RoomDB.getInstance(getApplicationContext());
        user = database.userDao().loginUser(email.getText().toString(), password.getText().toString());
        if (user == null){
            Toast.makeText(getApplicationContext(), "Oops, User Not Found!", Toast.LENGTH_SHORT).show();
        }
        else{
            SharedPreferences userPref = getApplicationContext().getSharedPreferences("user", getApplicationContext().MODE_PRIVATE);
            user = database.userDao().loginUser(email.getText().toString(), password.getText().toString());
            int id_user = user.getID();
            if (id_user>0){
                SharedPreferences.Editor editor = userPref.edit();
                editor.putBoolean("isLoggedIn", true);
                editor.putInt("id", user.getID());
                editor.putString("name", user.getName());
                editor.putString("email", user.getEmail());
                editor.putString("role", user.getRole());
                editor.putString("mobile", user.getMobile());
                editor.putString("address", user.getAddress());
                editor.putString("gender", user.getGender());
                editor.putString("birthdate", user.getBirthdate());
                editor.apply();
                if(userPref.getString("role", null).equals("1")){
                    Intent i = new Intent(LoginActivity.this, AdminHomeActivity.class);
                    startActivity(i);
                }else if(userPref.getString("role", null).equals("2")){
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                }

                Toast.makeText(getApplicationContext(), "Welcome "+userPref.getString("name",null)+"!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}