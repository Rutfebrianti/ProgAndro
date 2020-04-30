package com.example.progandro;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public  class AboutActivity extends AppCompatActivity {

    Button buttonAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        buttonAdd = findViewById(R.id.btn_Add);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AboutActivity.this, ShowItem.class));
            }
        });

    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);

        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "Orientasi Horizontal", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Orientasi Vertikal", Toast.LENGTH_SHORT).show();
        }
    }

}


