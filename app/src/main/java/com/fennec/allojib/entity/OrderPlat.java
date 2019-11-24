package com.fennec.allojib.entity;

public class OrderPlat {

    public int id;
    public int id_passOrder;
    public int id_plat;
    public int quantity;

    public OrderPlat()
    {

    }

    public OrderPlat(int id_passOrder, int id_plat, int quantity)
    {
        this.id_plat = id_plat;
        this.quantity = quantity;
        this.id_passOrder = id_passOrder;
    }

    public OrderPlat(int id, int id_passOrder, int id_plat, int quantity)
    {
        this.id = id;
        this.id_plat = id_plat;
        this.quantity = quantity;
        this.id_passOrder = id_passOrder;
    }
}
