package com.example.app_healthylifestyle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.app_healthylifestyle.CRUD.List_package;
import com.example.app_healthylifestyle.CRUD.List_user;
import com.example.app_healthylifestyle.CRUD.Update;
import com.example.app_healthylifestyle.MyAdapter.UserAdapter;
import com.example.app_healthylifestyle.database.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;

public class Tra_cuu_benh_nhan extends AppCompatActivity {
    Button show_user, update_user;
    EditText uName, uEmail, uPhone, uDate, uAddress;
    RadioGroup uGender;
    FirebaseFirestore db;
    String Name, Email, Phone, Date, Address, Gender, uID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tra_cuu_benh_nhan);

        show_user = findViewById(R.id.show_user);
        update_user = findViewById(R.id.update_user);
        uAddress = findViewById(R.id.address_user);
        uDate = findViewById(R.id.date_user);
        uName = findViewById(R.id.name_user);
        uEmail = findViewById(R.id.email_user);
        uPhone = findViewById(R.id.sdt_user);
        uGender = findViewById(R.id.gender_user);

        db = FirebaseFirestore.getInstance();
        //show danh sách
        show_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Tra_cuu_benh_nhan.this, List_user.class));
                finish();
            }
        });
        // Update
        update_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getIntent().getExtras();
                if (bundle != null) {
                    // Get selected gender
                    int selectedGenderId = uGender.getCheckedRadioButtonId();
                    if (selectedGenderId == R.id.gender_men_user) {
                        Gender = "Nam";
                    } else {
                        Gender = "Nữ";
                    }

                    // Update
                    String ID = uID;
                    String Name = uName.getText().toString().trim();
                    String Date = uDate.getText().toString().trim();
                    String Email = uEmail.getText().toString().trim();
                    String Phone = uPhone.getText().toString().trim();

                    if (checkInput(Name, Date, Email, Phone, Gender)) {
                        updateData(ID, Name, Date, Email, Phone, Gender);
                    }
                }
            }
        });
    }

    private boolean checkInput(String name, String date, String email, String phone, String gender) {
        if (name.isEmpty() || date.isEmpty() || email.isEmpty() || phone.isEmpty() || gender.isEmpty()) {
            Toast.makeText(Tra_cuu_benh_nhan.this, "Vui lòng nhập đầy đủ các trường!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void updateData(String id, String name, String date, String email, String phone, String gender) {
        db.collection("Users").document(id)
                .update("Name", name, "Date", date, "Email", email, "Phone", phone, "Gender", gender)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Tra_cuu_benh_nhan.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Tra_cuu_benh_nhan.this, "Cập nhật thất bại!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
