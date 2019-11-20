package com.fennec.allojib.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fennec.allojib.R;
import com.fennec.allojib.repository.ClientRepository;

public class Menu_Activity extends AppCompatActivity {

    TextView client_test;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        client_test = (TextView) findViewById(R.id.client_test);

        client_test.setText(ClientRepository.main_Client.nom+" "+ClientRepository.main_Client.prenom);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                client_test.setText(ClientRepository.main_Client.nom+" "+ClientRepository.main_Client.prenom);
            }
        });


    }
}
