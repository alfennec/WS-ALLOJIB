package com.fennec.allojib.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;

import com.fennec.allojib.R;
import com.fennec.allojib.adapter.CategoryPlatAdapter;
import com.fennec.allojib.adapter.OrderPlatAdapter;
import com.fennec.allojib.repository.CategoryPlatRepository;
import com.fennec.allojib.repository.OrderPlatRepository;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class Order_Plat_Activity extends AppCompatActivity {

    public static RecyclerView recyclerView;
    public static OrderPlatAdapter orderPlatAdapter;

    public static Order_Plat_Activity main;

    public Button btn_maps;

    public Button btnDatePicker, btnTimePicker;
    public EditText txtDate, txtTime;
    public TextInputEditText personne_conserner, tel_personne;

    private int mYear, mMonth, mDay, mHour, mMinute;

    public RadioButton btn_radio_l1,btn_radio_l2,btn_radio_moi,btn_radio_autre;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_plat);
        main = this;

        /** adapter  **/
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

    }

    public static void getNewAdpter()
    {
        /** adapter  **/

        orderPlatAdapter = new OrderPlatAdapter(OrderPlatRepository.list_orderPlat);
        recyclerView.setAdapter(orderPlatAdapter);
    }
}
