package com.example.app_healthylifestyle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app_healthylifestyle.CRUD.List_Doctor;
import com.example.app_healthylifestyle.CRUD.List_package;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Quan_ly_bac_si extends AppCompatActivity {
    EditText namebs, chuyenkhoabs, kinhnghiembs, benhvienbs, motabs;
    Button addbs, showbs, showck;
    FirebaseFirestore db;
    String Namebs, Chuyenkhoabs, Kinhnghiembs, Benhvienbs, Motabs,IDbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_bac_si);

        namebs = findViewById(R.id.txt_namebs);
        chuyenkhoabs = findViewById(R.id.txt_chuyenkhoa);
        kinhnghiembs = findViewById(R.id.txt_kinhnghiem);
        benhvienbs = findViewById(R.id.txt_benhvien);
        motabs = findViewById(R.id.txt_chitiet);
        addbs = findViewById(R.id.bnt_addbs);
        showbs = findViewById(R.id.bnt_showbs);
        showck = findViewById(R.id.bnt_showck);

        db = FirebaseFirestore.getInstance();
        //add
        addbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getIntent().getExtras();
                if (bundle != null) {
                    //update
                    String ID = IDbs;
                    String Name = namebs.getText().toString().trim();
                    String Benhvien = benhvienbs.getText().toString().trim();
                    String Kinhnghiem = kinhnghiembs.getText().toString().trim();
                    String Chuyenkhoa = chuyenkhoabs.getText().toString().trim();
                    String MoTa = motabs.getText().toString().trim();
                    if (checkInput(Name,Chuyenkhoa,Benhvien,Kinhnghiem,MoTa)){
                        updateData(ID, Name, Benhvien, Kinhnghiem, Chuyenkhoa,MoTa);
                    }
                } else {
                    //add new
                    String Name = namebs.getText().toString().trim();
                    String ChuyenKhoa = chuyenkhoabs.getText().toString().trim();
                    String KinhNghiem = kinhnghiembs.getText().toString().trim();
                    String BenhVien = benhvienbs.getText().toString().trim();
                    String MoTa = motabs.getText().toString().trim();
                    //add data
                    if (checkInput(Name,ChuyenKhoa,BenhVien,KinhNghiem,MoTa)){
                        addData(Name, ChuyenKhoa, KinhNghiem, BenhVien, MoTa);
                    }

                }
            }
        });
        //show
        showbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Quan_ly_bac_si.this, List_Doctor.class));
                finish();
            }
        });
        //update
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            //update
            addbs.setText("Cập nhật");
            //get data
            Namebs = bundle.getString("Namebs");
            Chuyenkhoabs = bundle.getString("Chuyenkhoabs");
            Kinhnghiembs = bundle.getString("Kinhnghiembs");
            Benhvienbs = bundle.getString("Benhvienbs");
            Motabs = bundle.getString("Motabs");
            IDbs = bundle.getString("IDbs");
            //set data
            namebs.setText(Namebs);
            chuyenkhoabs.setText(Chuyenkhoabs);
            kinhnghiembs.setText(Kinhnghiembs);
            benhvienbs.setText(Benhvienbs);
            motabs.setText(Motabs);
        }
        //show chuyên khoa
        showck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Quan_ly_bac_si.this,Quan_ly_chuyen_khoa.class));
            }
        });

    }

    private void updateData(String id, String name, String benhvien, String kinhnghiem, String chuyenkhoa, String moTa) {
        db.collection("Doctor").document(id)
                .update("Name",name, "BenhVien",benhvien, "KinhNghiem",kinhnghiem,"ChuyenKhoa",chuyenkhoa,"MoTa",moTa)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Quan_ly_bac_si.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                        addbs.setText("Thêm");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Quan_ly_bac_si.this, "Cập nhật thất bại!", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void addData(String name, String chuyenKhoa, String kinhNghiem, String benhVien, String moTa) {
        String id = UUID.randomUUID().toString();
        Map<String, Object> doctor = new HashMap<>();
        doctor.put("ID", id);
        doctor.put("Name", name);
        doctor.put("ChuyenKhoa", chuyenKhoa);
        doctor.put("KinhNghiem", kinhNghiem);
        doctor.put("BenhVien", benhVien);
        doctor.put("MoTa", moTa);

        db.collection("Doctor").document(id).set(doctor)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Quan_ly_bac_si.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Quan_ly_bac_si.this, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private boolean checkInput(String namebs,String chuyenkhoabs,String benhvienbs,String Kinhnghiembs,String Motabs) {
        if (namebs.isEmpty() ||chuyenkhoabs.isEmpty() || benhvienbs.isEmpty() || Kinhnghiembs.isEmpty() || Motabs.isEmpty() ) {
            Toast.makeText(Quan_ly_bac_si.this, "Vui lòng nhập đầy đủ các trường!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}