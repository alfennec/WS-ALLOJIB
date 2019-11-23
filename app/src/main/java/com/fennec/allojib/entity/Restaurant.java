package com.fennec.allojib.entity;

import java.util.List;

public class Restaurant {

    public int id;
    public String intituler;
    public String specialiter;
    public int situation;
    public float prix_transp;
    public String restaurant_image;

    public Restaurant() {
    }

    public Restaurant(String intituler, String specialiter, int situation, float prix_transp, String restaurant_image) {
        this.intituler = intituler;
        this.specialiter = specialiter;
        this.situation = situation;
        this.prix_transp = prix_transp;
        this.restaurant_image = restaurant_image;
}

    public Restaurant(int id, String intituler, String specialiter, int situation, float prix_transp, String restaurant_image, List<Plat> list_plat) {
        this.id = id;
        this.intituler = intituler;
        this.specialiter = specialiter;
        this.situation = situation;
        this.prix_transp = prix_transp;
        this.restaurant_image = restaurant_image;
    }
}
