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
import com.fennec.allojib.adapter.CategoryProductAdapter;
import com.fennec.allojib.adapter.MarketAdapter;
import com.fennec.allojib.adapter.RestaurantAdapter;
import com.fennec.allojib.repository.CategoryPlatRepository;
import com.fennec.allojib.repository.CategoryProductRepository;
import com.fennec.allojib.repository.MarketRepository;
import com.fennec.allojib.repository.RestaurantRepository;

public class Market_Activity extends AppCompatActivity {

    public static Market_Activity main;

    public static RecyclerView recyclerView1,recyclerView2;
    public static CategoryProductAdapter categoryProductAdapter;
    public static MarketAdapter marketAdapter;

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
        setContentView(R.layout.activity_market);

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

        categoryProductAdapter = new CategoryProductAdapter(CategoryProductRepository.list_categoryProduct);
        recyclerView1.setAdapter(categoryProductAdapter);
    }

    public static void onLoadRestaurant()
    {
        /** adapter 2 **/
        recyclerView2 = (RecyclerView) main.findViewById(R.id.recyclerView2);
        LinearLayoutManager lm = new LinearLayoutManager(main, LinearLayoutManager.VERTICAL, false);
        recyclerView2.setLayoutManager(lm);

        marketAdapter = new MarketAdapter(MarketRepository.list_market);
        recyclerView2.setAdapter(marketAdapter);
    }

    public static void update_adapter2(int id)
    {
        if(id == 0)
        {
            marketAdapter = new MarketAdapter(MarketRepository.list_market);
            recyclerView2.setAdapter(marketAdapter);
        }else{
            marketAdapter = new MarketAdapter(MarketRepository.MarketByCategory(id));
            recyclerView2.setAdapter(marketAdapter);
        }

    }

    public static void update_adapter_search(String name)
    {
        marketAdapter = new MarketAdapter(MarketRepository.MarketByName(name));
        recyclerView2.setAdapter(marketAdapter);
    }

    public static void to_newIntent(int id_mark)
    {
        /************************************************************************* ici la modification ************************************/
        Intent intent = new Intent(main, CategoryProduct_Activity.class);
        intent.putExtra("id_mark",id_mark);
        main.startActivity(intent);
    }
}