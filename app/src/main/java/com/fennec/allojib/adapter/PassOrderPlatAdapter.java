package com.fennec.allojib.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.fennec.allojib.R;
import com.fennec.allojib.controller.Commande_Activity;
import com.fennec.allojib.controller.ui.commande.CommandeFragment;
import com.fennec.allojib.entity.PassOrderPlat;


import java.util.List;

public class PassOrderPlatAdapter extends RecyclerView.Adapter<PassOrderPlatAdapter.MyViewHolder> {

    public List<PassOrderPlat> list;
    public boolean showAdd = false;

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView tv_commande,tv_prix,tv_time,tv_statu;
        public View parent;
        public RecyclerView recyclerView;

        public MyViewHolder(View view)
        {
            super(view);
            parent=view;
            tv_commande = (TextView) view.findViewById(R.id.tv_commande);
            tv_prix = (TextView) view.findViewById(R.id.tv_prix);
            tv_time = (TextView) view.findViewById(R.id.tv_time);
            tv_statu = (TextView) view.findViewById(R.id.tv_statu);


        }
    }

    public PassOrderPlatAdapter(List<PassOrderPlat> list)
    {
        this.list = list;
    }

    @Override
    public PassOrderPlatAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pass_order_plat, parent, false);

        return new PassOrderPlatAdapter.MyViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position)
    {
        if(position == getItemCount()-1 && showAdd)
            return 2;
        return 1;
    }

    @Override
    public void onBindViewHolder(final PassOrderPlatAdapter.MyViewHolder holder, final int position)
    {
        final PassOrderPlat myPassOrder = list.get(position);
        holder.tv_commande.setText("Commande N°: "+myPassOrder.id);
        holder.tv_prix.setText(myPassOrder.total+" MAD");
        holder.tv_time.setText(myPassOrder.date_order+" "+myPassOrder.time_order);

        switch (myPassOrder.situation)
        {
            case 1 : holder.tv_statu.setText("On attente"); break;

            case 2 : holder.tv_statu.setText("Confirmée"); break;

            case 3 : holder.tv_statu.setText("Lancée"); break;

            case 4 : holder.tv_statu.setText("Sérvi"); break;

            case 5 : holder.tv_statu.setText("Annulée"); break;

            default : holder.tv_statu.setText("Annulée"); break;
        }

        holder.parent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Restaurant_Activity.to_newIntent(myRestaurant.id);
                CommandeFragment.to_OtherActtivity(myPassOrder.id);
            }
        });

        //Log.d("TAG_JSON", "onClick: ADAPTER RESTAURANT " + myRestaurant.intituler+" "+myRestaurant.specialiter+" "+myRestaurant.prix_transp+" "+myRestaurant.situation+" "+myRestaurant.restaurant_image);
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }
}
