package com.fennec.allojib.controller;

import android.content.Intent;
import android.os.Bundle;

import com.fennec.allojib.adapter.CategoryPlatAdapter;
import com.fennec.allojib.adapter.ExCategoryPlatAdapter;
import com.fennec.allojib.adapter.ExCategoryProductAdapter;
import com.fennec.allojib.entity.CategoryPlat;
import com.fennec.allojib.entity.CategoryProduct;
import com.fennec.allojib.repository.CategoryPlatRepository;
import com.fennec.allojib.repository.CategoryProductRepository;
import com.fennec.allojib.repository.PlatRepository;
import com.fennec.allojib.repository.ProductRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.fennec.allojib.R;

import java.util.ArrayList;

public class CategoryProduct_Activity extends AppCompatActivity {

    public static RecyclerView recyclerView;
    public static CategoryPlatAdapter categoryProductAdapter;

    public static CategoryProduct_Activity main;

    public int id_mark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_product);
        main = this;


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Vos Produits");
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        id_mark = extras.getInt("id_mark");


        /*** traitement get our donnees form server **/

        for (int i = 0; i < CategoryProductRepository.list_categoryProduct.size(); i++)
        {
            CategoryProductRepository.list_categoryProduct.get(i).productList = new ArrayList<>();

            for (int j = 0; j < ProductRepository.list_product.size(); j++)
            {
                if( CategoryProductRepository.list_categoryProduct.get(i).id == ProductRepository.list_product.get(j).id_cat )
                {
                    if( ProductRepository.list_product.get(j).id_mark == id_mark )
                    {
                        CategoryProductRepository.list_categoryProduct.get(i).productList.add(ProductRepository.list_product.get(j));
                        Log.d("TAG_JSON", "------------> nothing ");
                    }
                }
            }
        }


        ArrayList<CategoryProduct> current_categoryProduct = new ArrayList<>();

        boolean exist = false;

        for (int i = 0; i < CategoryProductRepository.list_categoryProduct.size(); i++)
        {
            for (int j = 0; j < CategoryProductRepository.list_categoryProduct.get(i).productList.size(); j++)
            {
                if( CategoryProductRepository.list_categoryProduct.get(i).productList.get(j).id_mark == id_mark )
                {
                    exist = true;
                }
            }

            if(exist)
            {
                current_categoryProduct.add(CategoryProductRepository.list_categoryProduct.get(i));
                exist = false;
            }
        }

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ExCategoryProductAdapter adapter = new ExCategoryProductAdapter(main, current_categoryProduct);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(main));

        /***********************************************************************/

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(main, Order_Product_Activity.class);
                startActivity(intent);
            }
        });
    }

    public static void OnclickAdd(String msg)
    {
        Toast.makeText(main,msg, Toast.LENGTH_SHORT).show();
    }
}
