package com.example.progandro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    public static final String LOGIN_PREF = "Login";
    public static final String REMEMBER_PREF = "GetIn";

    DatabaseHelper databaseHelper;

    private EditText email;
    private EditText password;

    String emailMasuk;
    String passwordMasuk;

    Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //menyimpan instance

        sharedPreferences = getSharedPreferences(LOGIN_PREF, MODE_PRIVATE);
        String check = sharedPreferences.getString(REMEMBER_PREF, "");

        if (check.equals("true")) {
            startActivity(new Intent(Login.this, HomePageActivity.class));
            finish();
        }
        databaseHelper = new DatabaseHelper(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.login);
        TextView daftar = findViewById(R.id.daftar);



        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, SignUp.class));
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailMasuk = email.getText().toString();
                passwordMasuk = password.getText().toString();
                Boolean checkEmailPass = databaseHelper.emailPassword(emailMasuk, passwordMasuk);
                if (checkEmailPass==true){
                    Toast.makeText(getApplicationContext(), "Login Succesfully", Toast.LENGTH_SHORT).show();

                    SharedPreferences preferences = getSharedPreferences(LOGIN_PREF, MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(REMEMBER_PREF, "true");
                    editor.apply();

                    startActivity(new Intent(Login.this, HomePageActivity.class));
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
