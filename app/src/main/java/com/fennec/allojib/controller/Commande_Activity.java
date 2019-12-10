package com.fennec.allojib.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.fennec.allojib.R;
import com.fennec.allojib.entity.PassOrderPlat;
import com.fennec.allojib.repository.PassOrderPlatRepository;

public class Commande_Activity extends AppCompatActivity {

    public static Commande_Activity main;

    public TextView order_id,tv_total;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commande);
        main = this;

        order_id = (TextView) findViewById(R.id.order_id);
        tv_total = (TextView) findViewById(R.id.tv_total);

        order_id.setText("N°"+PassOrderPlatRepository.list_passOrderPlat.get(PassOrderPlatRepository.list_passOrderPlat.size()-1).id);
        tv_total.setText("Total :"+PassOrderPlatRepository.list_passOrderPlat.get(PassOrderPlatRepository.list_passOrderPlat.size()-1).total+"MAD");

    }
}
