package com.fennec.allojib.entity;

public class Setting {

    public int id;
    public String app_tel;
    public String app_coursier;
    public String app_des;

    public Setting(){}

    public Setting(int id, String app_tel, String app_coursier, String app_des) {
        this.id = id;
        this.app_tel = app_tel;
        this.app_coursier = app_coursier;
        this.app_des = app_des;
    }
}
