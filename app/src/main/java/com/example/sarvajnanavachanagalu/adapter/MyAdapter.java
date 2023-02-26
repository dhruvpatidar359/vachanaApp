package com.example.sarvajnanavachanagalu.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sarvajnanavachanagalu.R;
import com.example.sarvajnanavachanagalu.ReadActivity;
import com.example.sarvajnanavachanagalu.models.Vachanagalu;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {


    Context context;
    List<Vachanagalu> items;

    public MyAdapter(Context context, List<Vachanagalu> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.vachana_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull  MyViewHolder holder, int position) {
        String text = items.get(position).getVachana();
        holder.nameView.setText(Html.fromHtml(text));
        holder.itemView.setOnClickListener(view -> {

            Intent i = new Intent(context, ReadActivity.class);
            i.putExtra("data",text);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);


        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}