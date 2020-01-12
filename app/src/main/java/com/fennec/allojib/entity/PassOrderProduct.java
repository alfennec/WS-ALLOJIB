package com.fennec.allojib.entity;

public class PassOrderProduct {

    public int id;
    public double total;
    public int mode_livraison;
    public String date_order;
    public String time_order;
    public int collecteur;
    public String nom_collecteur;
    public String num_collecteur;
    public String adresse;
    public String note;
    public int id_client;
    public int situation;

    public PassOrderProduct()
    {

    }

    public PassOrderProduct(int id, int total, int mode_livraison, String date_order, String time_order, int collecteur, String nom_collecteur, String num_collecteur, String adresse, String note, int id_client, int situation) {
        this.id = id;
        this.total = total;
        this.mode_livraison = mode_livraison;
        this.date_order = date_order;
        this.time_order = time_order;
        this.collecteur = collecteur;
        this.nom_collecteur = nom_collecteur;
        this.num_collecteur = num_collecteur;
        this.adresse = adresse;
        this.note = note;
        this.id_client = id_client;
        this.situation = situation;
    }

    public PassOrderProduct(int total, int mode_livraison, String date_order, String time_order, int collecteur, String nom_collecteur, String num_collecteur, String adresse, String note, int id_client, int situation) {
        this.total = total;
        this.mode_livraison = mode_livraison;
        this.date_order = date_order;
        this.time_order = time_order;
        this.collecteur = collecteur;
        this.nom_collecteur = nom_collecteur;
        this.num_collecteur = num_collecteur;
        this.adresse = adresse;
        this.note = note;
        this.id_client = id_client;
        this.situation = situation;
    }
}

