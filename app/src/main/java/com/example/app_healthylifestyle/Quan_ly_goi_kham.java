package com.example.app_healthylifestyle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app_healthylifestyle.CRUD.List_package;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Quan_ly_goi_kham extends AppCompatActivity {
    EditText eName,ePrice,eIndex,eDescribe;
    Button bnt_add,bnt_show;
    FirebaseFirestore db;
    String pName,pDescribe,pIndex,pPrice,pID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_goi_kham);

        eName = findViewById(R.id.txt_name);
        ePrice = findViewById(R.id.txt_price);
        eIndex = findViewById(R.id.txt_index);
        eDescribe = findViewById(R.id.txt_describe);
        bnt_add = findViewById(R.id.bnt_add);
        bnt_show = findViewById(R.id.bnt_show);
        db = FirebaseFirestore.getInstance();
        //add
        bnt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getIntent().getExtras();
                if (bundle != null){
                    //update
                    String ID = pID;
                    String Name = eName.getText().toString().trim();
                    String Price = ePrice.getText().toString().trim();
                    String Index = eIndex.getText().toString().trim();
                    String Describe = eDescribe.getText().toString().trim();
                    if (checkInput(Name,Price,Index,Describe)){
                        updateData(ID,Name,Price,Index,Describe);
                    }
                }else{
                    //add new
                    String Name = eName.getText().toString().trim();
                    String Price = ePrice.getText().toString().trim();
                    String Index = eIndex.getText().toString().trim();
                    String Describe = eDescribe.getText().toString().trim();
                    //add data
                    if (checkInput(Name,Price,Index,Describe)){
                        addData(Name,Price,Index,Describe);
                    }
                }
            }
        });
        //show
        bnt_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Quan_ly_goi_kham.this, List_package.class));
                finish();
            }
        });
        //update
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            //update
            bnt_add.setText("Cập nhật");
            //get data
            pName = bundle.getString("pName");
            pPrice = bundle.getString("pPrice");
            pDescribe = bundle.getString("pDescribe");
            pIndex = bundle.getString("pIndex");
            pID = bundle.getString("pID");
            //set data
            eName.setText(pName);
            eDescribe.setText(pDescribe);
            ePrice.setText(pPrice);
            eIndex.setText(pIndex);
        }
    }
    private boolean checkInput(String name, String price, String index, String describe) {
        if (name.isEmpty() || price.isEmpty() || index.isEmpty() || describe.isEmpty()) {
            Toast.makeText(Quan_ly_goi_kham.this, "Vui lòng nhập đầy đủ các trường!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void updateData(String id, String name, String price, String index, String describe) {
        db.collection("Package").document(id)
                .update("Name",name, "Price",price, "Index",index,"Describe",describe)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Quan_ly_goi_kham.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                        bnt_add.setText("Thêm");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Quan_ly_goi_kham.this, "Cập nhật thất bại!", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void addData(String name, String price, String index, String describe) {
        String id = UUID.randomUUID().toString();
        Map<String, Object> goikham = new HashMap<>();
        goikham.put("ID",id);
        goikham.put("Name",name);
        goikham.put("Price",price);
        goikham.put("Index",index);
        goikham.put("Describe",describe);

        db.collection("Package").document(id).set(goikham)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Quan_ly_goi_kham.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Quan_ly_goi_kham.this, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}