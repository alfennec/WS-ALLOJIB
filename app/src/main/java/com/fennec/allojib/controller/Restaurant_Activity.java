package com.fennec.allojib.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.fennec.allojib.R;
import com.fennec.allojib.adapter.CategoryPlatAdapter;
import com.fennec.allojib.adapter.RestaurantAdapter;
import com.fennec.allojib.config.JsonUrlCategoryPlat;
import com.fennec.allojib.config.JsonUrlPlat;
import com.fennec.allojib.config.JsonUrlRestaurant;
import com.fennec.allojib.config.constant;
import com.fennec.allojib.entity.CategoryPlat;
import com.fennec.allojib.repository.CategoryPlatRepository;
import com.fennec.allojib.repository.PlatRepository;
import com.fennec.allojib.repository.RestaurantRepository;

public class Restaurant_Activity extends AppCompatActivity {

    public static Restaurant_Activity main;

    public static RecyclerView recyclerView1,recyclerView2;
    public static CategoryPlatAdapter categoryPlatAdapter;
    public static RestaurantAdapter restaurantAdapter;

    public Handler handler = new Handler();
    public int progress = 0;

    public ProgressBar progressBar3;

    public ImageButton btn_search;
    public EditText editText_restaurant;

    public static ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        main = this;

        editText_restaurant = (EditText) findViewById(R.id.editText_restaurant);
        btn_search = (ImageButton) findViewById(R.id.btn_search);


        btn_search.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                update_adapter_search(editText_restaurant.getText().toString());
            }
        });

        onLoadCategory();
        onLoadRestaurant();
    }

    public static void onLoadCategory()
    {
        /** adapter 1 **/
        recyclerView1 = (RecyclerView) main.findViewById(R.id.recyclerView1);
        LinearLayoutManager lm = new LinearLayoutManager(main, LinearLayoutManager.HORIZONTAL, false);
        recyclerView1.setLayoutManager(lm);

        categoryPlatAdapter = new CategoryPlatAdapter(CategoryPlatRepository.list_categoryPlat);
        recyclerView1.setAdapter(categoryPlatAdapter);
    }

    public static void onLoadRestaurant()
    {
        /** adapter 2 **/
        recyclerView2 = (RecyclerView) main.findViewById(R.id.recyclerView2);
        LinearLayoutManager lm = new LinearLayoutManager(main, LinearLayoutManager.VERTICAL, false);
        recyclerView2.setLayoutManager(lm);

        restaurantAdapter = new RestaurantAdapter(RestaurantRepository.list_restaurant);
        recyclerView2.setAdapter(restaurantAdapter);
    }

    public static void update_adapter2(int id)
    {
        if(id == 0)
        {
            restaurantAdapter = new RestaurantAdapter(RestaurantRepository.list_restaurant);
            recyclerView2.setAdapter(restaurantAdapter);
        }else{
            restaurantAdapter = new RestaurantAdapter(RestaurantRepository.RestaurantByCategory(id));
            recyclerView2.setAdapter(restaurantAdapter);
        }

    }

    public static void update_adapter_search(String name)
    {
        restaurantAdapter = new RestaurantAdapter(RestaurantRepository.RestaurantByName(name));
        recyclerView2.setAdapter(restaurantAdapter);
    }

    public static void to_newIntent(int id_rest)
    {
        Intent intent = new Intent(main, CategoryPlat_Activity.class);
        intent.putExtra("id_rest",id_rest);
        main.startActivity(intent);
    }
}
