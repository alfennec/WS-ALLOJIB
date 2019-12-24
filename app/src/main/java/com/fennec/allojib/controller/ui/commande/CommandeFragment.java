package com.fennec.allojib.controller.ui.commande;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fennec.allojib.R;
import com.fennec.allojib.adapter.CommandePlatAdapter;
import com.fennec.allojib.adapter.PassOrderPlatAdapter;
import com.fennec.allojib.repository.OrderPlatRepository;
import com.fennec.allojib.repository.PassOrderPlatRepository;

public class CommandeFragment extends Fragment {

    private CommandeViewModel commandeViewModel;


    public static RecyclerView recyclerView;
    public static PassOrderPlatAdapter passOrderPlatAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        commandeViewModel = ViewModelProviders.of(this).get(CommandeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_commande, container, false);

        //final TextView textView = root.findViewById(R.id.text_commande);

        /** adapter for test we have to improve our self for this app  **/
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        LinearLayoutManager lm = new LinearLayoutManager(inflater.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lm);

        passOrderPlatAdapter = new PassOrderPlatAdapter(PassOrderPlatRepository.list_passOrderPlat);
        recyclerView.setAdapter(passOrderPlatAdapter);
        /** adapter for test we have to improve our self for this end  **/

        commandeViewModel.getText().observe(this, new Observer<String>()
        {
            @Override
            public void onChanged(@Nullable String s)
            {
               // textView.setText(s);
            }
        });
        return root;
    }
}