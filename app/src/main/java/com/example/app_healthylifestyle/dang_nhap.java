package com.example.app_healthylifestyle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class dang_nhap extends AppCompatActivity {
    Button dn_bnt;
    EditText dn_email, dn_pass;
    TextView signup;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    private FirebaseFirestore fstore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        // Assign UI elements
        dn_bnt = findViewById(R.id.dn_bnt);
        dn_email = findViewById(R.id.dn_email);
        dn_pass = findViewById(R.id.dn_pass);
        signup = findViewById(R.id.signup);
        progressBar = findViewById(R.id.progressbar_dn);


        dn_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = dn_email.getText().toString();
                String pass = dn_pass.getText().toString();
                progressBar.setVisibility(View.VISIBLE);

                // Validate inputs
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(dang_nhap.this, "Nhập email!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(dang_nhap.this, "Nhập mật khẩu!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                mAuth.signInWithEmailAndPassword(email, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(dang_nhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        checkUser(authResult.getUser().getUid());
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(dang_nhap.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
                    }
                });
                // Sign in with Firebase Auth
//                mAuth.signInWithEmailAndPassword(email, pass)
//                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                progressBar.setVisibility(View.GONE);
//                                if (task.isSuccessful()) {
//                                    Toast.makeText(dang_nhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
//                                    checkUser(task.getResult().getUser().getUid());
//                                    finish();
//                                } else {
//                                    Log.e("Dang_nhap", "Đăng nhập thất bại!", task.getException());
//                                    Toast.makeText(dang_nhap.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dang_nhap.this, dang_ky.class);
                startActivity(intent);
            }
        });
    }

    private void checkUser(String uid) {
        DocumentReference df = fstore.collection("Users").document(uid);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG","Success"+ documentSnapshot.getData());
                //admin
                if (documentSnapshot.getString("isAdmin") != null){
                    Intent intent = new Intent(dang_nhap.this, Admin.class);
                    startActivity(intent);
                }
                //user
                if (documentSnapshot.getString("isUser") != null){
                    Intent intent = new Intent(dang_nhap.this, home.class);
                    startActivity(intent);
                }

            }
        });
    }
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            checkUser(currentUser.getUid());

//            Intent intent = new Intent(getApplicationContext(), home.class);
//            startActivity(intent);
//            finish();
        }
    }
}
