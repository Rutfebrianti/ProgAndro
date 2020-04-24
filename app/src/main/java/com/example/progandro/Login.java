package com.example.progandro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    public static final String LOGIN_PREF = "Login";
    public static final String REMEMBER_PREF = "GetIn";
    private FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    private static final String TAG = "login";

    DatabaseHelper databaseHelper;

    private EditText email;
    private EditText password;

    Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //menyimpan instance
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.login);
        TextView daftar = findViewById(R.id.daftar);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emailInput = email.getText().toString();
                final String passwordInput = password.getText().toString();

                //jika field kosong
                if (emailInput.isEmpty()) {
                    email.setError("Please Enter Your Email");
                    email.requestFocus();
                } else if (passwordInput.isEmpty()) {
                    password.setError("Please Enter Your Password");
                    password.requestFocus();
                } else {
                    firebaseAuth.signInWithEmailAndPassword(emailInput, passwordInput).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                email.getText().clear();
                                if (firebaseAuth.getCurrentUser().isEmailVerified()) {
                                    Log.d(TAG, "Login Success");
                                    Intent mainActivity = new Intent(Login.this, HomePageActivity.class);
                                    finish();
                                    startActivity(mainActivity);
                                } else {
                                    Toast.makeText(Login.this, "Please Verify Your Account", Toast.LENGTH_SHORT).show();
                                    password.getText().clear();
                                }
                            } else {
                                Toast.makeText(Login.this, "Incorrect Login Details", Toast.LENGTH_SHORT).show();
                                email.getText().clear();
                                password.getText().clear();
                            }
                        }
                    });
                }
            }
        });

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, SignUp.class));
            }
        });

    }
}
