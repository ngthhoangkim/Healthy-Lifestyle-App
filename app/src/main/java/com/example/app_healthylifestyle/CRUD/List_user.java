package com.example.app_healthylifestyle.CRUD;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.app_healthylifestyle.MyAdapter.PackageAdapter;
import com.example.app_healthylifestyle.MyAdapter.UserAdapter;
import com.example.app_healthylifestyle.R;
import com.example.app_healthylifestyle.database.Package;
import com.example.app_healthylifestyle.database.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;

public class List_user extends AppCompatActivity {
    List<Users> usersList = new ArrayList<>();
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseFirestore db;
    //adapter


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);

        mRecyclerView = findViewById(R.id.uRecycler_view);
        db = FirebaseFirestore.getInstance();
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        //show data
        showData();
        //update

    }

    private void showData() {
        db.collection("Users").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        usersList.clear();
                        //show
                        if (task.isSuccessful()){
                            for (DocumentSnapshot doc : task.getResult()){
                                Users list = new Users(
                                        doc.getString("Name"),
                                        doc.getString("Phone"),
                                        doc.getString("Email"),
                                        doc.getString("Date"),
                                        doc.getString("Gender"),
                                        doc.getString("Address"));
                                usersList.add(list);
                            }
                            UserAdapter uAdapter = new UserAdapter(List_user.this,usersList);
                            mRecyclerView.setAdapter(uAdapter);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(List_user.this,"Lỗi",Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void deletData(int index){
        db.collection("Users").document(usersList.get(index).getId())
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(List_user.this,"Đã xóa",Toast.LENGTH_SHORT).show();
                        showData();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
}