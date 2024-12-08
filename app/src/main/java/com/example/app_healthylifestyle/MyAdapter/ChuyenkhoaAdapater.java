package com.example.app_healthylifestyle.MyAdapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_healthylifestyle.CRUD.List_Chuyen_khoa;
import com.example.app_healthylifestyle.Quan_ly_chuyen_khoa;
import com.example.app_healthylifestyle.Quan_ly_goi_kham;
import com.example.app_healthylifestyle.R;
import com.example.app_healthylifestyle.database.ChuyenKhoa;

import java.util.List;

public class ChuyenkhoaAdapater extends RecyclerView.Adapter<ViewHolderChuyenkhoa>{
    List<ChuyenKhoa> chuyenKhoaList;
    List_Chuyen_khoa list_chuyen_khoa;

    public ChuyenkhoaAdapater(List<ChuyenKhoa> chuyenKhoaList, List_Chuyen_khoa list_chuyen_khoa) {
        this.chuyenKhoaList = chuyenKhoaList;
        this.list_chuyen_khoa = list_chuyen_khoa;
    }

    public ChuyenkhoaAdapater() {
    }

    @NonNull
    @Override
    public ViewHolderChuyenkhoa onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //lấy layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_chuyenkhoa, parent, false);
        ViewHolderChuyenkhoa viewHolderChuyenkhoa = new ViewHolderChuyenkhoa(view);

        viewHolderChuyenkhoa.setOnClickListener(new ViewHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //show data
                String Name = chuyenKhoaList.get(position).getName();
            }
            @Override
            public void onItemLongClick(View view, int position) {
                //gọi khi người dùng click
                AlertDialog.Builder builder = new AlertDialog.Builder(list_chuyen_khoa);
                String[] options = {"Cập nhật","Xóa"};
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0){
                            String Name = chuyenKhoaList.get(position).getName();
                            String ID = chuyenKhoaList.get(position).getId();

                            Intent intent = new Intent(list_chuyen_khoa, Quan_ly_chuyen_khoa.class);
                            //put data
                            intent.putExtra("ckID",ID);
                            intent.putExtra("ckName",Name);

                            list_chuyen_khoa.startActivity(intent);
                        }
                        if (which == 1){
                            new AlertDialog.Builder(viewHolderChuyenkhoa.itemView.getContext())
                                    .setTitle("Confirmation")
                                    .setMessage("Bạn muốn xóa nó?")
                                    .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            list_chuyen_khoa.deletData(position);
                                        }
                                    }).setNegativeButton("Không",null).show();
                        }
                    }
                }).create().show();

            }
        });
        return viewHolderChuyenkhoa;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderChuyenkhoa holder, int position) {
        holder.txt_Name.setText(chuyenKhoaList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return chuyenKhoaList.size();
    }
}
