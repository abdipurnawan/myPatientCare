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
import android.widget.Toast;

import com.example.praktikum.Database.RoomDB;
import com.example.praktikum.Model.User;
import com.example.praktikum.ProfileActivity;
import com.example.praktikum.R;
import com.google.android.material.textfield.TextInputLayout;

public class EditpassActivity extends AppCompatActivity {

    private Button button, btnEditPass;
    private TextInputLayout currentLayout, newLayout, confirmLayout;
    private EditText currentPass, newPass, confirmPass;
    ProgressDialog dialog;
    RoomDB database;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editpass);
        init();
    }

    public void openActivity() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
        finish();
    }

    public void init(){
        currentLayout = (TextInputLayout)findViewById(R.id.txtCurrentPasswordEditLayout);
        newLayout = (TextInputLayout)findViewById(R.id.txtNewPasswordEditLayout);
        confirmLayout = (TextInputLayout)findViewById(R.id.txtConfirmPasswordEditLayout);

        currentPass = (EditText)findViewById(R.id.txtCurrentPasswordEdit);
        newPass = (EditText)findViewById(R.id.txtNewPasswordEdit);
        confirmPass = (EditText)findViewById(R.id.txtConfirmPasswordEdit);

        button = (Button) findViewById(R.id.EditPassCancel);
        btnEditPass = (Button)findViewById(R.id.btnEditPassword);

        dialog = new ProgressDialog(EditpassActivity.this);
        dialog.setCancelable(false);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });

        btnEditPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    editPass();
                }
            }
        });

        currentPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (currentPass.getText().length()>7){
                    currentLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        newPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (newPass.getText().length()>7){
                    newLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        confirmPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (confirmPass.getText().toString().equals(newPass.getText().toString())){
                    confirmLayout.setErrorEnabled(false);
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

    private void editPass(){
        dialog.setMessage("Updating Password");
        dialog.show();
        SharedPreferences userPref = getApplicationContext().getSharedPreferences("user", getApplicationContext().MODE_PRIVATE);
        database = RoomDB.getInstance(getApplicationContext());
        user = database.userDao().getUser(userPref.getInt("id", 0));
        if (currentPass.getText().toString().equals(user.getPassword())){
            database.userDao().setNewPassword(userPref.getInt("id", 0), newPass.getText().toString());
            dialog.dismiss();
            openActivity();
            Toast.makeText(getApplicationContext(), "Edit Password Success", Toast.LENGTH_SHORT).show();
        }else{
            dialog.dismiss();
            Toast.makeText(getApplicationContext(), "Current Passowrd is Incorrect", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validate(){
        if(currentPass.getText().toString().length()<8){
            currentLayout.setErrorEnabled(true);
            currentLayout.setError("Current Password must be at least 8 characters");
            return false;
        }
        if(newPass.getText().toString().length()<8){
            newLayout.setErrorEnabled(true);
            newLayout.setError("New Password must be at least 8 characters");
            return false;
        }
        if(!newPass.getText().toString().equals(confirmPass.getText().toString())){
            confirmLayout.setErrorEnabled(true);
            confirmLayout.setError("Password not match");
            return false;
        }
        return true;
    }
}
