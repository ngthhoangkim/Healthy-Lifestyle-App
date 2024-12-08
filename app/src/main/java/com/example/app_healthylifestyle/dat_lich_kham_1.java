package com.example.app_healthylifestyle;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_healthylifestyle.database.lich_kham;

import java.util.List;

public class dat_lich_kham_1 extends RecyclerView.Adapter<dat_lich_kham_1.ViewHolder> {

    private Context context;
    private List<lich_kham> lichKhamList;

    public dat_lich_kham_1(Context context, List<lich_kham> lichKhamList) {
        this.context = context;
        this.lichKhamList = lichKhamList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dat_lich_kham, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final lich_kham lichKham = lichKhamList.get(position);

        // Set data to views
        holder.maDonHangTextView.setText(lichKham.getTenBacSi());
        holder.tinhTrangBenhVienTextView.setText(lichKham.getTenBenhVien());
        holder.tinhTrangKhoaTextView.setText(lichKham.getChuyenKhoa());

        // Handle item click
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pass data to chon_thoi_gian_kham activity
                Intent intent = new Intent(context, chon_thoi_gian_kham.class);
                intent.putExtra("bacSi", lichKham.getTenBacSi());
                intent.putExtra("benhVien", lichKham.getTenBenhVien());
                intent.putExtra("chuyenKhoa", lichKham.getChuyenKhoa());
                context.startActivity(intent);
            }
        });
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
