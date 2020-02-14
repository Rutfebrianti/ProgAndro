package com.example.progandro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //menyimpan instance
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.login);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    public void signIn(){
        if (email.getText().toString().equals("rutfebrianty@gmail.com") && password.getText().toString().equals("12345")){
            Intent home = new Intent(MainActivity.this, Home.class);
            startActivity(home);
            Toast.makeText(MainActivity.this, "Welcome to homepage", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this, "Your wrong human", Toast.LENGTH_SHORT).show();
        }
    }

}
