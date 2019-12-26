package com.fennec.allojib.controller.ui.panier;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.fennec.allojib.R;
import com.fennec.allojib.adapter.OrderPlatAdapter;
import com.fennec.allojib.controller.MapsActivity;
import com.fennec.allojib.controller.Order_Plat_Activity;
import com.fennec.allojib.repository.OrderPlatRepository;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;


public class PanierFragment extends Fragment {

    private PanierViewModel panierViewModel;

    public static PanierFragment main;

    public static RecyclerView recyclerView;
    public static OrderPlatAdapter orderPlatAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        panierViewModel = ViewModelProviders.of(this).get(PanierViewModel.class);
        View root = inflater.inflate(R.layout.fragment_panier, container, false);

        main = this;


        panierViewModel.getText().observe(this, new Observer<String>()
        {
            @Override
            public void onChanged(@Nullable String s)
            {
                // textView.setText(s);
            }
        });


        /** adapter for test we have to improve our self for this app  **/
        recyclerView = (RecyclerView)root.findViewById(R.id.recyclerView);
        LinearLayoutManager lm = new LinearLayoutManager(main.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lm);

        orderPlatAdapter = new OrderPlatAdapter(OrderPlatRepository.WithoutIdorder());
        recyclerView.setAdapter(orderPlatAdapter);
        /** adapter for test we have to improve our self for this end  **/

        Button btn_commande = (Button) root.findViewById(R.id.btn_commande);

        btn_commande.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(main.getContext(), Order_Plat_Activity.class);
                startActivity(intent);
            }
        });

        return root;
    }

    /*public static void to_OtherActtivity(int id_order)
    {
        Intent intent = new Intent(main.getContext(), Commande_Activity.class);
        intent.putExtra("id_order", id_order);
        main.startActivity(intent);
    }*/
}
