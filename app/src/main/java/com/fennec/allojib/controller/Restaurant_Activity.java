package com.fennec.allojib.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.fennec.allojib.R;
import com.fennec.allojib.adapter.CategoryPlatAdapter;
import com.fennec.allojib.adapter.RestaurantAdapter;
import com.fennec.allojib.repository.CategoryPlatRepository;
import com.fennec.allojib.repository.RestaurantRepository;

public class Restaurant_Activity extends AppCompatActivity {

    public static Restaurant_Activity main;

    public static RecyclerView recyclerView1,recyclerView2;
    public static CategoryPlatAdapter categoryPlatAdapter;
    public static RestaurantAdapter restaurantAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        main = this;

        /** adapter 1 **/
        recyclerView1 = (RecyclerView)findViewById(R.id.recyclerView1);
        LinearLayoutManager lm = new LinearLayoutManager(main, LinearLayoutManager.HORIZONTAL, false);
        recyclerView1.setLayoutManager(lm);

        categoryPlatAdapter = new CategoryPlatAdapter(CategoryPlatRepository.list_categoryPlat);
        recyclerView1.setAdapter(categoryPlatAdapter);

        /** adapter 2 **/
        recyclerView2 = (RecyclerView)findViewById(R.id.recyclerView2);
        lm = new LinearLayoutManager(main, LinearLayoutManager.VERTICAL, false);
        recyclerView2.setLayoutManager(lm);

        restaurantAdapter = new RestaurantAdapter(RestaurantRepository.list_restaurant);
        recyclerView2.setAdapter(restaurantAdapter);

    }

    public static void update_adapter2(int id)
    {
        restaurantAdapter = new RestaurantAdapter(RestaurantRepository.RestaurantByCategory(id));
        recyclerView2.setAdapter(restaurantAdapter);
    }

    public static void to_newIntent()
    {
        Intent intent = new Intent(main, CategoryPlat_Activity.class);
        main.startActivity(intent);
    }
}
