package com.fennec.allojib.lab;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.List;

public class Recipe implements Parent<Ingredient> {

    public String name;
    // a recipe contains several ingredients
    private List<Ingredient> mIngredients;

    public Recipe(String name, List<Ingredient> ingredients)
    {
        mIngredients = ingredients;
        this.name=name;
    }

    @Override
    public List<Ingredient> getChildList()
    {
        return mIngredients;
    }

    @Override
    public boolean isInitiallyExpanded()
    {
        return false;
    }
}
