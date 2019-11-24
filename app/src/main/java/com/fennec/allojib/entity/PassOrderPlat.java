package com.fennec.allojib.entity;

public class PassOrderPlat {

    public int id;
    public int total;
    public String date_order;
    public int id_client;

    public PassOrderPlat(int total, String date_order, int id_client)
    {
        this.total = total;
        this.date_order = date_order;
        this.id_client = id_client;
    }

    public PassOrderPlat(int id, int total, String date_order, int id_client)
    {
        this.id = id;
        this.total = total;
        this.date_order = date_order;
        this.id_client = id_client;
    }
}
