package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DishesDetailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView name,price,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishes_detail);


        imageView = findViewById(R.id.dishes_detail_image);
        name = findViewById(R.id.dishes_detail_name);
        price = findViewById(R.id.dishes_detail_price);
        description = findViewById(R.id.dishes_detail_description);

        Bundle bundle = getIntent().getExtras();

        getSupportActionBar().setTitle(bundle.getString("name"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name.setText(bundle.getString("name"));
        price.setText(bundle.getString("price"));
        description.setText(bundle.getString("desc"));
        imageView.setImageResource(bundle.getInt("image"));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}