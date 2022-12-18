package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    TextView title;
    SharedPreferences sp;

    RecyclerView recyclerView,dishesRecyclerView;



    String[] categoryNameArray = {"Indian","Chinese","Italian","Japanese"};
    int[] categoryImageArray = {R.drawable.south_indian,R.drawable.chinese,R.drawable.italian,R.drawable.asian};

    ArrayList<CategoryList> arrayList;

    String[] dishesNameArray = {"Biryaani","Butter Chicken","Chole Bhature","Paneer Masala","Pani Puri"};
    int[] dishesImageArray = {R.drawable.biryaani,R.drawable.butter_chicken,R.drawable.chole_bhature,R.drawable.paneer_masala,R.drawable.pani_puri};
    String[] dishesPriceArray = {"200","300","180","230","100"};
    String[] dishesDescriptionArray = {
            "Biryani is a spiced mix of0 meat and rice, traditionally cooked over an open fire in a leather pot. It is combined in different ways with a variety of components to create a number of highly tasty and unique flavor combinations.",
            "Butter chicken, traditionally known as murgh makhani,an Indian dish which is a type of curry made from chicken with a spiced tomato and butter (makhan) sauce. Its sauce is known for its rich texture. It is similar to chicken tikka masala, which uses a tomato paste.",
            "Chole bhature is a food dish popular in the Northern areas of the Indian subcontinent. It is a combination of chana masala and bhatura/puri, a fried bread made from maida. Although it is known as a typical Punjabi dish, there are varied claims around the origin of dish.",
            "Paneer Masala is easy to make dish of paneer where paneer cubes are simmered in an onion-tomato based spicy gravy. It can be paired with Indian bread or Jeera Rice.",
            "Pani puri is an immensely popular Indian street food of crispy, hollow, fried dough balls (puri) stuffed with boiled potatoes or steamed moong sprouts or boiled chickpeas or white peas curry (also known as ragda) together with spicy tangy water and a sweet chutney. Pani puri is a favorite chaat snack of many folks and ours too. "
            };

    ArrayList<DishesList> dishesArrayList;

    TextView viewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setTitle("Dashboard");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sp = getSharedPreferences(ConstantSp.PREF,MODE_PRIVATE);
        title = findViewById(R.id.home_title);


        title.setText("Welcome To\n"+sp.getString(ConstantSp.EMAIL,""));

        viewAll = findViewById(R.id.home_view_all);
        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               new CommonMethod(HomeActivity.this,DishesActivity.class);
            }
        });

        recyclerView = findViewById(R.id.home_recyclerview);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);

        arrayList = new ArrayList<>();
        for(int i=0;i<categoryNameArray.length;i++)
        {
            CategoryList list = new CategoryList();
            list.setName(categoryNameArray[i]);
            list.setImage(categoryImageArray[i]);
            arrayList.add(list);
        }

//        CategoryAdapter adapter = new CategoryAdapter(HomeActivity.this,categoryNameArray,categoryImageArray);
        CategoryAdapter adapter = new CategoryAdapter(HomeActivity.this,arrayList);
        recyclerView.setAdapter(adapter);

        dishesRecyclerView = findViewById(R.id.home_dishes_recyclerview);
        dishesRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
        dishesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        dishesRecyclerView.setNestedScrollingEnabled(false);

        dishesArrayList = new ArrayList<>();
        for(int i=0;i<dishesNameArray.length;i++){
            DishesList list = new DishesList();
            list.setName(dishesNameArray[i]);
            list.setPrice(dishesPriceArray[i]);
            list.setDescription(dishesDescriptionArray[i]);
            list.setImage(dishesImageArray[i]);
            dishesArrayList.add(list);
        }
        DishesAdapter dishesAdapter = new DishesAdapter(HomeActivity.this,dishesArrayList);
        dishesRecyclerView.setAdapter(dishesAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home)
        {
            onBackPressed();
        }
        if(id==R.id.home_menu_logout){
        sp.edit().clear().commit();
        new CommonMethod(HomeActivity.this,MainActivity.class);
        }
        if(id==R.id.home_menu_chat){
            new CommonMethod(HomeActivity.this,ChatActivity.class);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
       // super.onBackPressed();
        //finish();
//        finishAffinity();

        alertMethod();
    }
//  For Alert Box
    private void alertMethod()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("Exit Alert!");
        builder.setMessage("Are You Sure You Want To Exit!");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setNeutralButton("Rate Us", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                new CommonMethod(HomeActivity.this,"Rate Us");
            }
        });
        builder.setCancelable(false);
        builder.show();
    }
}