package com.fennec.allojib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.fennec.allojib.config.JsonUrlClient;
import com.fennec.allojib.config.constant;
import com.fennec.allojib.controller.Menu_Activity;
import com.fennec.allojib.entity.Client;
import com.fennec.allojib.repository.ClientRepository;

public class MainActivity extends AppCompatActivity {

    public static MainActivity main;
    String MY_PREFS_NAME = "first_log";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        main = this;

        if(isNetworkConnected())
        {
            if(isSharedPreferences())
            {
                Intent intent = new Intent(main, Menu_Activity.class);
                startActivity(intent);

                main.finish();
            }
            else{
                setContentView(R.layout.login_form);

                EditText editText_email = (EditText) findViewById(R.id.editText_email);
                EditText editText_pass = (EditText) findViewById(R.id.editText_pass);

                Button Button_valider = (Button) findViewById(R.id.Button_valider);
                Button_valider.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Toast.makeText(main,"connexion to the App", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }else {
            setContentView(R.layout.not_connected);
        }
    }

    public boolean isSharedPreferences()
    {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        int id = prefs.getInt("id", 0);
        String email = prefs.getString("email", "vide");
        String nom = prefs.getString("nom", "vide");
        String prenom = prefs.getString("prenom", "vide");
        String tel = prefs.getString("tel", "vide");
        String adresse = prefs.getString("adresse", "vide");
        String ville = prefs.getString("ville", "vide");
        int sexe = prefs.getInt("sexe", 0);

        if(id == 0 && email.equals("vide"))
        {
            return true;
        }else {
            return false;
        }
    }

    private boolean isNetworkConnected()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

}
