package com.fennec.allojib.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.fennec.allojib.R;
import com.fennec.allojib.adapter.CommandePlatAdapter;
import com.fennec.allojib.adapter.CommandeProductAdapter;
import com.fennec.allojib.config.JsonGetOrderProduct;
import com.fennec.allojib.config.constant;
import com.fennec.allojib.entity.PassOrderPlat;
import com.fennec.allojib.entity.PassOrderProduct;
import com.fennec.allojib.repository.OrderPlatRepository;
import com.fennec.allojib.repository.OrderProductRepository;
import com.fennec.allojib.repository.PassOrderPlatRepository;
import com.fennec.allojib.repository.PassOrderProductRepository;

public class Commande_product extends AppCompatActivity {

    public static PassOrderProduct CurrentPassOrder;
    public static Commande_product main;

    public TextView order_id,tv_total;

    public static RecyclerView recyclerView;
    public static CommandeProductAdapter commandeProductAdapter;

    public static ImageView iv_c1,iv_c2,iv_c3,iv_c4,iv_c5;
    public static TextView tv_c1,tv_c2,tv_c3,tv_c4,tv_c5;

    public static ProgressDialog dialog;

    public static int id_order;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commande_product);

        main = this;

        id_order = getIntent().getIntExtra("id_order",0);

        for (int i = 0; i < PassOrderProductRepository.list_passOrderProduct.size(); i++)
        {
            if(PassOrderProductRepository.list_passOrderProduct.get(i).id == id_order)
            {
                CurrentPassOrder = PassOrderProductRepository.list_passOrderProduct.get(i);
            }
        }

        order_id = (TextView) findViewById(R.id.order_id);
        tv_total = (TextView) findViewById(R.id.tv_total);

        order_id.setText("N°"+CurrentPassOrder.id);
        tv_total.setText("Total :"+CurrentPassOrder.total+" MAD");


        /** adapter for test we have to improve our self for this app  **/
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager lm = new LinearLayoutManager(main, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lm);

        commandeProductAdapter = new CommandeProductAdapter(OrderProductRepository.WithIdorder(CurrentPassOrder.id));
        recyclerView.setAdapter(commandeProductAdapter);
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


        OrderProductRepository.list_orderProduct.clear();

        String url_informations = constant.url_host+"json/getOrderProduct.php?";

        String myid_order = "id_order="+ id_order;

        url_informations = url_informations+myid_order;

        Log.d("TAG_JSON_ORDER", "-----------------------------> TO SEND "+ url_informations);
        Log.d("TAG_JSON_ORDER", "-----------------------------> TO SEND "+ myid_order);

        JsonGetOrderProduct jsonGetOrderProduct = new JsonGetOrderProduct(url_informations, main, 2);

        dialog = ProgressDialog.show(main, "", "Traitement de données. Veulliez attendre ...", true);
    }

    public static void onSucces()
    {
        /** adapter for test we have to improve our self for this app  **/
        LinearLayoutManager lm = new LinearLayoutManager(main, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lm);

        commandeProductAdapter = new CommandeProductAdapter(OrderProductRepository.WithIdorder(CurrentPassOrder.id));
        recyclerView.setAdapter(commandeProductAdapter);

        Log.d("TAG_PASSORDER", "onCreate: --------------------> "+OrderPlatRepository.WithIdorder(CurrentPassOrder.id).size());

        dialog.dismiss();
    }
}
