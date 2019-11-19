package com.fennec.allojib.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.fennec.allojib.R;
import com.fennec.allojib.repository.ClientRepository;

public class Menu_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        TextView client_test = (TextView) findViewById(R.id.client_test);

        client_test.setText(ClientRepository.main_Client.nom+" "+ClientRepository.main_Client.prenom);


    }
}
