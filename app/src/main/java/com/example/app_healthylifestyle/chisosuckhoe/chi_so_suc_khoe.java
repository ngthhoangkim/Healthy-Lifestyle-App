package com.example.app_healthylifestyle.chisosuckhoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.app_healthylifestyle.R;
import com.example.app_healthylifestyle.home;
import com.example.app_healthylifestyle.thong_tin_ca_nhan;

public class chi_so_suc_khoe extends AppCompatActivity {
    LinearLayout drink,sleep,bmi;
    LinearLayout icon_schedule_cs,icon_home_cs,icon_goikham_cs,icon_thongtin_cs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_so_suc_khoe);

        drink = findViewById(R.id.drink);
        sleep = findViewById(R.id.sleep);
        bmi = findViewById(R.id.bmi);
        icon_home_cs= findViewById(R.id.home);
        icon_schedule_cs = findViewById(R.id.icon_schedule_cs);
        icon_goikham_cs = findViewById(R.id.icon_goikham_cs);
        icon_thongtin_cs = findViewById(R.id.icon_thongtin_cs);

        drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), drink.class);
                startActivity(intent);
                finish();
            }
        });

        bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), bmi.class);
                startActivity(intent);
                finish();
            }
        });

        sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Giac_ngu.class);
                startActivity(intent);
                finish();
            }
        });
        //icon
        icon_thongtin_cs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), thong_tin_ca_nhan.class);
                startActivity(intent);
                finish();
            }
        });
        icon_home_cs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), home.class);
                startActivity(intent);
            }
        });
    }
}