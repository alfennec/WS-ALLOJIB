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
import com.fennec.allojib.controller.Order_Product_Activity;
import com.fennec.allojib.entity.OrderPlat;
import com.fennec.allojib.entity.OrderProduct;
import com.fennec.allojib.entity.Plat;
import com.fennec.allojib.entity.Product;
import com.fennec.allojib.repository.OrderPlatRepository;
import com.fennec.allojib.repository.OrderProductRepository;
import com.fennec.allojib.repository.PlatRepository;
import com.fennec.allojib.repository.ProductRepository;

import java.util.List;

public class OrderProductAdapter extends RecyclerView.Adapter<OrderProductAdapter.MyViewHolder> {

    public List<OrderProduct> list;
    public boolean showAdd = false;

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView plat_name,plat_prix;
        public ImageView image_restaurant;
        public View parent;
        public RecyclerView recyclerView;
        public EditText edit_quatity;
        public ImageButton btn_incrise,btn_dicrese;
        public ImageButton btn_delete;

        public MyViewHolder(View view)
        {
            super(view);
            parent=view;
            plat_name = (TextView) view.findViewById(R.id.plat_name);
            plat_prix = (TextView) view.findViewById(R.id.plat_prix);
            edit_quatity = (EditText) view.findViewById(R.id.edit_quatity);
            btn_incrise= (ImageButton) view.findViewById(R.id.btn_incrise);
            btn_dicrese = (ImageButton) view.findViewById(R.id.btn_dicrese);
            btn_delete = (ImageButton) view.findViewById(R.id.btn_delete);
        }
    }

    public OrderProductAdapter(List<OrderProduct> list)
    {
        this.list = list;
    }

    @Override
    public OrderProductAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_plat, parent, false);

        return new OrderProductAdapter.MyViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position)
    {
        if(position == getItemCount()-1 && showAdd)
            return 2;
        return 1;
    }

    @Override
    public void onBindViewHolder(final OrderProductAdapter.MyViewHolder holder, final int position)
    {
        final OrderProduct myOrderProduct = list.get(position);

        /** get donner form repository **/
        final Product current_product = ProductRepository.getPlatById(myOrderProduct.id_product);

        holder.plat_name.setText(current_product.intituler);
        holder.plat_prix.setText(""+current_product.prix*myOrderProduct.quantity);

        /** Qantity componenent **/
        holder.edit_quatity.setText(""+myOrderProduct.quantity);

        holder.btn_incrise.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int quatity = Integer.parseInt(holder.edit_quatity.getText().toString());
                holder.edit_quatity.setText(""+ ++quatity);

                Double prix = Double.valueOf(holder.plat_prix.getText().toString());
                prix = prix + current_product.prix;
                //Double.valueOf()

                holder.plat_prix.setText(""+prix);

                OrderProductRepository.list_orderProduct.get(position).quantity = quatity;
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
                    prix = prix - current_product.prix;
                    //Double.valueOf()

                    holder.plat_prix.setText(""+prix);

                    OrderProductRepository.list_orderProduct.get(position).quantity = quatity;
                }
            }
        });

        holder.btn_delete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                OrderProductRepository.list_orderProduct.remove(position);
                Order_Product_Activity.getNewAdpter();
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
