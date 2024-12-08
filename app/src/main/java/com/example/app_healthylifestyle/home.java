package com.example.app_healthylifestyle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.app_healthylifestyle.CRUD.List_package;
import com.example.app_healthylifestyle.chisosuckhoe.chi_so_suc_khoe;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class home extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    TextView name;
    ImageView logout,icon_infor,icon_home;
    LinearLayout thongtin,lichkham,lichsu,chiso,goikham;
    private FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        name = findViewById(R.id.txt_user);
        logout = findViewById(R.id.logout);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        fstore = FirebaseFirestore.getInstance();
        //icon
        icon_infor = findViewById(R.id.icon_user);
        icon_home = findViewById(R.id.icon_home);
        //menu
        thongtin = findViewById(R.id.bnt_infor);
        lichkham = findViewById(R.id.bnt_lichkham);
        chiso = findViewById(R.id.bnt_chiso);
        goikham = findViewById(R.id.bnt_goikham);

        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), dang_nhap.class);
            startActivity(intent);
            finish();
        }else {
            DocumentReference df = fstore.collection("Users").document(user.getUid());
            df.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException e) {
                    if (snapshot != null && snapshot.exists()) {
                        String userName = snapshot.getString("Name");
                        if (userName != null) {
                            name.setText("Xin chào "+ userName);
                        }
                    }
                }
            });
        }
       //home
        icon_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), home.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), dang_nhap.class);
                startActivity(intent);
                finish();
            }
        });
        //thông tin cá nhân
        thongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), thong_tin_ca_nhan.class);
                startActivity(intent);
                finish();
            }
        });
        //icon
        icon_infor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), thong_tin_ca_nhan.class);
                startActivity(intent);
                finish();
            }
        });
        //đặt lịch khám
        lichkham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Dat_lich_kham.class);
                startActivity(intent);
                finish();
            }
        });
        //chỉ số bệnh
        chiso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), chi_so_suc_khoe.class);
                startActivity(intent);
                finish();
            }
        });
        //gói khám
        goikham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this, goi_kham.class));
                finish();
            }
        });

    }
}