package com.example.app_healthylifestyle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.app_healthylifestyle.database.lich_kham;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Dat_lich_kham extends AppCompatActivity {

    private RecyclerView recyclerView;
    private dat_lich_kham_1 adapter;
    private List<lich_kham> lichKhamList;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_lich_kham);

        // Initialize RecyclerView and list
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        lichKhamList = new ArrayList<>();
        adapter = new dat_lich_kham_1(this, lichKhamList);
        recyclerView.setAdapter(adapter);

        // Initialize Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("lich_kham");

        // Load data from Firebase
        loadDataFromFirebase();
    }

    private void loadDataFromFirebase() {
        // Example query to load data (replace with your Firebase query)
        Query query = databaseReference.orderByChild("tenBacSi");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                lichKhamList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    lich_kham lichKham = snapshot.getValue(lich_kham.class);
                    lichKhamList.add(lichKham);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Firebase Error", databaseError.getMessage());
            }
        });
    }
}
