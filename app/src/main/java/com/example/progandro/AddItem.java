package com.example.progandro;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddItem extends AppCompatActivity {

    private EditText namaMahasiswa;
    private EditText nimMahasiswa;
    private EditText phoneMahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.additem);

        setTitle("New Note");

        namaMahasiswa = findViewById(R.id.id_name);
        nimMahasiswa = findViewById(R.id.id_nim);
        phoneMahasiswa = findViewById(R.id.id_phone);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.new_matkul_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.save_matkul) {
            saveData();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void saveData() {
        String nama = namaMahasiswa.getText().toString();
        String phone = phoneMahasiswa.getText().toString();
        String nim = nimMahasiswa.getText().toString();

        if (nama.trim().isEmpty() || nim.trim().isEmpty() || phone.trim().isEmpty()) {
            Toast.makeText(this, "Insert The Field Before Continue", Toast.LENGTH_SHORT).show();
            return;
        }
        CollectionReference collectionReference = FirebaseFirestore.getInstance().collection("Data Mahasiswa");
        collectionReference.add(new DataMahasiswa(nama, phone, nim));
        Toast.makeText(this,"Data Added to Firestore",Toast.LENGTH_SHORT).show();
        finish();
    }
}
