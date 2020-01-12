package com.fennec.allojib.repository;

import com.fennec.allojib.entity.Market;
import com.fennec.allojib.entity.Plat;
import com.fennec.allojib.entity.Product;
import com.fennec.allojib.entity.Restaurant;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MarketRepository {

    public static ArrayList<Market> list_market = new ArrayList<>();

    public static ArrayList<Market> MarketByCategory(int id)
    {
        ArrayList<Market> current_market = new ArrayList<>();
        ArrayList<Product> list_product = new ArrayList<>();

        for (int i = 0; i < ProductRepository.list_product.size(); i++)
        {
            if(ProductRepository.list_product.get(i).id_cat == id)
            {
                list_product.add(ProductRepository.list_product.get(i));
            }
        }

        for (int i = 0; i < list_market.size(); i++)
        {
            for (int j = 0; j < list_product.size(); j++)
            {
                if(ProductRepository.list_product.get(i).id == list_product.get(j).id_mark)
                {
                    current_market.add(MarketRepository.list_market.get(i));
                }
            }
        }

        /** delete the repeate element **/
        Set<Market> set = new HashSet<>(current_market);
        current_market.clear();
        current_market.addAll(set);

        return current_market;
    }

    public static ArrayList<Market> MarketByName(String name)
    {
        ArrayList<Market> current_market = new ArrayList<>();

        for (int i = 0; i < list_market.size(); i++)
        {
            if(MarketRepository.list_market.get(i).intituler.equals(name))
            {
                current_market.add(MarketRepository.list_market.get(i));
            }
        }

        return current_market;
    }
}

