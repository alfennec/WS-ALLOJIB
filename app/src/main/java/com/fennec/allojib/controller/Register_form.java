package com.fennec.allojib.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.fennec.allojib.R;
import com.fennec.allojib.config.JsonUrlClient;
import com.fennec.allojib.config.JsonUrlResgistre;
import com.fennec.allojib.config.constant;
import com.fennec.allojib.entity.Client;
import com.fennec.allojib.repository.ClientRepository;
import com.google.android.material.textfield.TextInputLayout;

import java.net.URLEncoder;

public class Register_form extends AppCompatActivity {

    public Button button_valide_form;
    public TextView textView_msg;

    public int progress = 0;
    public Handler handler = new Handler();

    public JsonUrlResgistre jsonRegister;
    public Client new_Client;

    public static Register_form main;

    public static Boolean all_Right ;

    public static ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);

        main= this;

        main.setTitle("Inscrivez-vous ! ");

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
                TextInputLayout editText_pass1 = (TextInputLayout) findViewById(R.id.input_pass);
                TextInputLayout editText_pass2 = (TextInputLayout) findViewById(R.id.input_pass2);

                String pass1 = editText_pass1.getEditText().getText().toString();
                String pass2 = editText_pass2.getEditText().getText().toString();

                TextInputLayout editText_nom = (TextInputLayout) findViewById(R.id.input_nom);
                TextInputLayout editText_prenom = (TextInputLayout) findViewById(R.id.input_prenom);
                TextInputLayout editText_tel = (TextInputLayout) findViewById(R.id.input_tel);
                TextInputLayout editText_adresse = (TextInputLayout) findViewById(R.id.input_adresse);
                TextInputLayout editText_ville = (TextInputLayout) findViewById(R.id.input_ville);
                TextInputLayout editText_email = (TextInputLayout) findViewById(R.id.input_email);

                RadioButton radioButton_homme = (RadioButton) findViewById(R.id.radioButton_homme);

                if(verifyIfBlank(editText_nom)
                        && verifyIfBlank(editText_prenom)
                        && verifyIfBlank(editText_tel)
                        && verifyIfBlank(editText_adresse)
                        && verifyIfBlank(editText_ville)
                        && verifyIfBlank(editText_email)
                        && verifyIfBlank(editText_pass1)
                        && verifyIfBlank(editText_pass2))
                {
                    all_Right = true;
                }else
                {
                    all_Right = false;
                }

                if(all_Right)
                {
                    if(pass1.equals(pass2))
                    {
                        int RadioGroupe;

                        if(radioButton_homme.isChecked())
                        {
                            RadioGroupe = 1;
                        }else
                        {
                            RadioGroupe = 0;
                        }

                        new_Client = new Client(
                                editText_email.getEditText().getText().toString(),
                                editText_pass1.getEditText().getText().toString(),
                                editText_nom.getEditText().getText().toString(),
                                editText_prenom.getEditText().getText().toString(),
                                editText_tel.getEditText().getText().toString(),
                                editText_adresse.getEditText().getText().toString(),
                                editText_ville.getEditText().getText().toString(),
                                RadioGroupe
                        );

                        ///livraison/json/setClient.php?email=bermod@gmail.com&pass=123456&nom=med&prenom=ber&tel=0611336605&adresse=haylaymoune&ville=oujda&sexe=1

                        String url_informations = "json/setClient.php?";
                        String email            = "email="+new_Client.email;
                        String pass             = "&pass="+new_Client.pass;
                        String nom              = "&nom="+new_Client.nom;
                        String prenom           = "&prenom="+new_Client.prenom;
                        String tel              = "&tel="+new_Client.tel;
                        String adresse          = "&adresse="+new_Client.adresse;
                        String ville            = "&ville="+new_Client.ville;
                        String sexe             = "&sexe="+new_Client.sexe;

                        try {
                            adresse = "&adresse="+ URLEncoder.encode(new_Client.adresse, "UTF-8");
                        }catch (Exception e) {}

                        url_informations = url_informations+email+pass+nom+prenom+tel+adresse+ville+sexe;

                        jsonRegister = new JsonUrlResgistre(constant.url_host+url_informations, main);

                        dialog = ProgressDialog.show(main, "", "Traitement de données. Veulliez attendre ...", true);

                    }else
                    {
                        textView_msg.setText("Retapez votre mot de passe");
                    }
                }
            }
        });
    }

    public static void OnSuccesRegistre()
    {
        dialog.dismiss();

        Costum_toast("Inscription faite avec succes");
        main.finish();
    }

    public static void OnFailedRegistre()
    {
        dialog.dismiss();

        Costum_toast("Erreur veuillez resaisir vos données");
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
}
