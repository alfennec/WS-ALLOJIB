package com.fennec.allojib.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.fennec.allojib.R;
import com.fennec.allojib.adapter.CategoryPlatAdapter;
import com.fennec.allojib.adapter.RestaurantAdapter;
import com.fennec.allojib.entity.Plat;
import com.fennec.allojib.repository.CategoryPlatRepository;
import com.fennec.allojib.repository.ClientRepository;
import com.fennec.allojib.repository.PassOrderPlatRepository;
import com.fennec.allojib.repository.PlatRepository;
import com.fennec.allojib.repository.RestaurantRepository;

public class Menu_Activity extends AppCompatActivity {

    public static Menu_Activity main;
    public TextView tv_nameClient,tv_email;

    Button button_restaurant, button_superMarket, button_coursierExpress;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        main = this;
        setContentView(R.layout.activity_menu);

        tv_nameClient = (TextView) findViewById(R.id.tv_nameClient);
        tv_email = (TextView) findViewById(R.id.tv_email);

        tv_nameClient.setText(ClientRepository.main_Client.nom+" "+ClientRepository.main_Client.prenom);
        tv_email.setText(ClientRepository.main_Client.email);

        /*** Button to set from activity **/
        button_restaurant = (Button) findViewById(R.id.button_restaurant);
        button_superMarket = (Button) findViewById(R.id.button_superMarket);
        button_coursierExpress = (Button) findViewById(R.id.button_coursierExpress);

        button_restaurant.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(main, Restaurant_Activity.class);
                startActivity(intent);
            }
        });

        button_superMarket.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(main, Home_Activity.class);
                startActivity(intent);
            }
        });


    }
}
