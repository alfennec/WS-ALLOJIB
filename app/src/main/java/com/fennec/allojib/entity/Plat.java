package com.fennec.allojib.entity;

public class Plat {

    public int id;
    public int id_rest;
    public int id_cat;
    public String intituler;
    public String contenue;
    public float prix;
    public int accom;
    public String img;

    public Plat() {
    }

    public Plat(int id_rest, int id_cat, String intituler, String contenue, float prix, int accom, String img) {
        this.id_rest = id_rest;
        this.id_cat = id_cat;
        this.intituler = intituler;
        this.contenue = contenue;
        this.prix = prix;
        this.accom = accom;
        this.img = img;
    }

    public Plat(int id, int id_rest, int id_cat, String intituler, String contenue, float prix, int accom, String img) {
        this.id = id;
        this.id_rest = id_rest;
        this.id_cat = id_cat;
        this.intituler = intituler;
        this.contenue = contenue;
        this.prix = prix;
        this.accom = accom;
        this.img = img;
    }
}
