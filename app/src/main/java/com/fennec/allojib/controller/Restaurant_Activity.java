package com.fennec.allojib.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        main = this;

        RestaurantRepository.list_restaurant.clear();
        PlatRepository.list_plat.clear();
        CategoryPlatRepository.list_categoryPlat.clear();

        String url_informations = constant.url_host+"/json/getTable.php?table=";
        JsonUrlPlat jsonUrlPlat = new JsonUrlPlat(url_informations+"tbl_plat", main);
        JsonUrlRestaurant jsonRestaurant = new JsonUrlRestaurant(url_informations+"tbl_retaurant", main);
        JsonUrlCategoryPlat jsonUrlCategoryPlat = new JsonUrlCategoryPlat(url_informations+"tbl_category_plat", main);

        progressBar3 = (ProgressBar) findViewById(R.id.progressBar3);
        progressBar3.setMax(100);
        progress = progressBar3.getProgress();

        new Thread(new Runnable()
        {
            public void run()
            {
                while (progress < 100)
                {
                    progress += 1;
                    handler.post(new Runnable()
                    {
                        public void run()
                        {
                            progressBar3.setProgress(progress);
                        }
                    });

                    try
                    {
                        Thread.sleep(50);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                // Progress finished, re-enter UI thread and set text
                handler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        progressBar3.setProgress(100);
                        progressBar3.setVisibility(progressBar3.GONE);

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
                });
            }
        }).start();
    }

    public static void update_adapter2(int id)
    {
        restaurantAdapter = new RestaurantAdapter(RestaurantRepository.RestaurantByCategory(id));
        recyclerView2.setAdapter(restaurantAdapter);
    }

    public static void to_newIntent(int id_rest)
    {
        Intent intent = new Intent(main, CategoryPlat_Activity.class);
        intent.putExtra("id_rest",id_rest);
        main.startActivity(intent);
    }
}
