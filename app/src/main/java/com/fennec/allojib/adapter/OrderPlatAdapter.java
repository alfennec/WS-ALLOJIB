package com.fennec.allojib.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class OrderPlatAdapter extends RecyclerView.Adapter<OrderPlatAdapter.MyViewHolder> {

    public List<OrderPlat> list;
    public boolean showAdd = false;

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView plat_name,plat_prix;
        public ImageView image_restaurant;
        public View parent;
        public RecyclerView recyclerView;
        public EditText edit_quatity;
        public Button btn_incrise,btn_dicrese;
        public ImageButton btn_delete;

        public MyViewHolder(View view)
        {
            super(view);
            parent=view;
            plat_name = (TextView) view.findViewById(R.id.plat_name);
            plat_prix = (TextView) view.findViewById(R.id.plat_prix);
            edit_quatity = (EditText) view.findViewById(R.id.edit_quatity);
            btn_incrise= (Button) view.findViewById(R.id.btn_incrise);
            btn_dicrese = (Button) view.findViewById(R.id.btn_dicrese);
            btn_delete = (ImageButton) view.findViewById(R.id.btn_delete);
        }
    }

    public OrderPlatAdapter(List<OrderPlat> list)
    {
        this.list = list;
    }

    @Override
    public OrderPlatAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_plat, parent, false);

        return new OrderPlatAdapter.MyViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position)
    {
        if(position == getItemCount()-1 && showAdd)
            return 2;
        return 1;
    }

    @Override
    public void onBindViewHolder(final OrderPlatAdapter.MyViewHolder holder, final int position)
    {
        final OrderPlat myOrderPlat = list.get(position);

        /** get donner form repository **/
        final Plat current_plat = PlatRepository.getPlatById(myOrderPlat.id_plat);

        holder.plat_name.setText(current_plat.intituler);
        holder.plat_prix.setText(""+current_plat.prix*myOrderPlat.quantity);

        /** Qantity componenent **/
        holder.edit_quatity.setText(""+myOrderPlat.quantity);

        holder.btn_incrise.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int quatity = Integer.parseInt(holder.edit_quatity.getText().toString());
                holder.edit_quatity.setText(""+ ++quatity);

                Double prix = Double.valueOf(holder.plat_prix.getText().toString());
                prix = prix + current_plat.prix;
                //Double.valueOf()

                holder.plat_prix.setText(""+prix);

                OrderPlatRepository.list_orderPlat.get(position).quantity = quatity;
            }
        });

        holder.btn_dicrese.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int quatity = Integer.parseInt(holder.edit_quatity.getText().toString());
                --quatity;

                if(quatity > 0)
                {
                    holder.edit_quatity.setText(""+ quatity);

                    Double prix = Double.valueOf(holder.plat_prix.getText().toString());
                    prix = prix - current_plat.prix;
                    //Double.valueOf()

                    holder.plat_prix.setText(""+prix);

                    OrderPlatRepository.list_orderPlat.get(position).quantity = quatity;
                }
            }
        });

        holder.btn_delete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                OrderPlatRepository.list_orderPlat.remove(position);
                Order_Plat_Activity.getNewAdpter();
            }
        });


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
