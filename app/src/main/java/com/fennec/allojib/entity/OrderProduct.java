package com.fennec.allojib.entity;

public class OrderProduct {

    public int id;
    public int id_passOrder;
    public int id_product;
    public int quantity;

    public OrderProduct()
    {

    }

    public OrderProduct(int id_product, int quantity)
    {
        this.id_product = id_product;
        this.quantity = quantity;
    }

    public OrderProduct(int id_passOrder, int id_product, int quantity)
    {
        this.id_product = id_product;
        this.quantity = quantity;
        this.id_passOrder = id_passOrder;
    }

    public OrderProduct(int id, int id_passOrder, int id_product, int quantity)
    {
        this.id = id;
        this.id_product = id_product;
        this.quantity = quantity;
        this.id_passOrder = id_passOrder;
    }

}
