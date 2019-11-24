package com.fennec.allojib.adapter;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.bumptech.glide.Glide;
import com.fennec.allojib.R;
import com.fennec.allojib.config.constant;
import com.fennec.allojib.controller.CategoryPlat_Activity;
import com.fennec.allojib.controller.Menu_Activity;
import com.fennec.allojib.entity.Plat;
import com.fennec.allojib.lab.Ingredient;

public class PlatViewHolder extends ChildViewHolder
{

    private TextView intituler_plat, prix_plat;
    public ImageView image_plat;
    public ImageButton add_plat;

    public PlatViewHolder(View itemView)
    {
        super(itemView);
        intituler_plat = itemView.findViewById(R.id.intituler_plat);
        prix_plat = itemView.findViewById(R.id.prix_plat);
        image_plat = itemView.findViewById(R.id.image_plat);
        add_plat = itemView.findViewById(R.id.add_plat);
    }

    public void bind(Plat plat)
    {
        intituler_plat.setText(plat.intituler);
        prix_plat.setText(""+plat.prix+" MAD");

        Glide.with(CategoryPlat_Activity.main).load(constant.url_host+"/upload/plat/"+plat.img).into(image_plat);


        add_plat.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                CategoryPlat_Activity.OnclickAdd();
            }
        });

    }
}
