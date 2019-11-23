package com.fennec.allojib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.fennec.allojib.config.JsonUrlCategoryPlat;
import com.fennec.allojib.config.JsonUrlClient;
import com.fennec.allojib.config.JsonUrlPlat;
import com.fennec.allojib.config.JsonUrlRestaurant;
import com.fennec.allojib.config.constant;
import com.fennec.allojib.controller.Menu_Activity;
import com.fennec.allojib.entity.Client;
import com.fennec.allojib.repository.ClientRepository;

public class MainActivity extends AppCompatActivity {

    public static MainActivity main;
    String MY_PREFS_NAME = "first_log";

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
            /*if(isSharedPreferences())
            {
                Intent intent = new Intent(main, Menu_Activity.class);
                startActivity(intent);

                main.finish();
            }
            else{*/
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

                        url_informations = constant.url_host+"/json/getTable.php?table=";

                        JsonUrlPlat jsonUrlPlat = new JsonUrlPlat(url_informations+"tbl_plat", main);
                        JsonUrlRestaurant jsonRestaurant = new JsonUrlRestaurant(url_informations+"tbl_retaurant", main);
                        JsonUrlCategoryPlat jsonUrlCategoryPlat = new JsonUrlCategoryPlat(url_informations+"tbl_category_plat", main);

                        //Log.d("TAG_JSON", "onClick: SEND URL " +url_informations);
                        //Toast.makeText(main,"onClick: SEND URL " +url_informations, Toast.LENGTH_SHORT).show();

                        new Thread(new Runnable()
                        {
                            public void run()
                            {
                                while (progress < 100)
                                {
                                    progress += 1;
                                    handler.post(new Runnable()
                                    {
                                        public void run()
                                        {
                                            progressBar2.setProgress(progress);
                                        }
                                    });

                                    try
                                    {
                                        // Sleep for 100 milliseconds to show the progress slowly.
                                        Thread.sleep(50);
                                    } catch (InterruptedException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                // Progress finished, re-enter UI thread and set text
                                handler.post(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        progressBar2.setProgress(100);

                                        if(!jsonClient.result_error)
                                        {
                                            Toast.makeText(main,"Connexion faite avec succés", Toast.LENGTH_SHORT).show();

                                            Intent intent = new Intent(main, Menu_Activity.class);
                                            startActivity(intent);

                                            main.finish();

                                        }else {
                                            Toast.makeText(main,"Email ou mot de pass incorrect ", Toast.LENGTH_SHORT).show();
                                            }

                                    }
                                });
                            }
                        }).start();
                    }
                });

            //}
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
