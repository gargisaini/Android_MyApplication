package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DishesAdapter extends RecyclerView.Adapter<DishesAdapter.MyHolder> {

    Context context;
    ArrayList<DishesList> arrayList;

    public DishesAdapter(Context homeActivity, ArrayList<DishesList> dishesArrayList) {
        this.context = homeActivity;
        this.arrayList = dishesArrayList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_dishes,parent,false);
        return new MyHolder(view);
    }

    public class MyHolder  extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView name,price;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.custom_dishes_image);
            name= itemView.findViewById(R.id.custom_dishes_name);
            price =itemView.findViewById(R.id.custom_dishes_price);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder,int position) {
        holder.imageView.setImageResource(arrayList.get(position).getImage());
        holder.name.setText(arrayList.get(position).getName());
        holder.price.setText(context.getResources().getString(R.string.price_symbol)+arrayList.get(position).getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DishesDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name",arrayList.get(position).getName());
                bundle.putString("price",context.getResources().getString(R.string.price_symbol)+arrayList.get(position).getPrice());
                bundle.putString("desc",arrayList.get(position).getDescription());
                bundle.putInt("image",arrayList.get(position).getImage());
                intent.putExtras(bundle);

                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
