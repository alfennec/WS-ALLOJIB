package com.fennec.allojib.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fennec.allojib.R;
import com.fennec.allojib.controller.Order_Plat_Activity;
import com.fennec.allojib.entity.OrderPlat;
import com.fennec.allojib.entity.Plat;
import com.fennec.allojib.repository.OrderPlatRepository;
import com.fennec.allojib.repository.PlatRepository;

import java.util.List;

public class CommandePlatAdapter extends RecyclerView.Adapter<CommandePlatAdapter.MyViewHolder> {

    public List<OrderPlat> list;
    public boolean showAdd = false;

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView plat_name,plat_prix;
        public ImageView image_restaurant;
        public View parent;

        public MyViewHolder(View view)
        {
            super(view);
            parent=view;
            plat_name = (TextView) view.findViewById(R.id.plat_name);
            plat_prix = (TextView) view.findViewById(R.id.plat_prix);
        }
    }

    public CommandePlatAdapter(List<OrderPlat> list)
    {
        this.list = list;
    }

    @Override
    public CommandePlatAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_commande_plat, parent, false);

        return new CommandePlatAdapter.MyViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position)
    {
        if(position == getItemCount()-1 && showAdd)
            return 2;
        return 1;
    }

    @Override
    public void onBindViewHolder(final CommandePlatAdapter.MyViewHolder holder, final int position)
    {
        final OrderPlat myOrderPlat = list.get(position);

        /** get donner form repository **/
        final Plat current_plat = PlatRepository.getPlatById(myOrderPlat.id_plat);

        holder.plat_name.setText(current_plat.intituler);
        holder.plat_prix.setText(""+current_plat.prix*myOrderPlat.quantity);

        holder.parent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // Restaurant_Activity.to_newIntent(myRestaurant.id);
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
