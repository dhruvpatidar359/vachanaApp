package com.example.sarvajnanavachanagalu.adapter;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sarvajnanavachanagalu.R;

public class MyViewHolder extends RecyclerView.ViewHolder {


    TextView nameView;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        nameView = itemView.findViewById(R.id.vachan_title);

    }
}