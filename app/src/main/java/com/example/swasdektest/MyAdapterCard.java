package com.example.swasdektest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterCard extends RecyclerView.Adapter<MyHolderCard> {

    Context context;
    ArrayList<ModelCard> models;

    public MyAdapterCard(Context context, ArrayList<ModelCard> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public MyHolderCard onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,null);
        return new MyHolderCard(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolderCard holder, int position) {

    holder.title.setText(models.get(position).getTitle());
    holder.desc.setText(models.get(position).getDescription());
    holder.imageView.setImageResource(models.get(position).getImg());

    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
