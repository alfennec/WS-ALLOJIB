package com.fennec.allojib.controller.ui.product;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fennec.allojib.R;
import com.fennec.allojib.adapter.PassOrderPlatAdapter;
import com.fennec.allojib.adapter.PassOrderProductAdapter;
import com.fennec.allojib.config.JsonGetPassOrderPlat;
import com.fennec.allojib.config.JsonGetPassOrderProduct;
import com.fennec.allojib.config.constant;
import com.fennec.allojib.controller.Commande_Activity;
import com.fennec.allojib.controller.Commande_product;
import com.fennec.allojib.controller.Market_Activity;
import com.fennec.allojib.controller.Restaurant_Activity;
import com.fennec.allojib.controller.ui.commande.CommandeFragment;
import com.fennec.allojib.repository.ClientRepository;
import com.fennec.allojib.repository.OrderPlatRepository;
import com.fennec.allojib.repository.OrderProductRepository;
import com.fennec.allojib.repository.PassOrderPlatRepository;
import com.fennec.allojib.repository.PassOrderProductRepository;

public class ProductFragment extends Fragment {

    private ProductViewModel productViewModel;

    public static ProductFragment main;

    public static View root;

    public static RecyclerView recyclerView;
    public static PassOrderProductAdapter passOrderProductAdapter;

    public static ProgressDialog dialog;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);

        if(PassOrderProductRepository.list_passOrderProduct.size() == 0)
        {
            root = inflater.inflate(R.layout.not_commande, container, false);
            main = this;
            Button btn_rafraichir = (Button) root.findViewById(R.id.btn_rafraichir);

            btn_rafraichir.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(main.getContext(), Market_Activity.class);
                    main.startActivity(intent);
                }
            });

        }else {
            root = inflater.inflate(R.layout.fragment_product, container, false);

            main = this;


            /** clear data for update data **/

            OrderProductRepository.list_orderProduct.clear();
            PassOrderProductRepository.list_passOrderProduct.clear();

            /** get PASS ORDER **/
            //localhost/livraison/json/getPassOrderProduct.php?id_client=1

            String url_informations = constant.url_host+"json/getPassOrderProduct.php?";
            String id_client = "id_client="+ ClientRepository.main_Client.id;
            url_informations = url_informations+id_client;
            JsonGetPassOrderProduct jsonGetPassOrderProduct = new JsonGetPassOrderProduct(url_informations, inflater.getContext(), 2);

            dialog = ProgressDialog.show(inflater.getContext(), "", "Traitement de données. Veulliez attendre ...", true);
        }

        return root;
    }

    public static void onSucces()
    {
        /** adapter for test we have to improve our self for this app  **/
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        LinearLayoutManager lm = new LinearLayoutManager(main.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lm);

        passOrderProductAdapter = new PassOrderProductAdapter(PassOrderProductRepository.list_passOrderProduct);
        recyclerView.setAdapter(passOrderProductAdapter);
        /** adapter for test we have to improve our self for this end  **/

        dialog.dismiss();
    }

    public static void to_OtherActtivity(int id_order)
    {
        Intent intent = new Intent(main.getContext(), Commande_product.class);
        intent.putExtra("id_order", id_order);
        main.startActivity(intent);
    }
}