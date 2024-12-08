package com.example.app_healthylifestyle.MyAdapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_healthylifestyle.R;

public class ViewHolderPackage extends RecyclerView.ViewHolder {
    TextView txt_Name,txt_Price;
    View mView;
    public ViewHolderPackage(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
        //item click
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemLongClick(v, getAdapterPosition());

            }
        });
        //item long click
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

        txt_Name = itemView.findViewById(R.id.item_name_package);
        txt_Price = itemView.findViewById(R.id.item_price);
    }
    private ViewHolder.ClickListener mClickListener;
    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
    public void setOnClickListener(ViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }
}
