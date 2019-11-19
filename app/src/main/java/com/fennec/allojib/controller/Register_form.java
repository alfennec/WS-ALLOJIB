package com.fennec.allojib.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.fennec.allojib.R;
import com.fennec.allojib.config.JsonUrlClient;
import com.fennec.allojib.config.constant;
import com.fennec.allojib.entity.Client;
import com.fennec.allojib.repository.ClientRepository;

public class Register_form extends AppCompatActivity {

    public Button button_valide_form;
    public TextView textView_msg;

    public ProgressBar progressBar;
    public int progress = 0;
    public Handler handler = new Handler();

    public JsonUrlClient jsonClient;
    public Client new_Client;

    Register_form main;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);

        main= this;

        inRegistreForm();

    }

    public void inRegistreForm()
    {
        button_valide_form = (Button) findViewById(R.id.button_valide_form);

        button_valide_form.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                textView_msg =   (TextView) findViewById(R.id.textView_msg);
                textView_msg.setText("in Button click");

                EditText editText_pass1 = (EditText) findViewById(R.id.editText_pass1);
                EditText editText_pass2 = (EditText) findViewById(R.id.editText_pass2);

                String pass1 = (editText_pass1.getText()).toString();
                String pass2 = (editText_pass2.getText()).toString();

                if(pass1.equals(pass2))
                {
                    EditText editText_nom = (EditText) findViewById(R.id.editText_nom);
                    EditText editText_prenom = (EditText) findViewById(R.id.editText_prenom);
                    EditText editText_tel = (EditText) findViewById(R.id.editText_tel);
                    EditText editText_adresse = (EditText) findViewById(R.id.editText_adresse);
                    EditText editText_ville = (EditText) findViewById(R.id.editText_ville);
                    EditText editText_email = (EditText) findViewById(R.id.editText_email);

                    RadioButton radioButton_homme = (RadioButton) findViewById(R.id.radioButton_homme);

                    int RadioGroupe;

                    if(radioButton_homme.isChecked())
                    {
                        RadioGroupe = 1;
                    }else
                    {
                        RadioGroupe = 0;
                    }

                    new_Client = new Client(
                            (editText_email.getText()).toString(),
                            (editText_pass1.getText()).toString(),
                            (editText_nom.getText()).toString(),
                            (editText_prenom.getText()).toString(),
                            (editText_tel.getText()).toString(),
                            (editText_adresse.getText()).toString(),
                            (editText_ville.getText()).toString(),
                            RadioGroupe
                    );

                    ///livraison/json/setClient.php?email=bermod@gmail.com&pass=123456&nom=med&prenom=ber&tel=0611336605&adresse=haylaymoune&ville=oujda&sexe=1

                    String url_informations = "livraison/json/setClient.php?";
                    String email = "email="+new_Client.email;
                    String pass = "&pass="+new_Client.pass;
                    String nom = "&nom="+new_Client.nom;
                    String prenom = "&prenom="+new_Client.prenom;
                    String tel = "&tel="+new_Client.tel;
                    String adresse = "&adresse="+new_Client.adresse;
                    String ville = "&ville="+new_Client.ville;
                    String sexe = "&sexe="+new_Client.sexe;

                    url_informations = url_informations+email+pass+nom+prenom+tel+adresse+ville+sexe;

                    jsonClient = new JsonUrlClient(constant.url_host+url_informations, main);


                    progressBar = (ProgressBar) findViewById(R.id.progress_json);
                    progressBar.setMax(100);
                    progress = progressBar.getProgress();
                    new Thread(new Runnable()
                    {
                        public void run()
                        {
                            while (jsonClient.result_succes)
                            {
                                progress += 10;
                                handler.post(new Runnable()
                                {
                                    public void run()
                                    {
                                        progressBar.setProgress(progress);
                                    }
                                });

                                try
                                {
                                    // Sleep for 100 milliseconds to show the progress slowly.
                                    Thread.sleep(100);
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
                                    /*String url_informations = "livraison/json/getClient.php";
                                    String email = "email="+new_Client.email;
                                    String tel = "&tel="+new_Client.tel;

                                    url_informations = url_informations+email+tel;

                                    jsonClient = new JsonUrlClient(constant.url_host+url_informations, main);*/

                                    ClientRepository.main_Client = new_Client;

                                    progressBar.setProgress(100);

                                    Toast.makeText(main,"fait avec succes", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(main, Menu_Activity.class);
                                    startActivity(intent);

                                    main.finish();
                                }
                            });
                        }
                    }).start();

                    /*SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putInt("id", 12);
                    editor.putString("email", "Elena");
                    editor.apply();*/


                }else
                {
                    textView_msg.setText("Retapez votre mot de passe");
                }
            }
        });
    }
}
