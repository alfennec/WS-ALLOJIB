package com.fennec.allojib.controller.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fennec.allojib.R;
import com.fennec.allojib.adapter.PassOrderPlatAdapter;
import com.fennec.allojib.config.JsonUpdateClient;
import com.fennec.allojib.config.JsonUrlResgistre;
import com.fennec.allojib.config.constant;
import com.fennec.allojib.controller.Commande_Activity;
import com.fennec.allojib.controller.Order_Plat_Activity;
import com.fennec.allojib.controller.ui.commande.CommandeFragment;
import com.fennec.allojib.controller.ui.commande.CommandeViewModel;
import com.fennec.allojib.entity.Client;
import com.fennec.allojib.repository.ClientRepository;
import com.fennec.allojib.repository.PassOrderPlatRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;

    public static ProfileFragment main;

    public TextView tv_nameUser, tv_total, tv_nom, tv_prenom, tv_sexe, tv_email, tv_tel, tv_adresse;

    public EditText edt_nom, edt_prenom, edt_email, edt_tel, edt_adresse, edt_pass1, edt_pass2;

    public ImageButton btn_edit;

    public RadioButton radioButton_femme,radioButton_homme;

    public FloatingActionButton floatingButton;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        main = this;

        //final TextView textView = root.findViewById(R.id.text_commande);

        tv_nameUser = (TextView) root.findViewById(R.id.tv_nameUser);
        tv_total = (TextView) root.findViewById(R.id.tv_total);
        tv_nom = (TextView) root.findViewById(R.id.tv_nom);
        tv_prenom= (TextView) root.findViewById(R.id.tv_prenom);
        tv_sexe = (TextView) root.findViewById(R.id.tv_sexe);
        tv_email = (TextView) root.findViewById(R.id.tv_email);
        tv_tel = (TextView) root.findViewById(R.id.tv_tel);
        tv_adresse = (TextView) root.findViewById(R.id.tv_adresse);

        edt_nom = (EditText) root.findViewById(R.id.edt_nom);
        edt_prenom = (EditText) root.findViewById(R.id.edt_prenom);
        edt_email = (EditText) root.findViewById(R.id.edt_email);
        edt_tel = (EditText) root.findViewById(R.id.edt_tel);
        edt_adresse = (EditText) root.findViewById(R.id.edt_adresse);
        edt_pass1 = (EditText) root.findViewById(R.id.edt_pass1);
        edt_pass2 = (EditText) root.findViewById(R.id.edt_pass2);

        btn_edit = (ImageButton) root.findViewById(R.id.btn_edit);

        floatingButton = (FloatingActionButton) root.findViewById(R.id.floatButton);

        radioButton_femme  = (RadioButton) root.findViewById(R.id.radioButton_femme);
        radioButton_homme  = (RadioButton) root.findViewById(R.id.radioButton_homme);

        tv_nameUser.setText(ClientRepository.main_Client.prenom+" "+ClientRepository.main_Client.nom);

        tv_nom.setText(ClientRepository.main_Client.nom);
        tv_prenom.setText(ClientRepository.main_Client.prenom);
        tv_email.setText(" "+ClientRepository.main_Client.email);
        tv_tel.setText(" "+ClientRepository.main_Client.tel);
        tv_adresse.setText(" "+ClientRepository.main_Client.adresse);

        edt_nom.setText(ClientRepository.main_Client.nom);
        edt_prenom.setText(ClientRepository.main_Client.prenom);
        edt_email.setText(" "+ClientRepository.main_Client.email);
        edt_tel.setText(" "+ClientRepository.main_Client.tel);
        edt_adresse.setText(" "+ClientRepository.main_Client.adresse);

        if(ClientRepository.main_Client.sexe == 1)
        {
            tv_sexe.setText("Homme");
            radioButton_homme.setChecked(true);
        }else
            {
                tv_sexe.setText("Femme");
                radioButton_femme.setChecked(true);
            }


        edt_nom.setVisibility(View.GONE);
        edt_prenom.setVisibility(View.GONE);
        edt_email.setVisibility(View.GONE);
        edt_tel.setVisibility(View.GONE);
        edt_adresse.setVisibility(View.GONE);
        edt_pass1.setVisibility(View.GONE);
        edt_pass2.setVisibility(View.GONE);

        radioButton_homme.setVisibility(View.GONE);
        radioButton_femme.setVisibility(View.GONE);

        btn_edit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                tv_nameUser.setVisibility(View.GONE);
                tv_total.setVisibility(View.GONE);
                tv_nom.setVisibility(View.GONE);
                tv_prenom.setVisibility(View.GONE);
                tv_sexe.setVisibility(View.GONE);
                tv_email.setVisibility(View.GONE);
                tv_tel.setVisibility(View.GONE);
                tv_adresse.setVisibility(View.GONE);

                edt_nom.setVisibility(View.VISIBLE);
                edt_prenom.setVisibility(View.VISIBLE);
                edt_email.setVisibility(View.VISIBLE);
                edt_tel.setVisibility(View.VISIBLE);
                edt_adresse.setVisibility(View.VISIBLE);
                edt_pass1.setVisibility(View.VISIBLE);
                edt_pass2.setVisibility(View.VISIBLE);

                radioButton_homme.setVisibility(View.VISIBLE);
                radioButton_femme.setVisibility(View.VISIBLE);
            }
        });

        floatingButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ClientRepository.main_Client.email = edt_email.getText().toString();

                if(edt_pass1.getText() == edt_pass2.getText())
                {
                    ClientRepository.main_Client.pass = edt_pass1.getText().toString();
                }

                ClientRepository.main_Client.nom = edt_nom.getText().toString();
                ClientRepository.main_Client.prenom = edt_prenom.getText().toString();
                ClientRepository.main_Client.tel = edt_tel.getText().toString();
                ClientRepository.main_Client.adresse = edt_adresse.getText().toString();

                if(radioButton_homme.isChecked())
                {
                    ClientRepository.main_Client.sexe = 1;
                }else
                    {
                        ClientRepository.main_Client.sexe = 2;
                    }


                String url_informations = "json/setClient.php?";
                String email            = "email="+ClientRepository.main_Client.email;
                String pass             = "&pass="+ClientRepository.main_Client.pass;
                String nom              = "&nom="+ClientRepository.main_Client.nom;
                String prenom           = "&prenom="+ClientRepository.main_Client.prenom;
                String tel              = "&tel="+ClientRepository.main_Client.tel;
                String adresse          = "&adresse="+ClientRepository.main_Client.adresse;
                String ville            = "&ville="+ClientRepository.main_Client.ville;
                String sexe             = "&sexe="+ClientRepository.main_Client.sexe;

                url_informations = url_informations+email+pass+nom+prenom+tel+adresse+ville+sexe;

                JsonUpdateClient updateClient = new JsonUpdateClient(constant.url_host+url_informations, main.getContext());

                /** past data in right place to do **/
                tv_nameUser.setText(ClientRepository.main_Client.prenom+" "+ClientRepository.main_Client.nom);

                tv_nom.setText(ClientRepository.main_Client.nom);
                tv_prenom.setText(ClientRepository.main_Client.prenom);
                tv_email.setText(" "+ClientRepository.main_Client.email);
                tv_tel.setText(" "+ClientRepository.main_Client.tel);
                tv_adresse.setText(" "+ClientRepository.main_Client.adresse);

                tv_nameUser.setVisibility(View.VISIBLE);
                tv_total.setVisibility(View.VISIBLE);
                tv_nom.setVisibility(View.VISIBLE);
                tv_prenom.setVisibility(View.VISIBLE);
                tv_sexe.setVisibility(View.VISIBLE);
                tv_email.setVisibility(View.VISIBLE);
                tv_tel.setVisibility(View.VISIBLE);
                tv_adresse.setVisibility(View.VISIBLE);

                edt_nom.setVisibility(View.GONE);
                edt_prenom.setVisibility(View.GONE);
                edt_email.setVisibility(View.GONE);
                edt_tel.setVisibility(View.GONE);
                edt_adresse.setVisibility(View.GONE);
                edt_pass1.setVisibility(View.GONE);
                edt_pass2.setVisibility(View.GONE);

                radioButton_homme.setVisibility(View.GONE);
                radioButton_femme.setVisibility(View.GONE);
            }
        });

        profileViewModel.getText().observe(this, new Observer<String>()
        {
            @Override
            public void onChanged(@Nullable String s)
            {
                // textView.setText(s);
            }
        });
        return root;
    }


    public static void Costum_toast(String msg)
    {
        /** Costume toast to test**/
        LayoutInflater inflater = main.getLayoutInflater();
        View layout = inflater.inflate(R.layout.activity_costum_toast, (ViewGroup) main.getActivity().findViewById(R.id.toast_layout_root));

        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(msg);

        Toast toast = new Toast(main.getActivity().getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
        /** end teaosot **/
    }

    public static void OnSuccesLogin()
    {
        Costum_toast("Mise à jour du profile faite avec succée ");
    }

    public static void OnFailedLogin()
    {
        Costum_toast("Votre Mise à jour a échouer ");
    }
}