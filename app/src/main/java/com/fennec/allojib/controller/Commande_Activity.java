package com.fennec.allojib.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.fennec.allojib.R;
import com.fennec.allojib.adapter.CommandePlatAdapter;
import com.fennec.allojib.adapter.OrderPlatAdapter;
import com.fennec.allojib.entity.PassOrderPlat;
import com.fennec.allojib.repository.OrderPlatRepository;
import com.fennec.allojib.repository.PassOrderPlatRepository;

public class Commande_Activity extends AppCompatActivity {

    public static int id_PassOrder;
    public static Commande_Activity main;

    public TextView order_id,tv_total;

    public static RecyclerView recyclerView;
    public static CommandePlatAdapter commandePlatAdapter;

    public static ImageView iv_c1,iv_c2,iv_c3,iv_c4,iv_c5;
    public static TextView tv_c1,tv_c2,tv_c3,tv_c4,tv_c5;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commande);
        main = this;

        id_PassOrder = PassOrderPlatRepository.list_passOrderPlat.get(PassOrderPlatRepository.list_passOrderPlat.size()-1).id;

        order_id = (TextView) findViewById(R.id.order_id);
        tv_total = (TextView) findViewById(R.id.tv_total);

        order_id.setText("N°"+id_PassOrder);
        tv_total.setText("Total :"+PassOrderPlatRepository.list_passOrderPlat.get(PassOrderPlatRepository.list_passOrderPlat.size()-1).total+"MAD");


        /** adapter for test we have to improve our self for this app  **/
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager lm = new LinearLayoutManager(main, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lm);

        commandePlatAdapter = new CommandePlatAdapter(OrderPlatRepository.WithIdorder(id_PassOrder));
        recyclerView.setAdapter(commandePlatAdapter);
        /** adapter for test we have to improve our self for this end  **/


        iv_c1 = (ImageView) findViewById(R.id.iv_c1);
        iv_c2 = (ImageView) findViewById(R.id.iv_c2);
        iv_c3 = (ImageView) findViewById(R.id.iv_c3);
        iv_c4 = (ImageView) findViewById(R.id.iv_c4);
        iv_c5 = (ImageView) findViewById(R.id.iv_c5);

        tv_c1 = (TextView) findViewById(R.id.tv_c1);
        tv_c2 = (TextView) findViewById(R.id.tv_c2);
        tv_c3 = (TextView) findViewById(R.id.tv_c3);
        tv_c4 = (TextView) findViewById(R.id.tv_c4);
        tv_c5 = (TextView) findViewById(R.id.tv_c5);

        int situation = PassOrderPlatRepository.list_passOrderPlat.get(PassOrderPlatRepository.list_passOrderPlat.size()-1).situation;

        switch (situation)
        {
            case 1 : iv_c1.setColorFilter(getResources().getColor(R.color.colorPrimary)); break;
            case 2 : iv_c2.setColorFilter(getResources().getColor(R.color.colorPrimary)); break;
            case 3 : iv_c3.setColorFilter(getResources().getColor(R.color.colorPrimary)); break;
            case 4 : iv_c4.setColorFilter(getResources().getColor(R.color.colorPrimary)); break;
            case 5 : iv_c5.setColorFilter(getResources().getColor(R.color.colorPrimary)); break;
        }

        for (int i = 0; i < OrderPlatRepository.list_orderPlat.size(); i++)
        {
            Log.d("TAG_PASSORDER", "onCreate: --------------------> "+OrderPlatRepository.list_orderPlat.get(i).id_passOrder);
        }

    }
}
