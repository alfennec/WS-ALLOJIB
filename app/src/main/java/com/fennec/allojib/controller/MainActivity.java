package com.fennec.allojib.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fennec.allojib.R;
import com.fennec.allojib.config.JsonUrlCategoryPlat;
import com.fennec.allojib.config.JsonUrlClient;
import com.fennec.allojib.config.JsonUrlPlat;
import com.fennec.allojib.config.JsonUrlRestaurant;
import com.fennec.allojib.config.constant;
import com.fennec.allojib.repository.ClientRepository;

public class MainActivity extends AppCompatActivity {

    public static MainActivity main;
    public static String MY_PREFS_NAME = "first_log";

    public ProgressBar progressBar2;
    public int progress = 0;

    EditText editText_email;
    EditText editText_pass;

    public Handler handler = new Handler();

    public JsonUrlClient jsonClient;

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

                editText_email = (EditText) findViewById(R.id.editText_email);
                editText_pass = (EditText) findViewById(R.id.editText_pass);

                progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
                progressBar2.setMax(100);
                progress = progressBar2.getProgress();

                Button Button_valider = (Button) findViewById(R.id.Button_valider);
                Button_valider.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        //Toast.makeText(main,"connexion to the App", Toast.LENGTH_SHORT).show();

                        String url_informations = "/json/getClient.php?";

                        String email = "email="+(editText_email.getText()).toString();
                        String pass = "&pass="+(editText_pass.getText()).toString();

                        url_informations = constant.url_host+url_informations+email+pass;

                        jsonClient = new JsonUrlClient(url_informations, main);
                    }
                });

            }
        }else {
            setContentView(R.layout.not_connected);
        }
    }

    public static void OnSuccesLogin()
    {
        Toast.makeText(main,"Connexion faite avec succés", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(main, Menu_Activity.class);
        main.startActivity(intent);

        SharedPreferences prefs = main.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor edit= prefs.edit();

        edit.putInt("id", ClientRepository.main_Client.id);
        edit.putString("email", ClientRepository.main_Client.email);
        edit.putString("nom", ClientRepository.main_Client.nom);
        edit.putString("prenom", ClientRepository.main_Client.prenom);
        edit.putString("tel", ClientRepository.main_Client.tel);
        edit.putString("adresse", ClientRepository.main_Client.adresse);
        edit.putString("ville", ClientRepository.main_Client.ville);
        edit.putInt("sexe", ClientRepository.main_Client.sexe);

        edit.commit();
        main.finish();
    }

    public static void OnFailedLogin()
    {
        Toast.makeText(main,"Email ou mot de pass incorrect ", Toast.LENGTH_SHORT).show();
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
            return false;
        }else {
            return true;
        }
    }

    private boolean isNetworkConnected()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

}
