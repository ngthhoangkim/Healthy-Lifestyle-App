package com.example.app_healthylifestyle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app_healthylifestyle.CRUD.List_Chuyen_khoa;
import com.example.app_healthylifestyle.CRUD.List_package;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Quan_ly_chuyen_khoa extends AppCompatActivity {
    EditText chuyenkhoa;
    Button dsbs, dsck, addck;
    FirebaseFirestore db;
    String ckName, ckID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_chuyen_khoa);

        chuyenkhoa = findViewById(R.id.txt_nameck);
        dsbs = findViewById(R.id.bnt_dsbs);
        dsck = findViewById(R.id.bnt_dsck);
        addck = findViewById(R.id.bnt_addck);
        db = FirebaseFirestore.getInstance();

        //add
        addck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getIntent().getExtras();
                if (bundle != null) {
                    //update
                    String ID = ckID;
                    String Name = chuyenkhoa.getText().toString().trim();
                    if(checkInput(Name)){
                        updateData(ID, Name);
                    }

                } else {
                    //add new
                    String Chuyenkhoa = chuyenkhoa.getText().toString().trim();
                    //add data
                   if(checkInput(Chuyenkhoa)){
                       addData(Chuyenkhoa);
                   }
                }
            }
        });
        //show
        dsck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Quan_ly_chuyen_khoa.this, List_Chuyen_khoa.class));
                finish();
            }
        });
        //update
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            //update
            addck.setText("Cập nhật");
            //get data
            ckName = bundle.getString("ckName");
            ckID = bundle.getString("ckID");
            //set data
            chuyenkhoa.setText(ckName);
        }
    }

    private void updateData(String id, String name) {
        db.collection("ChuyenKhoa").document(id)
                .update("Name", name)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Quan_ly_chuyen_khoa.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                        addck.setText("Thêm");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Quan_ly_chuyen_khoa.this, "Cập nhật thất bại!", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void addData(String Chuyenkhoa) {
        String id = UUID.randomUUID().toString();
        Map<String, Object> chuyenkhoa = new HashMap<>();
        chuyenkhoa.put("ID", id);
        chuyenkhoa.put("Name", Chuyenkhoa);
        db.collection("ChuyenKhoa").document(id).set(chuyenkhoa)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Quan_ly_chuyen_khoa.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Quan_ly_chuyen_khoa.this, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private boolean checkInput(String name) {
        if (name.isEmpty()) {
            Toast.makeText(Quan_ly_chuyen_khoa.this, "Vui lòng nhập đầy đủ các trường!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}