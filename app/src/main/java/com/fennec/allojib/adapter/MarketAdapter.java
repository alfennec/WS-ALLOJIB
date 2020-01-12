package com.fennec.allojib.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fennec.allojib.R;
import com.fennec.allojib.config.constant;
import com.fennec.allojib.controller.Market_Activity;
import com.fennec.allojib.controller.Restaurant_Activity;
import com.fennec.allojib.entity.Market;
import com.fennec.allojib.entity.Restaurant;

import java.util.List;

public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.MyViewHolder> {

    public List<Market> list;
    public boolean showAdd = false;

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView intituler,specialiter,situation,prix;
        public ImageView image_restaurant,image_restaurant2;
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
            image_restaurant2 = (ImageView) view.findViewById(R.id.image_restaurant2);
        }
    }

    public MarketAdapter(List<Market> list)
    {
        this.list = list;
    }

    @Override
    public MarketAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant, parent, false);

        return new MarketAdapter.MyViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position)
    {
        if(position == getItemCount()-1 && showAdd)
            return 2;
        return 1;
    }

    @Override
    public void onBindViewHolder(final MarketAdapter.MyViewHolder holder, final int position)
    {
        final Market myMarket = list.get(position);
        holder.intituler.setText(myMarket.intituler);
        holder.specialiter.setText("");
        holder.prix.setText(myMarket.prix_transp+" MAD");

        if(myMarket.situation == 1)
        {
            holder.situation.setText("Ouvert");

        }else{
            holder.situation.setText("Fermer");
        }

        Glide.with(Market_Activity.main).load(constant.url_host+"/upload/market/"+myMarket.market_image).into(holder.image_restaurant);
        Glide.with(Market_Activity.main).load(constant.url_host+"/upload/asset/courbes3.png").centerCrop().into(holder.image_restaurant2);


        holder.parent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Market_Activity.to_newIntent(myMarket.id);
            }
        });

        Log.d("TAG_JSON", "onClick: ADAPTER RESTAURANT " + myMarket.intituler+" "+myMarket.prix_transp+" "+myMarket.situation+" "+myMarket.market_image);
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }
}
