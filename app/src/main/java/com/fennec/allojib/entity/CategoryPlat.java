package com.fennec.allojib.entity;

import com.bignerdranch.expandablerecyclerview.model.Parent;
import com.fennec.allojib.lab.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class CategoryPlat implements Parent<Plat> {

    public int id;
    public String intituler;
    public ArrayList<Plat> platList;

    public CategoryPlat() {

    }

    public CategoryPlat(String intituler, ArrayList<Plat> platList)
    {
        this.intituler = intituler;
        this.platList = platList;
    }

    public CategoryPlat(int id, String intituler)
    {
        this.id = id;
        this.intituler = intituler;
    }

    public CategoryPlat(int id, String intituler, ArrayList<Plat> platList)
    {
        this.id = id;
        this.intituler = intituler;
        this.platList = platList;
    }

    @Override
    public ArrayList<Plat> getChildList()
    {
        return platList;
    }

    @Override
    public boolean isInitiallyExpanded()
    {
        return false;
    }
}
