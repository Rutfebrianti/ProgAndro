package com.example.progandro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    private EditText emailUser;
    private EditText pwd;
    private EditText pwd2;
    DatabaseHelper databaseHelper;
    private Button pencetDisini;
    private FirebaseAuth firebaseAuth;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        databaseHelper = new DatabaseHelper(this);

        emailUser = findViewById(R.id.emailUser);
        pwd = findViewById(R.id.pwd);
        pwd2 = findViewById(R.id.pwd2);
        pencetDisini = findViewById(R.id.loginFromSignUp);
        Button daftar = findViewById(R.id.daftarBaru);

        pencetDisini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, Login.class));
                finish();
            }
        });

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth = FirebaseAuth.getInstance();
                String email = emailUser.getText().toString();
                String password = pwd.getText().toString();
                String passwordConfirm = pwd2.getText().toString();

                //cek field kosong
                if (email.isEmpty()) {
                    emailUser.setError("Please Enter Your Email");
                    emailUser.requestFocus();
                } else if (password.isEmpty()) {
                    pwd.setError("Please Enter Your Password");
                    pwd.requestFocus();
                } else if (passwordConfirm.isEmpty()) {
                    pwd2.setError("Please Enter Your Confirmation Password");
                    pwd2.requestFocus();
                }

                //jika password konfirmasi tidak sama
                else if (!password.equals(passwordConfirm)) {
                    Toast.makeText(SignUp.this, "Incorrect Confirmation Password", Toast.LENGTH_SHORT).show();
                    emailUser.getText().clear();
                    pwd.getText().clear();
                    pwd2.getText().clear();
                }

                //jika semua memenuhi ketentuan
                else if (!(password.isEmpty() && email.isEmpty()) && password.equals(passwordConfirm)) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(SignUp.this, "Check Your Email For Verification", Toast.LENGTH_SHORT).show();
                                            Intent login = new Intent(SignUp.this, Login.class);
                                            finish();
                                            startActivity(login);
                                        } else {
                                            Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(SignUp.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
