package com.example.app_healthylifestyle.CRUD;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.app_healthylifestyle.MyAdapter.PackageAdapter;
import com.example.app_healthylifestyle.MyAdapter.UserAdapter;
import com.example.app_healthylifestyle.Quan_ly_goi_kham;
import com.example.app_healthylifestyle.R;
import com.example.app_healthylifestyle.Tra_cuu_benh_nhan;
import com.example.app_healthylifestyle.database.Package;
import com.example.app_healthylifestyle.database.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class List_package extends AppCompatActivity {
    List<Package> packageList = new ArrayList<>();
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseFirestore db;
    PackageAdapter pAdapter;
    FloatingActionButton pAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_package);

        mRecyclerView = findViewById(R.id.pRecycler_view);
        pAdd = findViewById(R.id.fab);
        db = FirebaseFirestore.getInstance();
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        //show data
        showData();
        //add
        pAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(List_package.this, Quan_ly_goi_kham.class));
            }
        });
    }

    private void showData() {
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
                            pAdapter = new PackageAdapter(List_package.this,packageList);
                            mRecyclerView.setAdapter(pAdapter);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(List_package.this,"Lỗi",Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void deletData(int index){
        db.collection("Package").document(packageList.get(index).getId())
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(List_package.this,"Đã xóa",Toast.LENGTH_SHORT).show();
                        showData();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
}