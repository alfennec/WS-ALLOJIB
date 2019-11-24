package com.fennec.allojib.repository;

import com.fennec.allojib.entity.Plat;

import java.util.ArrayList;

public class PlatRepository {

    public static ArrayList<Plat> list_plat = new ArrayList<>();

    public static Plat getPlatById(int id)
    {
        Plat current_plat  = new Plat();

        for (int i = 0; i < list_plat.size(); i++)
        {
            if(list_plat.get(i).id == id)
            {
                current_plat = list_plat.get(i);
                break;
            }
        }

        return current_plat;
    }

}
