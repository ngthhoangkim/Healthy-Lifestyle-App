package com.example.app_healthylifestyle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.app_healthylifestyle.database.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.auth.User;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class thong_tin_ca_nhan extends AppCompatActivity {
    DatePickerDialog picker;
    EditText name,email,sdt,address;
    RadioGroup gender;
    RadioButton men,women;
    Button submit;
    ImageView icon_infor,icon_home;
    private FirebaseFirestore fstore;
    TextView date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_ca_nhan);

        fstore = FirebaseFirestore.getInstance();
        date = findViewById(R.id.date);
        gender = findViewById(R.id.gender);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        sdt = findViewById(R.id.sdt);
        submit = findViewById(R.id.bnt_submit);
        men = findViewById(R.id.gender_men);
        women = findViewById(R.id.gender_women);
        address = findViewById(R.id.address);
        //icon
        icon_infor = findViewById(R.id.icon_user_tt);
        icon_home = findViewById(R.id.icon_home_tt);
        //set ngày
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                picker = new DatePickerDialog(thong_tin_ca_nhan.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);
                picker.show();
            }
        });
        //lấy data cũ
        setUser();
        //update data
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user == null){
                    return;
                }

                String Name = name.getText().toString();
                String Address = address.getText().toString();
                String Email = email.getText().toString();
                String Phone = sdt.getText().toString();
                String Birthday = date.getText().toString();
                // Khong duoc bo trong
                if (TextUtils.isEmpty(Name)) {
                    Toast.makeText(thong_tin_ca_nhan.this, "Nhập tên!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Address)) {
                    Toast.makeText(thong_tin_ca_nhan.this, "Nhập địa chỉ!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Email)) {
                    Toast.makeText(thong_tin_ca_nhan.this, "Nhập email!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Phone)) {
                    Toast.makeText(thong_tin_ca_nhan.this, "Nhập số điện thoại!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Birthday)) {
                    Toast.makeText(thong_tin_ca_nhan.this, "Nhập ngày sinh!", Toast.LENGTH_SHORT).show();
                    return;
                }
                //lấy gender
                int selectedGenderId = gender.getCheckedRadioButtonId();
                String Gender;
                if (selectedGenderId == R.id.gender_men) {
                    Gender = "Nam";
                } else {
                    Gender = "Nữ";
                }
                if (TextUtils.isEmpty(Gender)) {
                    Toast.makeText(thong_tin_ca_nhan.this, "Nhập giới tính!", Toast.LENGTH_SHORT).show();
                    return;
                }
                user.updateEmail(Email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(thong_tin_ca_nhan.this, "Cập nhật email thành công!",Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(thong_tin_ca_nhan.this, "Cập nhật email thất bại!",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                //name
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(Name)
                        .build();
                user.updateProfile(profileUpdates)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(thong_tin_ca_nhan.this, "Cập nhật thành công!",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                fstore = FirebaseFirestore.getInstance();
                DocumentReference df = fstore.collection("Users").document(user.getUid());
                //put trường dữ liệu
                Map<String, String> userInfo = new HashMap<>();
                userInfo.put("Phone",Phone);
                userInfo.put("Birthday",Birthday);
                userInfo.put("Name",Name);
                userInfo.put("Address",Address);
                userInfo.put("Gender",Gender);
                //update
                df.set(userInfo, SetOptions.merge());
            }
        });
    }
    //hiển thị thông tin user
    private void setUser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null){
            return;
        }
        DocumentReference df = fstore.collection("Users").document(user.getUid());
        df.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        name.setText(document.getString("Name"));
                        email.setText(user.getEmail());
                        sdt.setText(document.getString("Phone"));
                        date.setText(document.getString("Birthday"));
                        String genderValue = document.getString("Gender");
                        if ("Nam".equals(genderValue)) {
                            men.setChecked(true);
                        } else {
                            women.setChecked(true);
                        }
                    }
                }else {
                    Toast.makeText(thong_tin_ca_nhan.this, "Lỗi: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        //icon
        icon_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), home.class);
                startActivity(intent);
            }
        });
        icon_infor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), thong_tin_ca_nhan.class);
                startActivity(intent);
                finish();
            }
        });
    }

}