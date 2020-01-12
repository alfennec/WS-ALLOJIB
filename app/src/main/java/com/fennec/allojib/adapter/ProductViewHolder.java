package com.fennec.allojib.adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.bumptech.glide.Glide;
import com.fennec.allojib.R;
import com.fennec.allojib.config.constant;
import com.fennec.allojib.controller.CategoryPlat_Activity;
import com.fennec.allojib.controller.CategoryProduct_Activity;
import com.fennec.allojib.entity.OrderPlat;
import com.fennec.allojib.entity.OrderProduct;
import com.fennec.allojib.entity.Plat;
import com.fennec.allojib.entity.Product;
import com.fennec.allojib.repository.OrderPlatRepository;
import com.fennec.allojib.repository.OrderProductRepository;

public class ProductViewHolder extends ChildViewHolder
{

    private TextView intituler_plat, prix_plat, contenu_plat;
    public ImageView image_plat;
    public ImageButton add_plat;

    public ProductViewHolder(View itemView)
    {
        super(itemView);
        intituler_plat = itemView.findViewById(R.id.intituler_plat);
        prix_plat = itemView.findViewById(R.id.prix_plat);
        contenu_plat = itemView.findViewById(R.id.contenu_plat);
        image_plat = itemView.findViewById(R.id.image_plat);
        add_plat = itemView.findViewById(R.id.add_plat);
    }

    public void bind(final Product product)
    {
        intituler_plat.setText(product.intituler);
        prix_plat.setText(""+product.prix+" MAD");
        contenu_plat.setText(product.des);

        Glide.with(CategoryProduct_Activity.main).load(constant.url_host+"/upload/produit/"+product.img).into(image_plat);


        add_plat.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("TAG_JSON", "onClick:  add product");

                Boolean isFound = false;

                for (int i = 0; i < OrderProductRepository.list_orderProduct.size(); i++)
                {
                    if(OrderProductRepository.list_orderProduct.get(i).id_product == product.id && OrderProductRepository.list_orderProduct.get(i).id_passOrder == 0)
                    {
                        OrderProductRepository.list_orderProduct.get(i).quantity++;
                        isFound = true;

                        CategoryProduct_Activity.OnclickAdd(product.intituler+" Ajouté au Panier ");
                    }
                }

                if(!isFound)
                {
                    OrderProduct Current_order = new OrderProduct(product.id,1);

                    OrderProductRepository.list_orderProduct.add(Current_order);

                    CategoryProduct_Activity.OnclickAdd(product.intituler+" Ajouté au Panier");

                    Log.d("TAG_JSON", "onClick: size of liste " + OrderProductRepository.list_orderProduct.size()+" "+product.intituler);
                }
            }
        });

    }
}
