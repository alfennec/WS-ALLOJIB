package com.fennec.allojib.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.fennec.allojib.R;
import com.fennec.allojib.adapter.CommandePlatAdapter;
import com.fennec.allojib.entity.Coursier;
import com.fennec.allojib.entity.PassOrderPlat;
import com.fennec.allojib.repository.CoursierRepository;
import com.fennec.allojib.repository.OrderPlatRepository;
import com.fennec.allojib.repository.PassOrderPlatRepository;

public class Commande_coursier extends AppCompatActivity {

    public static Coursier CurrentCoursier;
    public static Commande_coursier main;

    public TextView order_id, tv_detail, tv_adresse;


    public static ImageView iv_c1,iv_c2,iv_c3,iv_c4,iv_c5;
    public static TextView tv_c1,tv_c2,tv_c3,tv_c4,tv_c5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commande_coursier);

        main = this;

        int id_order = getIntent().getIntExtra("id_coursier",0);

        for (int i = 0; i < CoursierRepository.list_coursier.size(); i++)
        {
            if(CoursierRepository.list_coursier.get(i).id == id_order)
            {
                CurrentCoursier = CoursierRepository.list_coursier.get(i);
            }
        }

        order_id = (TextView) findViewById(R.id.order_id);
        tv_detail = (TextView) findViewById(R.id.tv_detail);
        tv_adresse = (TextView) findViewById(R.id.tv_adresse);

        order_id.setText("N°"+CurrentCoursier.id);
        tv_detail.setText(CurrentCoursier.detail);
        tv_adresse.setText(CurrentCoursier.adr_liv);



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

        int situation = CurrentCoursier.situation;

        switch (situation)
        {
            case 1 : iv_c1.setColorFilter(getResources().getColor(R.color.colorPrimary)); break;
            case 2 : iv_c2.setColorFilter(getResources().getColor(R.color.colorPrimary)); break;
            case 3 : iv_c3.setColorFilter(getResources().getColor(R.color.colorPrimary)); break;
            case 4 : iv_c4.setColorFilter(getResources().getColor(R.color.colorPrimary)); break;
            case 5 : iv_c5.setColorFilter(getResources().getColor(R.color.colorPrimary)); break;
        }
        
    }
}
