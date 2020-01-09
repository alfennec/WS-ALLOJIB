package com.fennec.allojib.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.fennec.allojib.R;
import com.fennec.allojib.config.JsonSetCoursier;
import com.fennec.allojib.config.constant;
import com.fennec.allojib.entity.Coursier;
import com.fennec.allojib.repository.ClientRepository;
import com.fennec.allojib.repository.CoursierRepository;
import com.fennec.allojib.repository.PassOrderPlatRepository;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class CoursierActivity extends AppCompatActivity {

    public static CoursierActivity main;

    public Button btn_valider_commande;

    public ImageButton btnDatePicker, btnTimePicker;
    public EditText txtDate, txtTime;
    public TextInputLayout input_adr_col, input_adr_liv, input_detail, input_tel;

    private int mYear, mMonth, mDay, mHour, mMinute;

    public Switch sw_liv;

    public int lastPosition;

    public boolean form_right = true;

    public static ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coursier);

        main = this;

        btnDatePicker=(ImageButton)findViewById(R.id.btn_date);
        btnTimePicker=(ImageButton)findViewById(R.id.btn_time);
        txtDate=(EditText)findViewById(R.id.in_date);
        txtTime=(EditText)findViewById(R.id.in_time);

        input_adr_col = (TextInputLayout) findViewById(R.id.input_adr_col);
        input_adr_liv = (TextInputLayout) findViewById(R.id.input_adr_liv);
        input_detail = (TextInputLayout) findViewById(R.id.input_detail);
        input_tel = (TextInputLayout) findViewById(R.id.input_tel);

        sw_liv = (Switch) findViewById(R.id.sw_liv);
        sw_liv.setChecked(true);

        btn_valider_commande = (Button) findViewById(R.id.btn_valider_commande);


        btnDatePicker.setVisibility(View.GONE);
        btnTimePicker.setVisibility(View.GONE);
        txtDate.setVisibility(View.GONE);
        txtTime.setVisibility(View.GONE);


        sw_liv.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked)
                {
                    btnDatePicker.setVisibility(View.GONE);
                    btnTimePicker.setVisibility(View.GONE);
                    txtDate.setVisibility(View.GONE);
                    txtTime.setVisibility(View.GONE);
                }else
                    {
                        btnDatePicker.setVisibility(View.VISIBLE);
                        btnTimePicker.setVisibility(View.VISIBLE);
                        txtDate.setVisibility(View.VISIBLE);
                        txtTime.setVisibility(View.VISIBLE);
                    }
            }
        });

        btnDatePicker.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(main,
                        new DatePickerDialog.OnDateSetListener()
                        {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth)
                            {

                                txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        btnTimePicker.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(main,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                txtTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });

        /*** on finalizer la commande **/

        btn_valider_commande.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                form_right = true;

                if(!(sw_liv.isChecked()))
                {
                    if(txtDate.getText().toString().trim().length() == 0 || txtTime.getText().toString().trim().length() == 0)
                    {
                        form_right = false;
                    }
                }

                if(input_adr_col.getEditText().getText().toString().trim().length() == 0)
                {
                    form_right = false;
                }

                if(input_adr_liv.getEditText().getText().toString().trim().length() == 0)
                {
                    form_right = false;
                }

                if(input_detail.getEditText().getText().toString().trim().length() == 0)
                {
                    form_right = false;
                }

                if(input_tel.getEditText().getText().toString().trim().length() == 0)
                {
                    form_right = false;
                }


                if(form_right)
                {
                    traitement_function();
                } else {
                    Costum_toast("Veuillez Remplir tout les champs");
                }
            }
        });

    }

    public static void traitement_function()
    {
        Coursier current_coursier = new Coursier();

        if(main.sw_liv.isChecked())
        {
            SimpleDateFormat mydate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            SimpleDateFormat mytime = new SimpleDateFormat("HH:mm", Locale.getDefault());

            String currentDate = mydate.format(new Date());
            String currentTime = mytime.format(new Date());

            current_coursier.date_col = currentDate;
            current_coursier.heure_col = currentTime;
        }else {
            current_coursier.date_col = main.txtDate.getText().toString();
            current_coursier.heure_col = main.txtTime.getText().toString();
        }

        current_coursier.id_client = ClientRepository.main_Client.id;

        current_coursier.adr_col = main.input_adr_col.getEditText().getText().toString();
        current_coursier.adr_liv = main.input_adr_liv.getEditText().getText().toString();
        current_coursier.detail = main.input_detail.getEditText().getText().toString();
        current_coursier.tel = main.input_tel.getEditText().getText().toString();

        current_coursier.situation = 1;


        main.lastPosition = CoursierRepository.list_coursier.size();
        CoursierRepository.list_coursier.add(current_coursier);

        /*** set order in server **/

        //localhost/livraison/json/setCoursier.php?id_client=17&adr_col=nour&adr_liv=kods&detail=rien&date_col=12&heure_col=13&tel=0615442168&situation=1


        String url_informations = constant.url_host+"json/setCoursier.php?";

        String id_client = "id_client="+current_coursier.id_client;
        String adr_col = "&adr_col="+current_coursier.adr_col;
        String adr_liv = "&adr_liv="+current_coursier.adr_liv;
        String detail = "&detail="+current_coursier.detail;
        String date_col = "&date_col="+current_coursier.date_col;
        String heure_col = "&heure_col="+current_coursier.heure_col;
        String tel = "&tel="+current_coursier.tel;

        String situation = "&situation="+current_coursier.situation;

        try {
            adr_col = "&adr_col="+ URLEncoder.encode(current_coursier.adr_col, "UTF-8");
            adr_liv = "&adr_liv="+URLEncoder.encode(current_coursier.adr_liv, "UTF-8");
            detail = "&detail="+URLEncoder.encode(current_coursier.detail, "UTF-8");
        }catch (Exception e) {}


        url_informations = url_informations+id_client+adr_col+adr_liv+detail+date_col+heure_col+tel+situation;


        Log.d("TAG_JSON_DECODE", "-----------------------------> TO SEND "+ url_informations);

        JsonSetCoursier jsonSetCoursier = new JsonSetCoursier(url_informations, main);

        dialog = ProgressDialog.show(main, "", "Traitement de données. Veulliez attendre ...", true);

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

    public static void onSucces()
    {
        dialog.dismiss();
        Intent intent = new Intent(main, Commande_coursier.class);
        intent.putExtra("id_coursier", CoursierRepository.list_coursier.get(main.lastPosition).id);
        main.startActivity(intent);
        main.finish();
    }
}


