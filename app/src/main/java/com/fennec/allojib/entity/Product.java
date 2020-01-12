package com.fennec.allojib.entity;

public class Product {

    public int id;
    public int id_mark;
    public int id_cat;
    public String intituler;
    public String des;
    public float prix;
    public String img;

    public Product() {
    }

    public Product(int id, int id_mark, int id_cat, String intituler, String des, float prix, String img) {
        this.id = id;
        this.id_mark = id_mark;
        this.id_cat = id_cat;
        this.intituler = intituler;
        this.des = des;
        this.prix = prix;
        this.img = img;
    }
}
