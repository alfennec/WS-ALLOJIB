package com.fennec.allojib.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.fennec.allojib.R;
import com.fennec.allojib.adapter.CategoryPlatAdapter;
import com.fennec.allojib.adapter.OrderPlatAdapter;
import com.fennec.allojib.repository.CategoryPlatRepository;
import com.fennec.allojib.repository.OrderPlatRepository;

public class Order_Plat_Activity extends AppCompatActivity {

    public static RecyclerView recyclerView;
    public static OrderPlatAdapter orderPlatAdapter;

    public static Order_Plat_Activity main;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_plat);
        main = this;

        /** adapter  **/
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager lm = new LinearLayoutManager(main, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lm);

        orderPlatAdapter = new OrderPlatAdapter(OrderPlatRepository.list_orderPlat);
        recyclerView.setAdapter(orderPlatAdapter);
    }

    public static void getNewAdpter()
    {
        /** adapter  **/

        orderPlatAdapter = new OrderPlatAdapter(OrderPlatRepository.list_orderPlat);
        recyclerView.setAdapter(orderPlatAdapter);
    }
}
