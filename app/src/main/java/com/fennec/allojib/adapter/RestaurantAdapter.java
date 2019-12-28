package com.fennec.allojib.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fennec.allojib.R;
import com.fennec.allojib.config.constant;
import com.fennec.allojib.controller.Menu_Activity;
import com.fennec.allojib.controller.Restaurant_Activity;
import com.fennec.allojib.entity.Restaurant;
import com.fennec.allojib.repository.RestaurantRepository;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.MyViewHolder> {

    public List<Restaurant> list;
    public boolean showAdd = false;

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView intituler,specialiter,situation,prix;
        public ImageView image_restaurant;
        public View parent;
        public RecyclerView recyclerView;

        public MyViewHolder(View view)
        {
            super(view);
            parent=view;
            intituler = (TextView) view.findViewById(R.id.intituler_restaurant);
            specialiter = (TextView) view.findViewById(R.id.specialiter);
            situation = (TextView) view.findViewById(R.id.situation);
            prix = (TextView) view.findViewById(R.id.prix);
            image_restaurant = (ImageView) view.findViewById(R.id.image_restaurant);

        }
    }

    public RestaurantAdapter(List<Restaurant> list)
    {
        this.list = list;
    }

    @Override
    public RestaurantAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant, parent, false);

        return new RestaurantAdapter.MyViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position)
    {
        if(position == getItemCount()-1 && showAdd)
            return 2;
        return 1;
    }

    @Override
    public void onBindViewHolder(final RestaurantAdapter.MyViewHolder holder, final int position)
    {
        final Restaurant myRestaurant = list.get(position);
        holder.intituler.setText(myRestaurant.intituler);
        holder.specialiter.setText("Spécialiter : "+myRestaurant.specialiter);
        holder.prix.setText(myRestaurant.prix_transp+" MAD");

        if(myRestaurant.situation == 1)
        {
            holder.situation.setText("Ouvert");

        }else{
            holder.situation.setText("Fermer");
        }

        Glide.with(Restaurant_Activity.main).load(constant.url_host+"/upload/restaurant/"+myRestaurant.restaurant_image).into(holder.image_restaurant);
        Log.d("TAG_GLIDE", "onBindViewHolder: "+constant.url_host+"/upload/restaurant/"+myRestaurant.restaurant_image);

        holder.parent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Restaurant_Activity.to_newIntent(myRestaurant.id);
            }
        });

        Log.d("TAG_JSON", "onClick: ADAPTER RESTAURANT " + myRestaurant.intituler+" "+myRestaurant.specialiter+" "+myRestaurant.prix_transp+" "+myRestaurant.situation+" "+myRestaurant.restaurant_image);
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }
}