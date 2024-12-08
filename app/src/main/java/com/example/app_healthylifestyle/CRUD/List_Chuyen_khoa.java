package com.example.app_healthylifestyle.CRUD;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.app_healthylifestyle.MyAdapter.ChuyenkhoaAdapater;
import com.example.app_healthylifestyle.MyAdapter.PackageAdapter;
import com.example.app_healthylifestyle.R;
import com.example.app_healthylifestyle.database.ChuyenKhoa;
import com.example.app_healthylifestyle.database.Package;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class List_Chuyen_khoa extends AppCompatActivity {
    List<ChuyenKhoa> chuyenKhoaList = new ArrayList<>();
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseFirestore db;
    ChuyenkhoaAdapater ckAdapter;
    FloatingActionButton ckAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_chuyen_khoa);

        mRecyclerView = findViewById(R.id.ckRecycler_view);
        ckAdd = findViewById(R.id.ckfab);
        db = FirebaseFirestore.getInstance();
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        //show data
        showData();
    }

    private void showData() {
        db.collection("ChuyenKhoa").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        chuyenKhoaList.clear();
                        //show
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot doc : task.getResult()) {
                                ChuyenKhoa list = new ChuyenKhoa(
                                        doc.getString("ID"),
                                        doc.getString("Name"));
                                chuyenKhoaList.add(list);
                            }
                            //adapter
                            ckAdapter = new ChuyenkhoaAdapater(chuyenKhoaList, List_Chuyen_khoa.this);
                            mRecyclerView.setAdapter(ckAdapter);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(List_Chuyen_khoa.this, "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void deletData(int index) {
        db.collection("ChuyenKhoa").document(chuyenKhoaList.get(index).getId())
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(List_Chuyen_khoa.this, "Đã xóa", Toast.LENGTH_SHORT).show();
                        showData();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(List_Chuyen_khoa.this, "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}