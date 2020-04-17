package com.example.progandro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class ShowItem extends AppCompatActivity {
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = firebaseFirestore.collection("Data Mahasiswa");

    private AdapterDataMahasiswa adapterDataMahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showitem);

        FloatingActionButton buttonAdd = findViewById(R.id.tambah);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowItem.this, AddItem.class));
            }
        });
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        Query query = collectionReference.orderBy("Nama",Query.Direction.ASCENDING);

        FirestoreRecyclerOptions<DataMahasiswa> options = new FirestoreRecyclerOptions.Builder<DataMahasiswa>()
                .setQuery(query, DataMahasiswa.class)
                .build();

        adapterDataMahasiswa = new AdapterDataMahasiswa(options);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewMahasiswa);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterDataMahasiswa);
        adapterDataMahasiswa.notifyDataSetChanged();

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                adapterDataMahasiswa.deleteItem(viewHolder.getAdapterPosition());
            }
        }).attachToRecyclerView(recyclerView);

        adapterDataMahasiswa.setOnClickItemListener(new AdapterDataMahasiswa.OnClickItemListener() {
            @Override
            public void onClickItem(DocumentSnapshot documentSnapshot, int pos) {
                DataMahasiswa dataMahasiswa = documentSnapshot.toObject(DataMahasiswa.class);
                String Data = documentSnapshot.getId();
                String path = documentSnapshot.getReference().getPath();
                Toast.makeText(ShowItem.this, "Position " + pos + " In Firestore: " + Data, Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapterDataMahasiswa.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (adapterDataMahasiswa != null) {
            adapterDataMahasiswa.stopListening();
        }
    }
}
