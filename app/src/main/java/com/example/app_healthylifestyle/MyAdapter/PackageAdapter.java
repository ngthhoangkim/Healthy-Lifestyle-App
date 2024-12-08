package com.example.app_healthylifestyle.MyAdapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_healthylifestyle.CRUD.List_package;
import com.example.app_healthylifestyle.CRUD.Update;
import com.example.app_healthylifestyle.Quan_ly_goi_kham;
import com.example.app_healthylifestyle.R;
import com.example.app_healthylifestyle.database.Package;

import java.util.List;

public class PackageAdapter extends RecyclerView.Adapter<ViewHolderPackage> {
    List_package list_package;
    List<Package> packageList;
    Context context;
    public PackageAdapter(List_package list_package, List<Package> packageList) {
        this.list_package = list_package;
        this.packageList = packageList;
    }
    @NonNull
    @Override
    public ViewHolderPackage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //lấy layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_package_item, parent, false);
        ViewHolderPackage viewHolderPackage = new ViewHolderPackage(view);

        viewHolderPackage.setOnClickListener(new ViewHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //show data
                String Name = packageList.get(position).getName();
                String Price = packageList.get(position).getPrice();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                //gọi khi người dùng click
                AlertDialog.Builder builder = new AlertDialog.Builder(list_package);
                String[] options = {"Cập nhật","Xóa"};
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0){
                            String Name = packageList.get(position).getName();
                            String ID = packageList.get(position).getId();
                            String Describe = packageList.get(position).getDescribe();
                            String Index = packageList.get(position).getIndex();
                            String Price = packageList.get(position).getPrice();

                            Intent intent = new Intent(list_package, Quan_ly_goi_kham.class);
                            //put data
                            intent.putExtra("pID",ID);
                            intent.putExtra("pName",Name);
                            intent.putExtra("pDescribe",Describe);
                            intent.putExtra("pIndex",Index);
                            intent.putExtra("pPrice",Price);

                            list_package.startActivity(intent);
                        }
                        if (which == 1){
                            new AlertDialog.Builder(viewHolderPackage.itemView.getContext())
                                    .setTitle("Confirmation")
                                    .setMessage("Bạn muốn xóa nó?")
                                    .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            list_package.deletData(position);
                                        }
                                    }).setNegativeButton("Không",null).show();
                        }
                    }
                }).create().show();
            }
        });

        return viewHolderPackage;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPackage holder, int position) {
        holder.txt_Name.setText(packageList.get(position).getName());
        holder.txt_Price.setText(packageList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return packageList.size();
    }
}
