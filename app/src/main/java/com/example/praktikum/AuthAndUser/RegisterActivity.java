package com.example.praktikum.AuthAndUser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.praktikum.Database.RoomDB;
import com.example.praktikum.Model.User;
import com.example.praktikum.R;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {

    View view;
    TextView textView, photo;
    private AutoCompleteTextView gender;
    EditText name, email,password, passwordConfirm, mobile, address, birthdate;
    TextInputLayout layoutName, layoutEmail, layoutPassword, layoutPasswordConfirm, layoutMobile, layoutAddress, layoutBirthdate, layoutGender;
    Button btnregis, btnBirthdate, btnGender;
    ProgressDialog dialog;
    RoomDB database;
    User user;

    private static final String[] jk = new String[] {"Male", "Female"};

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
    }
    private void init(){
        database = RoomDB.getInstance(getApplicationContext());

        dateFormatter = new SimpleDateFormat("YYYY-MM-dd", Locale.US);
        layoutName = (TextInputLayout)findViewById(R.id.txtLayoutNameSignUp);
        layoutEmail = (TextInputLayout)findViewById(R.id.txtLayoutEmailSignUp);
        layoutPassword = (TextInputLayout)findViewById(R.id.txtLayoutPasswordSignUp);
        layoutPasswordConfirm = (TextInputLayout)findViewById(R.id.txtLayoutPasswordConfirmSignUp);
        layoutMobile = (TextInputLayout)findViewById(R.id.txtLayoutMobileSignUp);
        layoutAddress = (TextInputLayout)findViewById(R.id.txtLayoutAddressSignUp);
        layoutBirthdate = (TextInputLayout)findViewById(R.id.txtLayoutBirthdateSignUp);
        layoutGender = (TextInputLayout)findViewById(R.id.txtLayoutGenderSignUp);

        name = (EditText)findViewById(R.id.txtNameSignUp);
        email = (EditText)findViewById(R.id.txtEmailSignUp);
        password = (EditText)findViewById(R.id.txtPasswordSignUp);
        passwordConfirm = (EditText)findViewById(R.id.txtPasswordConfirmSignUp);
        mobile = (EditText)findViewById(R.id.txtMobileSignUp);
        address = (EditText)findViewById(R.id.txtAddressSignUp);
        birthdate = (EditText)findViewById(R.id.txtBirthdateSignUp);
        gender = findViewById(R.id.txtGenderSignUp);
        btnBirthdate = (Button) findViewById(R.id.btnBirthdateSignUp);
        btnregis = (Button)findViewById(R.id.btnSignUp);
        btnGender = (Button)findViewById(R.id.btnGenderSignUp);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, jk);
        gender.setAdapter(adapter);

        btnGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender.showDropDown();
            }
        });

        btnBirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        dialog = new ProgressDialog(RegisterActivity.this);
        dialog.setCancelable(false);

        textView = (TextView)findViewById(R.id.logindulu);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                Toast.makeText(RegisterActivity.this,"Please Sign In", Toast.LENGTH_LONG).show();
            }
        });

        btnregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    register();
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

        passwordConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (passwordConfirm.getText().toString().equals(password.getText().toString())){
                    layoutPasswordConfirm.setErrorEnabled(false);
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
        if(password.getText().toString().length()<8){
            layoutPassword.setErrorEnabled(true);
            layoutPassword.setError("Password at least 8 characters");
            return false;
        }
        if(!passwordConfirm.getText().toString().equals(password.getText().toString())){
            layoutPassword.setErrorEnabled(true);
            layoutPassword.setError("Password not match");
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

    private void register(){
        dialog.setMessage("Registering");
        dialog.show();
        database = RoomDB.getInstance(getApplicationContext());
        user = database.userDao().cekEmail(email.getText().toString());
        if (user == null){
            User user = new User();
            user.setName(name.getText().toString());
            user.setEmail(email.getText().toString());
            user.setPassword(password.getText().toString());
            user.setAddress(address.getText().toString());
            user.setBirthdate(birthdate.getText().toString());
            user.setGender(gender.getText().toString());
            user.setMobile(mobile.getText().toString());
            user.setRole("2");
            database.userDao().insert(user);
            dialog.dismiss();
            Intent intent1 = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent1);
            Toast.makeText(getApplicationContext(), "Register Success", Toast.LENGTH_SHORT).show();
        }else{
            dialog.dismiss();
            Toast.makeText(getApplicationContext(), "Oops, Email Has been Used", Toast.LENGTH_SHORT).show();
        }

    }


}
