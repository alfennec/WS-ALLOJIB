package com.fennec.allojib.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fennec.allojib.R;
import com.fennec.allojib.config.JsonGetPassOrderPlat;
import com.fennec.allojib.config.JsonUrlCategoryPlat;
import com.fennec.allojib.config.JsonUrlClient;
import com.fennec.allojib.config.JsonUrlPlat;
import com.fennec.allojib.config.JsonUrlRestaurant;
import com.fennec.allojib.config.constant;
import com.fennec.allojib.repository.ClientRepository;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    public static MainActivity main;
    public static String MY_PREFS_NAME = "first_log";

    public ProgressBar progressBar2;
    public int progress = 0;

    TextInputLayout editText_email;
    TextInputLayout editText_pass;

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
                Intent intent = new Intent(main, Home_Activity.class);
                startActivity(intent);

                main.finish();
            }
            else{
                setContentView(R.layout.login_form);

                editText_email = (TextInputLayout) findViewById(R.id.editText_email);
                editText_pass = (TextInputLayout) findViewById(R.id.editText_pass);

                Button Button_valider = (Button) findViewById(R.id.Button_valider);

                Button Button_registre = (Button) findViewById(R.id.Button_registre);

                Button_valider.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        if(verifyIfBlank(editText_email) && verifyIfBlank(editText_pass))
                        {
                            String url_informations = "json/getClient.php?";

                            String email = "email="+editText_email.getEditText().getText().toString();
                            String pass = "&pass="+editText_pass.getEditText().getText().toString();

                            url_informations = constant.url_host+url_informations+email+pass;

                            Toast.makeText(main,""+editText_email.getEditText().getText().toString(), Toast.LENGTH_SHORT).show();

                            Log.d("TAG_DEPLOY", " app : "+url_informations);

                            jsonClient = new JsonUrlClient(url_informations, main);
                        }else
                            {
                                OnFailedLogin();
                            }
                    }
                });

                Button_registre.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(main, Register_form.class);
                        main.startActivity(intent);
                    }
                });

            }
        }else {
            setContentView(R.layout.not_connected);
        }

    }

    public static boolean verifyIfBlank(TextInputLayout input)
    {
        if (TextUtils.isEmpty(input.getEditText().getText().toString()))
        {
            input.setError("Champs vide");
            return false;
        }else {
            input.setErrorEnabled(false);
            return true;
        }
    }

    public static void OnSuccesLogin()
    {
        Costum_toast("Connexion faite avec succés !! ");

        Intent intent = new Intent(main, Home_Activity.class);
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
        Costum_toast(" Email ou mot de pass incorrect !");
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

        ClientRepository.main_Client.id = id;
        ClientRepository.main_Client.email = email;
        ClientRepository.main_Client.nom = nom;
        ClientRepository.main_Client.prenom = prenom;
        ClientRepository.main_Client.tel = tel;
        ClientRepository.main_Client.adresse = adresse;
        ClientRepository.main_Client.ville = ville;
        ClientRepository.main_Client.sexe = sexe;

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

    public static void Costum_toast(String msg)
    {
        /** Costume toast to test**/
        LayoutInflater inflater = main.getLayoutInflater();
        View layout = inflater.inflate(R.layout.activity_costum_toast,
                (ViewGroup) main.findViewById(R.id.toast_layout_root));

        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(msg);

        Toast toast = new Toast(main.getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
        /** end teaosot **/
    }

    public static void quitter()
    {
        SharedPreferences prefs = main.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor edit= prefs.edit();

        edit.putInt("id", 0);
        edit.putString("email", "vide");
        edit.putString("nom", "vide");
        edit.putString("prenom", "vide");
        edit.putString("tel", "vide");
        edit.putString("adresse", "vide");
        edit.putString("ville", "vide");
        edit.putInt("sexe", 0);

        edit.commit();
    }

}
