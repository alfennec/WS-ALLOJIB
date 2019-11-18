package com.fennec.allojib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.fennec.allojib.config.JsonUrlClient;
import com.fennec.allojib.config.constant;
import com.fennec.allojib.entity.Client;

public class MainActivity extends AppCompatActivity {

    public static MainActivity main;
    String MY_PREFS_NAME = "first_log";

    public Button button_valide_form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main = this;

        if(isNetworkConnected())
        {
            inRegistreForm();
        }else {
            setContentView(R.layout.not_connected);

        }


        /*SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        int idClient = prefs.getInt("idClient", 0);
        String email = prefs.getString("email", "vide");

        if(idClient == 0 && email.equals("vide"))
        {
            setContentView(R.layout.register_form);
        }else {
            setContentView(R.layout.login_form);
        }*/





    }



    public void inRegistreForm()
    {
        setContentView(R.layout.register_form);

        button_valide_form = (Button) findViewById(R.id.button_valide_form);

        button_valide_form.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TextView textView_msg =   (TextView) findViewById(R.id.textView_msg);
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

                    Client new_Client = new Client(
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

                    JsonUrlClient jsonClient = new JsonUrlClient(constant.url_host+url_informations, main);


                    Toast toast = Toast.makeText(main, "succes", Toast.LENGTH_SHORT);
                    toast.show();

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

    private boolean isNetworkConnected()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
