package com.example.app_healthylifestyle.CRUD;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.app_healthylifestyle.MyAdapter.DoctorAdapter;
import com.example.app_healthylifestyle.MyAdapter.PackageAdapter;
import com.example.app_healthylifestyle.Quan_ly_bac_si;
import com.example.app_healthylifestyle.R;
import com.example.app_healthylifestyle.database.Doctor;
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

public class List_Doctor extends AppCompatActivity {
    List<Doctor> doctorList = new ArrayList<>();
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseFirestore db;
    DoctorAdapter dAdapter;
    FloatingActionButton dAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_doctor);

        mRecyclerView = findViewById(R.id.dRecycler_view);
        dAdd = findViewById  (R.id.dfab);
        db = FirebaseFirestore.getInstance();
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        //show data
        showData();
        //bnt add
        dAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(List_Doctor.this, Quan_ly_bac_si.class));
                finish();
            }
        });
    }

    private void showData() {
        db.collection("Doctor").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        doctorList.clear();
                        //show
                        if (task.isSuccessful()){
                            for (DocumentSnapshot doc : task.getResult()){
                                Doctor listDoctor = new Doctor(
                                        doc.getString("Name"),
                                        doc.getString("ID"),
                                        doc.getString("Chuyenkhoa"),
                                        doc.getString("Kinhnghiem"),
                                        doc.getString("BenhVien"),
                                        doc.getString("MoTa"));
                                doctorList.add(listDoctor);
                            }
                            //adapter
                            dAdapter = new DoctorAdapter(List_Doctor.this,doctorList);
                            mRecyclerView.setAdapter(dAdapter);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(List_Doctor .this,"Lỗi",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void deletData(int index) {
        db.collection("Doctor").document(doctorList.get(index).getId())
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(List_Doctor.this,"Đã xóa",Toast.LENGTH_SHORT).show();
                        showData();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(List_Doctor.this,"Lỗi",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}