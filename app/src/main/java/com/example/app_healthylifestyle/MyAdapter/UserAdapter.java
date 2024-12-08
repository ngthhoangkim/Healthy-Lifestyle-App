package com.example.app_healthylifestyle.MyAdapter;

import static androidx.fragment.app.FragmentManager.TAG;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_healthylifestyle.CRUD.List_user;
import com.example.app_healthylifestyle.CRUD.Update;
import com.example.app_healthylifestyle.R;
import com.example.app_healthylifestyle.Tra_cuu_benh_nhan;
import com.example.app_healthylifestyle.database.Users;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<ViewHolder> {
    List<Users> usersList;
    List_user listUser;

    public UserAdapter(List_user listUser, List<Users> usersList) {
        this.usersList = usersList;
        this.listUser = listUser;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //lấy layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_user_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        // khi click
        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //show data ra
                String name = usersList.get(position).getName();
                String email = usersList.get(position).getEmail();
                String date = usersList.get(position).getDate();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                //gọi khi người dùng click
                AlertDialog.Builder builder = new AlertDialog.Builder(listUser);
                String[] options = {"Cập nhật", "Xóa"};
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //update
                        if (which == 0) {
                            //get data
                            String name = usersList.get(position).getName();
                            String email = usersList.get(position).getEmail();
                            String date = usersList.get(position).getDate();
                            String gender = usersList.get(position).getGender();
                            String address = usersList.get(position).getAddress();
                            String phone = usersList.get(position).getPhone();

                            Intent intent = new Intent(listUser, Tra_cuu_benh_nhan.class);
                            //put data
                            intent.putExtra("pName", name);
                            intent.putExtra("pEmail", email);
                            intent.putExtra("pDate", date);
                            intent.putExtra("pPhone", phone);
                            intent.putExtra("pAddress", address);
                            intent.putExtra("pGender", gender);

                            listUser.startActivity(intent);
                        }
                        //delete
                        if (which == 1) {
                            new AlertDialog.Builder(viewHolder.itemView.getContext())
                                    .setTitle("Confirmation")
                                    .setMessage("Bạn muốn xóa nó?")
                                    .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            listUser.deletData(position);
                                        }
                                    }).setNegativeButton("Không", null).show();
                        }
                    }
                }).create().show();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //set data
        holder.txt_Name.setText(usersList.get(position).getName());
        holder.txt_Email.setText(usersList.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }
}
