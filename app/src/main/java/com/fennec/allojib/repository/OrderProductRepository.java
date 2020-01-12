package com.fennec.allojib.repository;

import com.fennec.allojib.entity.OrderPlat;
import com.fennec.allojib.entity.OrderProduct;
import com.fennec.allojib.entity.Plat;
import com.fennec.allojib.entity.Product;

import java.util.ArrayList;

public class OrderProductRepository {

    public static ArrayList<OrderProduct> list_orderProduct = new ArrayList<>();

    public static double getTotalOrder()
    {
        double Total_Order=0;

        Product current_product = new Product();

        for (int i = 0; i < list_orderProduct.size(); i++)
        {
            if(list_orderProduct.get(i).id_passOrder == 0)
            {
                current_product = ProductRepository.getPlatById(list_orderProduct.get(i).id_product);

                Total_Order += (current_product.prix*list_orderProduct.get(i).quantity);
            }
        }

        return Total_Order;
    }

    public static ArrayList<OrderProduct> WithIdorder(int idPassOrder)
    {
        ArrayList<OrderProduct> list_CurrentProductOrder = new ArrayList<>();

        for (int i = 0; i < list_orderProduct.size(); i++)
        {
            if(list_orderProduct.get(i).id_passOrder == idPassOrder)
            {
                list_CurrentProductOrder.add(list_orderProduct.get(i));
            }
        }
        return list_CurrentProductOrder;
    }

    public static ArrayList<OrderProduct> WithoutIdorder()
    {
        ArrayList<OrderProduct> list_CurrentProductOrder = new ArrayList<>();

        for (int i = 0; i < list_orderProduct.size(); i++)
        {
            if(list_orderProduct.get(i).id_passOrder == 0)
            {
                list_CurrentProductOrder.add(list_orderProduct.get(i));
            }
        }
        return list_CurrentProductOrder;
    }
}
