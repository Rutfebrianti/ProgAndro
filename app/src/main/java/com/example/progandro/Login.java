package com.example.progandro;

import android.content.ComponentName;
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
    public static final String MY_PREFERENCES = "MyPrefs";

    DatabaseHelper databaseHelper;

    private EditText email;
    private EditText password;
    Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //menyimpan instance
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        databaseHelper = new DatabaseHelper(this);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.login);
        TextView daftar = findViewById(R.id.daftar);

        sharedPreferences = getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE);

        if (sharedPreferences.contains("email") && sharedPreferences.contains("password")) {
            startActivity(new Intent(Login.this, HomePageActivity.class));
        }

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, SignUp.class));
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailMasuk = email.getText().toString();
                String passwordMasuk = password.getText().toString();
                Boolean checkEmailPass = databaseHelper.emailPassword(emailMasuk, passwordMasuk);
                if (checkEmailPass==true) {
                    startActivity(new Intent(Login.this, HomePageActivity.class));
                    Toast.makeText(getApplicationContext(), "Login Succesfully", Toast.LENGTH_SHORT).show();

                  Bundle extras = new Bundle();
                  extras.putString("ingat", "true");
                  Intent intent = new Intent(Login.this, HomePageActivity.class);


                //  Intent intent = new Intent(Login.this,HomePageActivity.class);
                 //   startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(),"Login Failed", Toast.LENGTH_SHORT).show();

            }
        });
    }

        private void broadcaster(){
        Intent broadcastIntent = new Intent("MY_ACTION");
        broadcastIntent.setComponent(new ComponentName(getPackageName(),
                "com.example.tugas.MyBroadcastReceiver"));

        getApplicationContext().sendBroadcast(broadcastIntent);
        }


}
