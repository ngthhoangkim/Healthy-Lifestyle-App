package com.example.app_healthylifestyle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app_healthylifestyle.database.lich_kham;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnFailureListener;


public class them_lich_kham extends AppCompatActivity {

    private EditText editTextTenBacSi;
    private EditText editTextTenBenhVien;
    private EditText editTextChuyenKhoa;
    private Button buttonXacNhan;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_lich_kham);

        editTextTenBacSi = findViewById(R.id.editTextText);
        editTextTenBenhVien = findViewById(R.id.editTextText2);
        editTextChuyenKhoa = findViewById(R.id.editTextText3);
        buttonXacNhan = findViewById(R.id.button3);

        databaseReference = FirebaseDatabase.getInstance().getReference("lich_kham");

        buttonXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadData();
            }
        });
    }

    private void uploadData() {
        String tenBacSi = editTextTenBacSi.getText().toString().trim();
        String tenBenhVien = editTextTenBenhVien.getText().toString().trim();
        String chuyenKhoa = editTextChuyenKhoa.getText().toString().trim();

        // Kiểm tra xem các trường thông tin có rỗng không
        if (tenBacSi.isEmpty() || tenBenhVien.isEmpty() || chuyenKhoa.isEmpty()) {
            Toast.makeText(them_lich_kham.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        // Tạo đối tượng lich_kham mới
        String uploadId = databaseReference.push().getKey();
        lich_kham lichKham = new lich_kham(tenBacSi, tenBenhVien, chuyenKhoa);

        // Lưu đối tượng lich_kham lên Firebase Database
        if (uploadId != null) {
            databaseReference.child(uploadId).setValue(lichKham)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(them_lich_kham.this, "Lưu thông tin thành công", Toast.LENGTH_SHORT).show();
                            // Xử lý khi lưu thành công (nếu cần)
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(them_lich_kham.this, "Lỗi khi lưu thông tin: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(them_lich_kham.this, "Lỗi khi lưu thông tin", Toast.LENGTH_SHORT).show();
        }
    }
}
