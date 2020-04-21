package com.example.lab1;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChickenItem extends RecyclerView.ViewHolder{
    TextView text_num;
    TextView text_full_num;
    public ChickenItem(@NonNull View itemView) {
        super(itemView);
        text_num = itemView.findViewById(R.id.item_number);
        text_full_num = itemView.findViewById(R.id.full_num);
    }
}
