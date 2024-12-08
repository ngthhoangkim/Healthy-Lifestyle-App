package com.example.app_healthylifestyle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class chon_thoi_gian_kham extends AppCompatActivity {

    TextView bsTextView;
    TextView benhvienTextView;
    TextView khamTextView;
    Button btnThuBa;
    Button btnThuTu;
    EditText tenEditText;
    EditText sdtEditText;
    RadioGroup radioGroup;

    private FirebaseFirestore fStore;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_thoi_gian_kham);

        // Initialize Firebase Auth and Firestore
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        // Initialize views
        bsTextView = findViewById(R.id.bs);
        benhvienTextView = findViewById(R.id.benhvien);
        khamTextView = findViewById(R.id.kham);
        btnThuBa = findViewById(R.id.btnThuBa);
        btnThuTu = findViewById(R.id.btnThuTu);
        tenEditText = findViewById(R.id.ten);
        sdtEditText = findViewById(R.id.sdt);
        radioGroup = findViewById(R.id.nhomNutLuaChon);

        // Retrieve data from intent
        Intent intent = getIntent();
        if (intent != null) {
            String bacSi = intent.getStringExtra("bacSi");
            String benhVien = intent.getStringExtra("benhVien");
            String chuyenKhoa = intent.getStringExtra("chuyenKhoa");

            // Display data in TextViews
            bsTextView.setText(bacSi);
            benhvienTextView.setText(benhVien);
            khamTextView.setText(chuyenKhoa);
        }

        // Set date for buttons
        setButtonDates();

        // Handle Xác nhận button click
        Button sacNhanButton = findViewById(R.id.sacnhan);
        sacNhanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAppointmentData();
            }
        });
    }

    private void setButtonDates() {
        // Get current date
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", new Locale("vi", "VN"));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM", Locale.getDefault());

        // Set date for btnThuBa
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        String day1 = dayFormat.format(calendar.getTime());
        String date1 = dateFormat.format(calendar.getTime());
        btnThuBa.setText(String.format("%s \n %s", day1, date1));

        // Set date for btnThuTu
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        String day2 = dayFormat.format(calendar.getTime());
        String date2 = dateFormat.format(calendar.getTime());
        btnThuTu.setText(String.format("%s \n %s", day2, date2));
    }

    private void saveAppointmentData() {
        String uid = mAuth.getCurrentUser().getUid();
        String bacSi = bsTextView.getText().toString();
        String benhVien = benhvienTextView.getText().toString();
        String chuyenKhoa = khamTextView.getText().toString();
        String ten = tenEditText.getText().toString().trim();
        String sdt = sdtEditText.getText().toString().trim();

        // Validate inputs
        if (ten.isEmpty()) {
            tenEditText.setError("Nhập tên của bạn");
            tenEditText.requestFocus();
            return;
        }
        if (sdt.isEmpty()) {
            sdtEditText.setError("Nhập số điện thoại của bạn");
            sdtEditText.requestFocus();
            return;
        }

        // Get selected radio button text
        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
        String selectedTime = selectedRadioButton.getText().toString();

        // Create a Map to store data
        Map<String, Object> appointmentData = new HashMap<>();
        appointmentData.put("uid", uid);
        appointmentData.put("bacSi", bacSi);
        appointmentData.put("benhVien", benhVien);
        appointmentData.put("chuyenKhoa", chuyenKhoa);
        appointmentData.put("ten", ten);
        appointmentData.put("sdt", sdt);
        appointmentData.put("thoiGian", selectedTime);

        // Add data to Firestore
        fStore.collection("Appointments")
                .add(appointmentData)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(chon_thoi_gian_kham.this, "Đặt lịch thành công", Toast.LENGTH_SHORT).show();
                    // Redirect or finish activity as needed
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(chon_thoi_gian_kham.this, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
