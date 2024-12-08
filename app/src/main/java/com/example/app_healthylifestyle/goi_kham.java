package com.example.app_healthylifestyle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.app_healthylifestyle.AdapterUser.PackageAdapterUser;
import com.example.app_healthylifestyle.database.Package;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class goi_kham extends AppCompatActivity {
    List<Package> packageList = new ArrayList<>();
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseFirestore db;
    PackageAdapterUser pAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goi_kham);

        db = FirebaseFirestore.getInstance();
        mRecyclerView = findViewById(R.id.userRecycler_view);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        db.collection("Package").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        packageList.clear();
                        //show
                        if (task.isSuccessful()){
                            for (DocumentSnapshot doc : task.getResult()){
                                Package listPackage = new Package(
                                        doc.getString("ID"),
                                        doc.getString("Describe"),
                                        doc.getString("Index"),
                                        doc.getString("Name"),
                                        doc.getString("Price"));
                                packageList.add(listPackage);
                            }
                            //adapter
                            pAdapter = new PackageAdapterUser(goi_kham.this,packageList);
                            mRecyclerView.setAdapter(pAdapter);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(goi_kham.this,"Lỗi",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}