package com.example.app_healthylifestyle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_healthylifestyle.AdapterUser.LichKhamAdapter;
import com.example.app_healthylifestyle.database.lich_kham;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class quan_li_lich_kham extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LichKhamAdapter adapter;
    private List<lich_kham> lichKhamList;
    private DatabaseReference databaseReference;
    private FloatingActionButton them;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_li_lich_kham);

        // Initialize RecyclerView and list
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        lichKhamList = new ArrayList<>();
        adapter = new LichKhamAdapter(this, lichKhamList);
        recyclerView.setAdapter(adapter);

        // Initialize Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("lich_kham");

        // Load data from Firebase
        loadDataFromFirebase();

        them = findViewById(R.id.fab);
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), them_lich_kham.class);
                startActivity(intent);
            }
        });
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
