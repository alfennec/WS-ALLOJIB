package com.fennec.allojib.entity;

public class PassOrderPlat {

    public int id;
    public int total;
    public String date_order;
    public int id_client;
    public int situation;

    public PassOrderPlat()
    {

    }

    public PassOrderPlat(int id_client, String date_order, int total, int situation)
    {
        this.total = total;
        this.date_order = date_order;
        this.id_client = id_client;
        this.situation = situation;
    }

    public PassOrderPlat(int id, int id_client, String date_order, int total, int situation)
    {
        this.id = id;
        this.total = total;
        this.date_order = date_order;
        this.id_client = id_client;
        this.situation = situation;
    }
}
