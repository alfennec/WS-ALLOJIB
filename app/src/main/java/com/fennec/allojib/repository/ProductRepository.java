package com.fennec.allojib.repository;

import com.fennec.allojib.entity.Plat;
import com.fennec.allojib.entity.Product;

import java.util.ArrayList;

public class ProductRepository {

    public static ArrayList<Product> list_product = new ArrayList<>();

    public static Product getPlatById(int id)
    {
        Product current_product  = new Product();

        for (int i = 0; i < list_product.size(); i++)
        {
            if(list_product.get(i).id == id)
            {
                current_product = list_product.get(i);
                break;
            }
        }

        return current_product;
    }
}
