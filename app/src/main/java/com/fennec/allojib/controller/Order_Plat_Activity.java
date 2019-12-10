package com.fennec.allojib.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.fennec.allojib.R;
import com.fennec.allojib.adapter.CategoryPlatAdapter;
import com.fennec.allojib.adapter.OrderPlatAdapter;
import com.fennec.allojib.adapter.RestaurantAdapter;
import com.fennec.allojib.config.JsonUrlClient;
import com.fennec.allojib.config.JsonUrlOrderPlat;
import com.fennec.allojib.config.JsonUrlPassOrderPlat;
import com.fennec.allojib.config.JsonUrlPlat;
import com.fennec.allojib.config.constant;
import com.fennec.allojib.entity.OrderPlat;
import com.fennec.allojib.entity.PassOrderPlat;
import com.fennec.allojib.repository.CategoryPlatRepository;
import com.fennec.allojib.repository.ClientRepository;
import com.fennec.allojib.repository.OrderPlatRepository;
import com.fennec.allojib.repository.PassOrderPlatRepository;
import com.fennec.allojib.repository.RestaurantRepository;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class Order_Plat_Activity extends AppCompatActivity {

    public static RecyclerView recyclerView;
    public static OrderPlatAdapter orderPlatAdapter;

    public static Order_Plat_Activity main;

    public Button btn_maps,btn_valider_commande;

    public Button btnDatePicker, btnTimePicker;
    public EditText txtDate, txtTime;
    public TextInputEditText personne_conserner, tel_personne, adresse_maps, note_client;

    private int mYear, mMonth, mDay, mHour, mMinute;

    public RadioButton btn_radio_l1,btn_radio_l2,btn_radio_moi,btn_radio_autre;

    public Handler handler = new Handler();
    public int progress = 0;
    public ProgressBar progressBar4;

    public int lastPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_plat);
        main = this;

        /** adapter for test we have to improve our self for this app  **/
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager lm = new LinearLayoutManager(main, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lm);

        orderPlatAdapter = new OrderPlatAdapter(OrderPlatRepository.list_orderPlat);
        recyclerView.setAdapter(orderPlatAdapter);

        btn_radio_l1 = (RadioButton) findViewById(R.id.btn_radio_l1);
        btn_radio_l2 = (RadioButton) findViewById(R.id.btn_radio_l2);

        btn_radio_moi = (RadioButton) findViewById(R.id.btn_radio_moi);
        btn_radio_autre = (RadioButton) findViewById(R.id.btn_radio_autre);

        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        txtDate=(EditText)findViewById(R.id.in_date);
        txtTime=(EditText)findViewById(R.id.in_time);
        btn_maps = (Button) findViewById(R.id.btn_maps);

        personne_conserner = (TextInputEditText)findViewById(R.id.personne_conserner);
        tel_personne = (TextInputEditText)findViewById(R.id.tel_personne);

        adresse_maps = (TextInputEditText)findViewById(R.id.adresse_maps);
        note_client = (TextInputEditText)findViewById(R.id.note_client);

        btn_valider_commande = (Button) findViewById(R.id.btn_valider_commande);

        progressBar4 = (ProgressBar) findViewById(R.id.progressBar4);
        progressBar4.setMax(100);
        progress = progressBar4.getProgress();

        btnDatePicker.setVisibility(View.GONE);
        btnTimePicker.setVisibility(View.GONE);
        txtDate.setVisibility(View.GONE);
        txtTime.setVisibility(View.GONE);

        personne_conserner.setVisibility(View.GONE);
        tel_personne.setVisibility(View.GONE);

        btn_radio_l1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
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
                }
            }
        });

        btn_radio_l2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked)
                {
                    btnDatePicker.setVisibility(View.VISIBLE);
                    btnTimePicker.setVisibility(View.VISIBLE);
                    txtDate.setVisibility(View.VISIBLE);
                    txtTime.setVisibility(View.VISIBLE);
                }
            }
        });

        btn_radio_moi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked)
                {
                    personne_conserner.setVisibility(View.GONE);
                    tel_personne.setVisibility(View.GONE);
                }
            }
        });

        btn_radio_autre.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked)
                {
                    personne_conserner.setVisibility(View.VISIBLE);
                    tel_personne.setVisibility(View.VISIBLE);
                }
            }
        });


        /*** on click of all buttons **/

        btn_maps.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(main, MapsActivity.class);
                startActivity(intent);
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
                PassOrderPlat current_order = new PassOrderPlat();

                current_order.total = OrderPlatRepository.getTotalOrder();

                if(btn_radio_l1.isChecked())
                {
                    current_order.mode_livraison = 1;
                    current_order.date_order = "vide";
                    current_order.time_order = "vide";
                }else {
                    current_order.mode_livraison = 2;
                    current_order.date_order = txtDate.getText().toString();
                    current_order.time_order = txtTime.getText().toString();
                }

                if(btn_radio_moi.isChecked())
                {
                    current_order.collecteur = 1;
                    current_order.nom_collecteur = "vide";
                    current_order.num_collecteur = "vide";
                }else {
                    current_order.collecteur = 2;
                    current_order.nom_collecteur = personne_conserner.getText().toString();
                    current_order.num_collecteur = tel_personne.getText().toString();
                }

                current_order.adresse = adresse_maps.getText().toString();
                current_order.note = note_client.getText().toString();

                current_order.id_client = ClientRepository.main_Client.id;
                current_order.situation = 1;


                lastPosition = PassOrderPlatRepository.list_passOrderPlat.size();
                PassOrderPlatRepository.list_passOrderPlat.add(current_order);

                /*** set order in server **/

                //localhost/livraison/json/setPassOrderPlat.php?total=120&mode_livraison=1&date_order=vide&time_order=vide&collecteur=2
                //&nom_collecteur=med&num_collecteur=0611336628&adresse=hayoujda&note=rien&id_client=1&situation=1


                String url_informations = constant.url_host+"json/setPassOrderPlat.php?";

                String total = "total="+current_order.total;
                String mode_livraison = "&mode_livraison="+current_order.mode_livraison;
                String date_order = "&date_order="+current_order.date_order;
                String time_order = "&time_order="+current_order.time_order;
                String collecteur = "&collecteur="+current_order.collecteur;
                String nom_collecteur = "&nom_collecteur="+current_order.nom_collecteur;
                String num_collecteur = "&num_collecteur="+current_order.num_collecteur;
                String adresse = "&adresse="+current_order.adresse;
                String note = "&note="+current_order.note ;
                String id_client = "&id_client="+current_order.id_client;
                String situation = "&situation="+current_order.situation;

                url_informations = url_informations+total+mode_livraison+date_order+time_order+collecteur+nom_collecteur+num_collecteur+adresse+note+id_client+situation;

                //Log.d("TAG_JSON_ORDER", "TO SEND "+ url_informations);

                JsonUrlPassOrderPlat jsonUrlPassOrderPlat = new JsonUrlPassOrderPlat(url_informations, main, lastPosition);

                Toast.makeText(main,"total : "+current_order.total, Toast.LENGTH_SHORT ).show();

                /** SET THE THREAD FOR SEND DAETAIL ORDER PLAT TO SERVER */
                //.....
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
                                    // set progress
                                    progressBar4.setProgress(progress);
                                }
                            });

                            try
                            {
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
                                // handler after sleep
                                progressBar4.setProgress(100);

                                /*** set detaille order in server **/

                                //localhost/livraison/json/setOrderPlat.php?id_passOrder=1&id_plat=1&quantity=5

                                //OrderPlat current_order = new OrderPlat();
                                //current_order.id_plat =

                                for (int i = 0; i < OrderPlatRepository.list_orderPlat.size(); i++)
                                {
                                    OrderPlatRepository.list_orderPlat.get(i).id_passOrder = PassOrderPlatRepository.list_passOrderPlat.get(lastPosition).id;

                                    String url_informations = constant.url_host+"json/setOrderPlat.php?";

                                    String id_passOrder = "id_passOrder="+OrderPlatRepository.list_orderPlat.get(i).id_passOrder;
                                    String id_plat = "&id_plat="+OrderPlatRepository.list_orderPlat.get(i).id_plat;
                                    String quantity = "&quantity="+OrderPlatRepository.list_orderPlat.get(i).quantity;


                                    url_informations = url_informations+id_passOrder+id_plat+quantity;

                                    Log.d("TAG_JSON_ORDER_DETAIL", "TO SEND "+ url_informations);

                                    JsonUrlOrderPlat jsonUrlOrderPlat = new JsonUrlOrderPlat(url_informations, main);

                                }

                                /** oppen new intent to detaills of command **/

                                Intent intent = new Intent(main, Commande_Activity.class);
                                startActivity(intent);

                                main.finish();

                            }
                        });
                    }
                }).start();

            }
        });

    }

    public static void getNewAdpter()
    {
        /** adapter  **/

        orderPlatAdapter = new OrderPlatAdapter(OrderPlatRepository.list_orderPlat);
        recyclerView.setAdapter(orderPlatAdapter);
    }
}