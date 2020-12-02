package com.example.praktikum.AuthAndUser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.praktikum.Database.RoomDB;
import com.example.praktikum.Model.User;
import com.example.praktikum.ProfileActivity;
import com.example.praktikum.R;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditprofileActivity extends AppCompatActivity {

    private Button button, btnEdit, btnBirthdate, btnGender;
    private TextView name, mobile, email, address, birthdate, photo;
    TextInputLayout layoutName, layoutEmail, layoutMobile, layoutAddress, layoutBirthdate, layoutGender;
    private AutoCompleteTextView gender;
    ProgressDialog dialog;
    RoomDB database;
    User user;

    private static final String[] jk = new String[] {"Male", "Female"};
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        isLogin();
        init();
    }

    public void openActivity() {
        Intent intent1 = new Intent(EditprofileActivity.this, ProfileActivity.class);
        startActivity(intent1);
        finish();
    }

    private void init(){
        dateFormatter = new SimpleDateFormat("YYYY-MM-dd", Locale.US);
        btnBirthdate = (Button)findViewById(R.id.btnBirthdateEdit);
        btnGender = (Button)findViewById(R.id.btnGenderEdit);
        button = (Button) findViewById(R.id.EditProfileCancel);
        btnEdit = (Button)findViewById(R.id.btnEditProfile);

        layoutName = (TextInputLayout)findViewById(R.id.txtNameEditLayout);
        layoutAddress = (TextInputLayout)findViewById(R.id.txtAddressEditLayout);
        layoutEmail = (TextInputLayout)findViewById(R.id.txtEmailEditLayout);
        layoutGender = (TextInputLayout)findViewById(R.id.txtGenderEditLayout);
        layoutMobile = (TextInputLayout)findViewById(R.id.txtMobileEditLayout);
        layoutBirthdate = (TextInputLayout)findViewById(R.id.txtBirthdateEditLayout);

        name = (TextView)findViewById(R.id.txtNameEdit);
        mobile = (TextView)findViewById(R.id.txtMobileEdit);
        email = (TextView)findViewById(R.id.txtEmailEdit);
        address = (TextView)findViewById(R.id.txtAddressEdit);
        birthdate = (TextView)findViewById(R.id.txtBirthdateEdit);
        gender = (AutoCompleteTextView)findViewById(R.id.txtGenderEdit);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, jk);
        gender.setAdapter(adapter);

        btnGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender.showDropDown();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });

        btnBirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        setUserInfo();

        dialog = new ProgressDialog(EditprofileActivity.this);
        dialog.setCancelable(false);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    edit();
                    openActivity();
                    finish();
                }
            }
        });

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!name.getText().toString().isEmpty()){
                    layoutName.setErrorEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

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

        gender.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!gender.getText().toString().isEmpty()){
                    layoutGender.setErrorEnabled(true);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(mobile.getText().toString().length()>9){
                    layoutMobile.setErrorEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        address.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!address.getText().toString().isEmpty()){
                    layoutAddress.setErrorEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        birthdate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!birthdate.getText().toString().isEmpty()){
                    layoutBirthdate.setErrorEnabled(false);
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


    private void setUserInfo(){
        SharedPreferences userPref = getApplicationContext().getSharedPreferences("user", getApplicationContext().MODE_PRIVATE);
        String nameLogin = userPref.getString("name",null);
        String emailLogin = userPref.getString("email",null);
        String mobileLogin = userPref.getString("mobile",null);
        String addressLogin = userPref.getString("address",null);
        String genderLogin = userPref.getString("gender",null);
        String birthdateLogin = userPref.getString("birthdate",null);

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

            startActivity(new Intent(EditprofileActivity.this,EditprofileActivity.class));
        }else{
            startActivity(new Intent(EditprofileActivity.this, LoginActivity.class));
            finish();
        }
    }

    private void edit(){
        dialog.setMessage("Updating Data");
        SharedPreferences userPref = getApplicationContext().getSharedPreferences("user", getApplicationContext().MODE_PRIVATE);
        database = RoomDB.getInstance(getApplicationContext());
        database.userDao().updateUser(userPref.getInt("id", 0), name.getText().toString(), email.getText().toString(), mobile.getText().toString(), gender.getText().toString(), address.getText().toString(), birthdate.getText().toString());
        user = database.userDao().getUser(userPref.getInt("id", 0));
        SharedPreferences.Editor editor = userPref.edit();
        editor.putBoolean("isLoggedIn", true);
        editor.putString("name", user.getName());
        editor.putString("email", user.getEmail());
        editor.putString("mobile", user.getMobile());
        editor.putString("address", user.getAddress());
        editor.putString("gender", user.getGender());
        editor.putString("birthdate", user.getBirthdate());
        editor.apply();
        Toast.makeText(getApplicationContext(), "Edit Success", Toast.LENGTH_SHORT).show();
        dialog.dismiss();
    }

    private void showDateDialog() {
        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                birthdate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }

    private boolean validate(){
        if(name.getText().toString().length()<8){
            layoutName.setErrorEnabled(true);
            layoutName.setError("Name must be at least 8");
            return false;
        }
        if(email.getText().toString().isEmpty()){
            layoutEmail.setErrorEnabled(true);
            layoutEmail.setError("Email is Required");
            return false;
        }
        if(gender.getText().toString().isEmpty()){
            layoutGender.setErrorEnabled(true);
            layoutGender.setError("Gender is Required");
            return false;
        }
        if(mobile.getText().toString().length()<10){
            layoutMobile.setErrorEnabled(true);
            layoutMobile.setError("Please Input a Valid Number Phone");
            return false;
        }
        if(address.getText().toString().isEmpty()){
            layoutAddress.setErrorEnabled(true);
            layoutAddress.setError("Address is Required");
            return false;
        }
        if(birthdate.getText().toString().isEmpty()){
            layoutBirthdate.setErrorEnabled(true);
            layoutBirthdate.setError("Birthdate is Required");
            return false;
        }
        return true;
    }
}
