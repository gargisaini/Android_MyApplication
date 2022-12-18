package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class DishesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<DishesList> arrayList;

    String[] dishesNameArray = {"Biryaani","Butter Chicken","Chole Bhature","Paneer Masala","Pani Puri"};
    int[] dishesImageArray = {R.drawable.biryaani,R.drawable.butter_chicken,R.drawable.chole_bhature,R.drawable.paneer_masala,R.drawable.pani_puri};
    String[] dishesPriceArray = {"200","300","180","230","100"};
    String[] dishesDescriptionArray = {
            "Biryani is a spiced mix of meat and rice, traditionally cooked over an open fire in a leather pot. It is combined in different ways with a variety of components to create a number of highly tasty and unique flavor combinations.",
            "Butter chicken, traditionally known as murgh makhani,an Indian dish which is a type of curry made from chicken with a spiced tomato and butter (makhan) sauce. Its sauce is known for its rich texture. It is similar to chicken tikka masala, which uses a tomato paste.",
            "Chole bhature is a food dish popular in the Northern areas of the Indian subcontinent. It is a combination of chana masala and bhatura/puri, a fried bread made from maida. Although it is known as a typical Punjabi dish, there are varied claims around the origin of dish.",
            "Paneer Masala is easy to make dish of paneer where paneer cubes are simmered in an onion-tomato based spicy gravy. It can be paired with Indian bread or Jeera Rice.",
            "Pani puri is an immensely popular Indian street food of crispy, hollow, fried dough balls (puri) stuffed with boiled potatoes or steamed moong sprouts or boiled chickpeas or white peas curry (also known as ragda) together with spicy tangy water and a sweet chutney. Pani puri is a favorite chaat snack of many folks and ours too. "
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishes);
        getSupportActionBar().setTitle("Dishes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.dishes_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(DishesActivity.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        arrayList = new ArrayList<>();
        for(int i=0;i<dishesNameArray.length;i++){
            DishesList list = new DishesList();
            list.setName(dishesNameArray[i]);
            list.setPrice(dishesPriceArray[i]);
            list.setDescription(dishesDescriptionArray[i]);
            list.setImage(dishesImageArray[i]);
            arrayList.add(list);
        }
        DishesListAdapter adapter = new DishesListAdapter(DishesActivity.this,arrayList);
        recyclerView.setAdapter(adapter);

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id =item.getItemId();
        if(id==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}