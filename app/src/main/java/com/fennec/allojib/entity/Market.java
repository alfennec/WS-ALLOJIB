package com.fennec.allojib.entity;

public class Market {

    public int id;
    public String intituler;
    public int situation;
    public float prix_transp;
    public String market_image;

    public Market() {
    }

    public Market(int id, String intituler, int situation, float prix_transp, String market_image) {
        this.id = id;
        this.intituler = intituler;
        this.situation = situation;
        this.prix_transp = prix_transp;
        this.market_image = market_image;
    }
}
