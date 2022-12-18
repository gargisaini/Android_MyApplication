package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryAdapter  extends RecyclerView.Adapter<CategoryAdapter.MyHolder> {
    Context context;
    /*String[] categoryNameArray;
    int[] categoryImageArray;*/
    ArrayList<CategoryList>arrayList;

    public CategoryAdapter(Context homeActivity, ArrayList<CategoryList> arrayList) {
        this.context =homeActivity;
        this.arrayList = arrayList;
    }

   /* public CategoryAdapter(Context homeActivity, String[] categoryNameArray, int[] categoryImageArray) {
        context = homeActivity;
        this.categoryNameArray = categoryNameArray;
        this.categoryImageArray = categoryImageArray;
    }*/

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_category,parent,false);
        return new MyHolder(view);
    }

    public class MyHolder  extends  RecyclerView.ViewHolder{

        ImageView imageView;
        TextView name;

        public MyHolder(@NonNull View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.custom_category_image);
            name = itemView.findViewById(R.id.custom_category_name);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.imageView.setImageResource(arrayList.get(position).getImage());
        holder.name.setText(arrayList.get(position).getName());
    }

    @Override
    public int getItemCount() {

        return arrayList.size();
    }


}
