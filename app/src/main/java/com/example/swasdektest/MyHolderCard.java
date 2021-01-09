package com.example.swasdektest;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyHolderCard extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView title,desc;

    public MyHolderCard(@NonNull View itemView) {
        super(itemView);

        this.imageView = itemView.findViewById(R.id.imageHeart);
        this.title = itemView.findViewById(R.id.titleHeart);
        this.desc= itemView.findViewById(R.id.descHeart);
    }
}

