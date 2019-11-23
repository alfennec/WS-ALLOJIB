package com.fennec.allojib.entity;

import java.util.List;

public class CategoryPlat {

    public int id;
    public String intituler;

    public CategoryPlat() {
    }

    public CategoryPlat(String intituler)
    {
        this.intituler = intituler;
    }

    public CategoryPlat(int id, String intituler)
    {
        this.id = id;
        this.intituler = intituler;
    }
}
