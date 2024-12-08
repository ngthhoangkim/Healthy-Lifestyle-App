package com.example.app_healthylifestyle.AdapterUser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_healthylifestyle.R;
import com.example.app_healthylifestyle.database.lich_kham;

import java.util.List;

public class LichKhamAdapter extends RecyclerView.Adapter<LichKhamAdapter.ViewHolder> {

    private Context context;
    private List<lich_kham> lichKhamList;

    public LichKhamAdapter(Context context, List<lich_kham> lichKhamList) {
        this.context = context;
        this.lichKhamList = lichKhamList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_quan_li_lich_kham, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        lich_kham lichKham = lichKhamList.get(position);

        // Set data to views
        holder.maDonHangTextView.setText(lichKham.getTenBacSi());
        holder.tinhTrangBenhVienTextView.setText(lichKham.getTenBenhVien());
        holder.tinhTrangKhoaTextView.setText(lichKham.getChuyenKhoa());
    }

    @Override
    public int getItemCount() {
        return lichKhamList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView maDonHangTextView;
        TextView tinhTrangBenhVienTextView;
        TextView tinhTrangKhoaTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            maDonHangTextView = itemView.findViewById(R.id.maDonHang);
            tinhTrangBenhVienTextView = itemView.findViewById(R.id.tinhTrangBenhVien);
            tinhTrangKhoaTextView = itemView.findViewById(R.id.tinhTrangKhoa);
        }
    }
}
