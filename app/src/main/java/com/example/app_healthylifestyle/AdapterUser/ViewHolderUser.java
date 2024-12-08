package com.example.app_healthylifestyle.AdapterUser;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_healthylifestyle.MyAdapter.ViewHolder;
import com.example.app_healthylifestyle.R;

public class ViewHolderUser extends RecyclerView.ViewHolder {
    TextView uName,uPrice;
    View mView;
    public ViewHolderUser(@NonNull View itemView) {
        super(itemView);
        mView = itemView;

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
        uName = itemView.findViewById(R.id.item_name_package);
        uPrice = itemView.findViewById(R.id.item_price);
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
