package com.fennec.allojib.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.fennec.allojib.R;
import com.fennec.allojib.adapter.CommandePlatAdapter;
import com.fennec.allojib.adapter.OrderPlatAdapter;
import com.fennec.allojib.config.JsonGetOrderPlat;
import com.fennec.allojib.config.constant;
import com.fennec.allojib.entity.PassOrderPlat;
import com.fennec.allojib.repository.OrderPlatRepository;
import com.fennec.allojib.repository.OrderProductRepository;
import com.fennec.allojib.repository.PassOrderPlatRepository;

public class Commande_Activity extends AppCompatActivity {

    public static PassOrderPlat CurrentPassOrder;
    public static Commande_Activity main;

    public TextView order_id,tv_total;

    public static RecyclerView recyclerView;
    public static CommandePlatAdapter commandePlatAdapter;

    public static ImageView iv_c1,iv_c2,iv_c3,iv_c4,iv_c5;
    public static TextView tv_c1,tv_c2,tv_c3,tv_c4,tv_c5;

    public int id_order;

    public static ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commande);
        main = this;

        id_order = getIntent().getIntExtra("id_order",0);

        for (int i = 0; i < PassOrderPlatRepository.list_passOrderPlat.size(); i++)
        {
            if(PassOrderPlatRepository.list_passOrderPlat.get(i).id == id_order)
            {
                CurrentPassOrder = PassOrderPlatRepository.list_passOrderPlat.get(i);
            }
        }

        order_id = (TextView) findViewById(R.id.order_id);
        tv_total = (TextView) findViewById(R.id.tv_total);

        order_id.setText("N°"+CurrentPassOrder.id);
        tv_total.setText("Total :"+CurrentPassOrder.total+" MAD");


        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

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

        int situation = CurrentPassOrder.situation;

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

        /************** get order ***/

        OrderPlatRepository.list_orderPlat.clear();

        String url_informations = constant.url_host+"json/getOrderPlat.php?";

        String my_id_order = "id_order="+ id_order;

        url_informations = url_informations+my_id_order;

        Log.d("TAG_JSON_ORDER", "-----------------------------> TO SEND "+ url_informations);
        Log.d("TAG_JSON_ORDER", "-----------------------------> TO SEND "+ my_id_order);

        JsonGetOrderPlat jsonGetOrderPlat = new JsonGetOrderPlat(url_informations, main,2);

        dialog = ProgressDialog.show(main, "", "Traitement de données. Veulliez attendre ...", true);

    }

    public static void onSucces()
    {
        /** adapter for test we have to improve our self for this app  **/
        LinearLayoutManager lm = new LinearLayoutManager(main, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lm);

        commandePlatAdapter = new CommandePlatAdapter(OrderPlatRepository.WithIdorder(CurrentPassOrder.id));
        recyclerView.setAdapter(commandePlatAdapter);

        Log.d("TAG_PASSORDER", "onCreate: --------------------> "+OrderPlatRepository.WithIdorder(CurrentPassOrder.id).size());

        dialog.dismiss();
    }
}
