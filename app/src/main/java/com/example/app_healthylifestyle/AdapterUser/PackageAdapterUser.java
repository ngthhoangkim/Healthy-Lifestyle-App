package com.example.app_healthylifestyle.AdapterUser;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_healthylifestyle.CRUD.List_package;
import com.example.app_healthylifestyle.MyAdapter.ViewHolder;
import com.example.app_healthylifestyle.MyAdapter.ViewHolderPackage;
import com.example.app_healthylifestyle.R;
import com.example.app_healthylifestyle.database.Package;
import com.example.app_healthylifestyle.goi_kham;

import java.util.List;

public class PackageAdapterUser extends RecyclerView.Adapter<ViewHolderUser>{
    goi_kham uList_package;
    List<Package> uPackageList;

    public PackageAdapterUser() {
    }
    public PackageAdapterUser(goi_kham uList_package, List<Package> uPackageList) {
        this.uList_package = uList_package;
        this.uPackageList = uPackageList;
    }

    @NonNull
    @Override
    public ViewHolderUser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //lay layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_package_item, parent, false);
        ViewHolderUser viewHolderPackage_u = new ViewHolderUser(view);

        viewHolderPackage_u.setOnClickListener(new ViewHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //show data
                String Name = uPackageList.get(position).getName();
                String Price = uPackageList.get(position).getPrice();
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

        return viewHolderPackage_u;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderUser holder, int position) {
        holder.uName.setText(uPackageList.get(position).getName());
        holder.uPrice.setText(uPackageList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return uPackageList.size();
    }
}
