package com.example.progandro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {
    private EditText emailUser,pwd,pwd2;
    DatabaseHelper databaseHelper;
    private Button pencetDisini;

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
            //@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
//                if (checking(String.valueOf(pwd.getText()),String.valueOf(pwd2.getText()),String.valueOf(emailUser.getText()))){
//                    Intent homepage = new Intent(SignUp.this,HomePageActivity.class);
//                    finish();
//                    startActivity(homepage);
//                }
                String email = emailUser.getText().toString();
                String password1 = pwd.getText().toString();
                String password2 = pwd2.getText().toString();
                if (email.equals("") || password1.equals("") || password2.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fields Are Empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (password1.equals(password2)) {
                        Boolean checkEmail = databaseHelper.checkEmail(email);
                        if (checkEmail == true) {
                            Boolean insert = databaseHelper.insert(email, password1);
                            if (insert == true) {
                                Toast.makeText(getApplicationContext(), "Registered Succesfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Email Already Exists", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(getApplicationContext(), "Silahkan tekan login disini", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
//    private boolean checking(String pass1, String pass2, String email){
//        if (!pass1.isEmpty() && !email.isEmpty() && !pass1.equals(pass2)){
//            Toast.makeText(this,"Field Must Not Empty", Toast.LENGTH_SHORT).show();
//            return true;
//        }
//        else if (pass1.isEmpty() || pass2.isEmpty() || email.isEmpty()){
//            Toast.makeText(this,"Must Fill The Form", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        else {
//            return false;
//        }
//    }
}
