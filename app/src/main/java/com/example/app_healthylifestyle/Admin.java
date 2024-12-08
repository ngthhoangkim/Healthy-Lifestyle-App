package com.example.app_healthylifestyle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Admin extends AppCompatActivity {
    ImageView logout;
    LinearLayout tcbenhnhan,goikham,qlbs, lichkham,icon_schedule_a,icon_home_a,icon_goikham_a,icon_benhnha_a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        lichkham= findViewById(R.id.lichkham);
        logout = findViewById(R.id.admin_logout);
        tcbenhnhan = findViewById(R.id.bnt_benhnhan);
        goikham = findViewById(R.id.bnt_Agoikham);
        qlbs = findViewById(R.id.qlbs);
        icon_schedule_a = findViewById(R.id.icon_schedule_a);
        icon_home_a = findViewById(R.id.icon_home_a);
        icon_goikham_a = findViewById(R.id.icon_goikham_a);
        icon_benhnha_a = findViewById(R.id.icon_benhnha_a);
        //home
        icon_home_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Admin.class);
                startActivity(intent);
            }
        });
        //Tra cứu bệnh nhân
        tcbenhnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Tra_cuu_benh_nhan.class);
                startActivity(intent);
            }
        });
        icon_benhnha_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Tra_cuu_benh_nhan.class);
                startActivity(intent);
            }
        });
        //quản lý gói khám
        goikham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Quan_ly_goi_kham.class);
                startActivity(intent);
            }
        });
        icon_goikham_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Quan_ly_goi_kham.class);
                startActivity(intent);
            }
        });
        //quản lý bác sĩ
        qlbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Quan_ly_bac_si.class);
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

        lichkham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), quan_li_lich_kham.class);
                startActivity(intent);
            }
        });
        icon_schedule_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), quan_li_lich_kham.class);
                startActivity(intent);
            }
        });
    }
}