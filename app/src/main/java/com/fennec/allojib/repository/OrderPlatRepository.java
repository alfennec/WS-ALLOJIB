package com.fennec.allojib.repository;

import com.fennec.allojib.entity.OrderPlat;
import com.fennec.allojib.entity.PassOrderPlat;
import com.fennec.allojib.entity.Plat;

import java.util.ArrayList;

public class OrderPlatRepository {

    public static ArrayList<OrderPlat> list_orderPlat = new ArrayList<>();

    public static double getTotalOrder()
    {
        double Total_Order=0;

        Plat current_plat = new Plat();

        for (int i = 0; i < list_orderPlat.size(); i++)
        {
            current_plat = PlatRepository.getPlatById(list_orderPlat.get(i).id_plat);

            Total_Order += (current_plat.prix*list_orderPlat.get(i).quantity);
        }

        return Total_Order;
    }

    public static ArrayList<OrderPlat> WithIdorder(int idPassOrder)
    {
        ArrayList<OrderPlat> list_CurrentPlatOrder = new ArrayList<>();

        for (int i = 0; i < list_orderPlat.size(); i++)
        {
            if(list_orderPlat.get(i).id_passOrder == idPassOrder)
            {
                list_CurrentPlatOrder.add(list_orderPlat.get(i));
            }
        }
        return list_CurrentPlatOrder;
    }

    public static ArrayList<OrderPlat> WithoutIdorder()
    {
        ArrayList<OrderPlat> list_CurrentPlatOrder = new ArrayList<>();

        for (int i = 0; i < list_orderPlat.size(); i++)
        {
            if(list_orderPlat.get(i).id_passOrder == 0)
            {
                list_CurrentPlatOrder.add(list_orderPlat.get(i));
            }
        }
        return list_CurrentPlatOrder;
    }
}
