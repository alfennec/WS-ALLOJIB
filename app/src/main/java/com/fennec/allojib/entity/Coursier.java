package com.fennec.allojib.entity;

public class Coursier {

    public int id;
    public int id_client;
    public String adr_col;
    public String adr_liv;
    public String detail;
    public String date_col;
    public String heure_col;
    public String tel;
    public int situation;


    public Coursier() {
    }

    public Coursier(int id, int id_client, String adr_col, String adr_liv, String detail, String date_col, String heure_col, String tel, int situation) {
        this.id = id;
        this.id_client = id_client;
        this.adr_col = adr_col;
        this.adr_liv = adr_liv;
        this.detail = detail;
        this.date_col = date_col;
        this.heure_col = heure_col;
        this.tel = tel;
        this.situation = situation;
    }
}
