package com.fennec.allojib.lab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.fennec.allojib.R;

import java.util.List;

public class RecipeAdapter extends ExpandableRecyclerAdapter<Recipe, Ingredient, RecipeViewHolder, IngredientViewHolder> {

    private LayoutInflater mInflater;

    public RecipeAdapter(Context context, @NonNull List<Recipe> recipeList) {
        super(recipeList);
        mInflater = LayoutInflater.from(context);
    }

    // onCreate ...
    @Override
    public RecipeViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View recipeView = mInflater.inflate(R.layout.recipe_view, parentViewGroup, false);
        return new RecipeViewHolder(recipeView);
    }

    @Override
    public IngredientViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View ingredientView = mInflater.inflate(R.layout.ingredient_view, childViewGroup, false);
        return new IngredientViewHolder(ingredientView);
    }

    // onBind ...
    @Override
    public void onBindParentViewHolder(@NonNull RecipeViewHolder recipeViewHolder, int parentPosition, @NonNull Recipe recipe) {
        recipeViewHolder.bind(recipe);
    }

    @Override
    public void onBindChildViewHolder(@NonNull IngredientViewHolder ingredientViewHolder, int parentPosition, int childPosition, @NonNull Ingredient ingredient) {
        ingredientViewHolder.bind(ingredient);
    }
}
