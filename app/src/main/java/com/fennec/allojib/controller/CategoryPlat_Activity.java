package com.fennec.allojib.controller;

import android.os.Bundle;

import com.fennec.allojib.adapter.CategoryPlatAdapter;
import com.fennec.allojib.lab.Ingredient;
import com.fennec.allojib.lab.Recipe;
import com.fennec.allojib.lab.RecipeAdapter;
import com.fennec.allojib.repository.CategoryPlatRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.fennec.allojib.R;

import java.util.Arrays;
import java.util.List;

public class CategoryPlat_Activity extends AppCompatActivity {

    public static RecyclerView recyclerView;
    public static CategoryPlatAdapter categoryPlatAdapter;

    public static CategoryPlat_Activity main;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plat);
        main = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /** adapter 1 **/
        /*recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager lm = new LinearLayoutManager(main, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lm);

        categoryPlatAdapter = new CategoryPlatAdapter(CategoryPlatRepository.list_categoryPlat);
        recyclerView.setAdapter(categoryPlatAdapter);*/

        /** adapter 2 **/

        Ingredient beef = new Ingredient("beef");
        Ingredient cheese = new Ingredient("cheese");
        Ingredient salsa = new Ingredient("salsa");
        Ingredient tortilla = new Ingredient("tortilla");

        Recipe taco = new Recipe("taco",Arrays.asList(beef, cheese, salsa, tortilla));
        Recipe quesadilla = new Recipe("quesadilla",Arrays.asList(cheese, tortilla));
        List<Recipe> recipes = Arrays.asList(taco, quesadilla);


        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecipeAdapter adapter = new RecipeAdapter(this, recipes);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
