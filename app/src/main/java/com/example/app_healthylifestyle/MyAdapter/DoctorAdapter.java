package com.example.app_healthylifestyle.MyAdapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_healthylifestyle.CRUD.List_Doctor;
import com.example.app_healthylifestyle.CRUD.List_package;
import com.example.app_healthylifestyle.Quan_ly_bac_si;
import com.example.app_healthylifestyle.Quan_ly_chuyen_khoa;
import com.example.app_healthylifestyle.R;
import com.example.app_healthylifestyle.database.Doctor;
import com.example.app_healthylifestyle.database.Package;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<ViewHolderDoctor>{
    List_Doctor list_doctor;
    List<Doctor> doctorList;

    public DoctorAdapter(List_Doctor list_doctor, List<Doctor> doctorList) {
        this.list_doctor = list_doctor;
        this.doctorList = doctorList;
    }
    @NonNull
    @Override
    public ViewHolderDoctor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //lấy layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_doctor_item, parent, false);
        ViewHolderDoctor viewHolderDoctor = new ViewHolderDoctor(view);

        viewHolderDoctor.setOnClickListener(new ViewHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //show data
                String Name = doctorList.get(position).getName();
                String Chuyenkhoa = doctorList.get(position).getChuyenkhoa();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                //gọi khi người dùng click
                AlertDialog.Builder builder = new AlertDialog.Builder(list_doctor);
                String[] options = {"Cập nhật","Xóa"};
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0){
                            String Name = doctorList.get(position).getName();
                            String ID = doctorList.get(position).getId();
                            String Chuyenkhoa = doctorList.get(position).getChuyenkhoa();
                            String Kinhnghiem = doctorList.get(position).getKinhnghiem();
                            String Benhvien = doctorList.get(position).getBenhvien();
                            String Mota = doctorList.get(position).getChitiet();

                            Intent intent = new Intent(list_doctor, Quan_ly_bac_si.class);
                            //put data
                            intent.putExtra("IDbs",ID);
                            intent.putExtra("Namebs",Name);
                            intent.putExtra("Chuyenkhoabs",Chuyenkhoa);
                            intent.putExtra("Kinhnghiembs",Kinhnghiem);
                            intent.putExtra("Benhvienbs",Benhvien);
                            intent.putExtra("Motabs",Mota);

                            list_doctor.startActivity(intent);
                        }
                        if (which == 1){
                            new android.app.AlertDialog.Builder(viewHolderDoctor.itemView.getContext())
                                    .setTitle("Confirmation")
                                    .setMessage("Bạn muốn xóa nó?")
                                    .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            list_doctor.deletData(position);
                                        }
                                    }).setNegativeButton("Không",null).show();
                        }
                    }
                }).create().show();
            }
        });
        return viewHolderDoctor;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDoctor holder, int position) {
        holder.txt_Name.setText(doctorList.get(position).getName());
        holder.txt_Chuyenkhoa.setText(doctorList.get(position).getChuyenkhoa());
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }
}
