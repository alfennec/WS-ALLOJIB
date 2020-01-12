package com.fennec.allojib.entity;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.ArrayList;

public class CategoryProduct implements Parent<Product> {

    public int id;
    public String intituler;
    public ArrayList<Product> productList;

    public CategoryProduct() {

    }

    public CategoryProduct(String intituler, ArrayList<Product> productList)
    {
        this.intituler = intituler;
        this.productList = productList;
    }

    public CategoryProduct(int id, String intituler)
    {
        this.id = id;
        this.intituler = intituler;
    }

    public CategoryProduct(int id, String intituler, ArrayList<Product> productList)
    {
        this.id = id;
        this.intituler = intituler;
        this.productList = productList;
    }

    @Override
    public ArrayList<Product> getChildList()
    {
        return productList;
    }

    @Override
    public boolean isInitiallyExpanded()
    {
        return false;
    }
}
