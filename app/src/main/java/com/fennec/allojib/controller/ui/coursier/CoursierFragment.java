package com.fennec.allojib.controller.ui.coursier;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fennec.allojib.R;
import com.fennec.allojib.adapter.CoursierAdapter;
import com.fennec.allojib.adapter.PassOrderPlatAdapter;
import com.fennec.allojib.config.JsonGetCoursier;
import com.fennec.allojib.config.JsonGetPassOrderPlat;
import com.fennec.allojib.config.constant;
import com.fennec.allojib.controller.Commande_Activity;
import com.fennec.allojib.controller.Commande_coursier;
import com.fennec.allojib.controller.ui.commande.CommandeFragment;
import com.fennec.allojib.repository.ClientRepository;
import com.fennec.allojib.repository.CoursierRepository;
import com.fennec.allojib.repository.OrderPlatRepository;
import com.fennec.allojib.repository.PassOrderPlatRepository;


public class CoursierFragment extends Fragment {

    private CoursierViewModel coursierViewModel;

    public static CoursierFragment main;

    public static View root;

    public static RecyclerView recyclerView;

    public static CoursierAdapter coursierAdapter;

    public static ProgressDialog dialog;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        coursierViewModel =  ViewModelProviders.of(this).get(CoursierViewModel.class);
        root = inflater.inflate(R.layout.fragment_coursier, container, false);

        main = this;

        //final TextView textView = root.findViewById(R.id.text_commande);

        /** clear data for update data **/
        CoursierRepository.list_coursier.clear();


        /** get PASS ORDER **/
        //localhost/livraison/json/getPassOrderPlat.php?id_client=1

        String url_informations = constant.url_host+"json/getCoursier.php?";
        String id_client = "id_client="+ ClientRepository.main_Client.id;
        url_informations = url_informations+id_client;

        JsonGetCoursier jsonGetCoursier = new JsonGetCoursier(url_informations, inflater.getContext());

        dialog = ProgressDialog.show(inflater.getContext(), "", "Traitement de données. Veulliez attendre ...", true);

        return root;
    }

    public static void onSucces()
    {
        /** adapter for test we have to improve our self for this app  **/
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        LinearLayoutManager lm = new LinearLayoutManager(main.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lm);

        coursierAdapter = new CoursierAdapter(CoursierRepository.list_coursier);
        recyclerView.setAdapter(coursierAdapter);
        /** adapter for test we have to improve our self for this end  **/

        dialog.dismiss();
    }

    public static void to_OtherActtivity(int id_order)
    {
        Intent intent = new Intent(main.getContext(), Commande_coursier.class);
        intent.putExtra("id_coursier", id_order);
        main.startActivity(intent);
    }
}