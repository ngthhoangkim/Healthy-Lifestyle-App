package com.example.app_healthylifestyle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class dang_ky extends AppCompatActivity {
    Button bnt_dangky;
    TextView sign_in;
    private FirebaseAuth mAuth;
    private FirebaseFirestore fstore;
    EditText ed_pass,ed_email,ed_confirm;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        bnt_dangky = findViewById(R.id.dk_bnt);
        ed_email = findViewById(R.id.dk_email);
        ed_pass = findViewById(R.id.dk_pass);
        ed_confirm = findViewById(R.id.dk_confirm);
        sign_in = findViewById(R.id.signin);
        progressBar = findViewById(R.id.progressbar_dk);

        bnt_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = ed_email.getText().toString();
                String pass = ed_pass.getText().toString();
                String confirmpass = ed_confirm.getText().toString();
                progressBar.setVisibility(View.VISIBLE);
                mAuth = FirebaseAuth.getInstance();
                fstore = FirebaseFirestore.getInstance();

                //Không cho để trống
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(dang_ky.this,"Hãy nhập email!",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                if (TextUtils.isEmpty(pass)){
                    Toast.makeText(dang_ky.this,"Hãy nhập mật khẩu!",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                if (TextUtils.isEmpty(confirmpass)){
                    Toast.makeText(dang_ky.this,"Hãy nhập lại mật khẩu!",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                if (!pass.equals(confirmpass)){
                    Toast.makeText(dang_ky.this,"Mật khẩu không giống nhau!",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        progressBar.setVisibility(View.GONE);
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(dang_ky.this, "Đăng ký thành công!",Toast.LENGTH_SHORT).show();
                        DocumentReference df = fstore.collection("Users").document(user.getUid());
                       //put data
                        Map<String, String> userInfo = new HashMap<>();
                        userInfo.put("Email",email);
                        //user:1 && admin:0
                        userInfo.put("isUser","1");
                        df.set(userInfo);
                        Intent intent = new Intent(getApplicationContext(), home.class);
                        startActivity(intent);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(dang_ky.this, "Đăng ký thất bại!",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dang_ky.this, dang_nhap.class);
                startActivity(intent);
            }
        });
    }
}