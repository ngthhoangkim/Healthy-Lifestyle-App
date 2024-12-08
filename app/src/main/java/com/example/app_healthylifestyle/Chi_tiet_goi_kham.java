package com.example.app_healthylifestyle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
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

public class Chi_tiet_goi_kham extends AppCompatActivity {
    TextView tengoi, chiso, gia, mota;
    Button datlich, lichkham;
    FirebaseFirestore db;
    List<Package> packageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_goi_kham);

        tengoi = findViewById(R.id.show_name);
        chiso = findViewById(R.id.show_index);
        gia = findViewById(R.id.show_price);
        mota = findViewById(R.id.show_describe);
        datlich = findViewById(R.id.bnt_dat);
        lichkham = findViewById(R.id.bnt_show);
        db = FirebaseFirestore.getInstance();

        // Lấy ID từ Intent
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            db.collection("Package").get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
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

                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Chi_tiet_goi_kham.this,"Lỗi",Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}