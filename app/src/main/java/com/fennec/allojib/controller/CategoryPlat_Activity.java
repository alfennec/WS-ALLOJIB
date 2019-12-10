package com.fennec.allojib.controller;

import android.content.Intent;
import android.os.Bundle;

import com.fennec.allojib.adapter.CategoryPlatAdapter;
import com.fennec.allojib.adapter.ExCategoryPlatAdapter;
import com.fennec.allojib.config.JsonUrlCategoryPlat;
import com.fennec.allojib.config.JsonUrlPlat;
import com.fennec.allojib.config.JsonUrlRestaurant;
import com.fennec.allojib.config.constant;
import com.fennec.allojib.entity.CategoryPlat;
import com.fennec.allojib.entity.Plat;
import com.fennec.allojib.lab.Ingredient;
import com.fennec.allojib.lab.Recipe;
import com.fennec.allojib.lab.RecipeAdapter;
import com.fennec.allojib.repository.CategoryPlatRepository;
import com.fennec.allojib.repository.PlatRepository;
import com.fennec.allojib.repository.RestaurantRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.fennec.allojib.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategoryPlat_Activity extends AppCompatActivity {

    public static RecyclerView recyclerView;
    public static CategoryPlatAdapter categoryPlatAdapter;

    public static CategoryPlat_Activity main;

    public int id_rest;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plat);
        main = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Choisissez vos plats");
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        id_rest = extras.getInt("id_rest");

        /*** traitement get our donnees form server **/

        for (int i = 0; i < CategoryPlatRepository.list_categoryPlat.size(); i++)
        {
            CategoryPlatRepository.list_categoryPlat.get(i).platList = new ArrayList<>();

            for (int j = 0; j < PlatRepository.list_plat.size(); j++)
            {
                if( CategoryPlatRepository.list_categoryPlat.get(i).id == PlatRepository.list_plat.get(j).id_cat )
                {
                    if( PlatRepository.list_plat.get(j).id_rest == id_rest )
                    {
                        CategoryPlatRepository.list_categoryPlat.get(i).platList.add(PlatRepository.list_plat.get(j));
                    }
                }
            }
        }

        ArrayList<CategoryPlat> current_categoryPlat = new ArrayList<>();
        boolean exist = false;

        for (int i = 0; i < CategoryPlatRepository.list_categoryPlat.size(); i++)
        {
            for (int j = 0; j < CategoryPlatRepository.list_categoryPlat.get(i).platList.size(); j++)
            {
                if( CategoryPlatRepository.list_categoryPlat.get(i).platList.get(j).id_rest == id_rest )
                {
                    exist = true;
                }
            }

            if(exist)
            {
                current_categoryPlat.add(CategoryPlatRepository.list_categoryPlat.get(i));
                exist = false;
            }
        }

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ExCategoryPlatAdapter adapter = new ExCategoryPlatAdapter(main, current_categoryPlat);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(main));

        /***********************************************************************/
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(main, Order_Plat_Activity.class);
                startActivity(intent);
            }
        });
    }

    public static void OnclickAdd(String msg)
    {
        Toast.makeText(main,msg, Toast.LENGTH_SHORT).show();
    }
}
