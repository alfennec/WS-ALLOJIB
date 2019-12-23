package com.fennec.allojib.repository;

import android.util.Log;

import com.fennec.allojib.entity.Plat;
import com.fennec.allojib.entity.Restaurant;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RestaurantRepository {

    public static ArrayList<Restaurant> list_restaurant = new ArrayList<>();

    public static ArrayList<Restaurant> RestaurantByCategory(int id)
    {
        ArrayList<Restaurant> current_restaurant = new ArrayList<>();
        ArrayList<Plat> list_plat = new ArrayList<>();

        for (int i = 0; i < PlatRepository.list_plat.size(); i++)
        {
            if(PlatRepository.list_plat.get(i).id_cat == id)
            {
                list_plat.add(PlatRepository.list_plat.get(i));
            }
        }

        for (int i = 0; i < list_restaurant.size(); i++)
        {
            for (int j = 0; j < list_plat.size(); j++)
            {
                if(RestaurantRepository.list_restaurant.get(i).id == list_plat.get(j).id_rest)
                {
                    current_restaurant.add(RestaurantRepository.list_restaurant.get(i));
                }
            }
        }

        /** delete the repeate element **/
        Set<Restaurant> set = new HashSet<>(current_restaurant);
        current_restaurant.clear();
        current_restaurant.addAll(set);

        return current_restaurant;
    }

    public static ArrayList<Restaurant> RestaurantByName(String name)
    {
        ArrayList<Restaurant> current_restaurant = new ArrayList<>();

        for (int i = 0; i < list_restaurant.size(); i++)
        {
            if(RestaurantRepository.list_restaurant.get(i).intituler.equals(name))
            {
                current_restaurant.add(RestaurantRepository.list_restaurant.get(i));
            }
        }

        return current_restaurant;
    }
}
