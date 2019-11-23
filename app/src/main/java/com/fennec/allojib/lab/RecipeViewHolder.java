package com.fennec.allojib.lab;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.fennec.allojib.R;

public class RecipeViewHolder extends ParentViewHolder {

        private TextView mRecipeTextView;

    public RecipeViewHolder(View itemView) {
            super(itemView);
            mRecipeTextView = itemView.findViewById(R.id.recipe_textview);
        }

        public void bind(Recipe recipe) {
            mRecipeTextView.setText(recipe.name);
        }
    }

    class IngredientViewHolder extends ChildViewHolder
    {

        private TextView mIngredientTextView;

        public IngredientViewHolder(View itemView) {
            super(itemView);
            mIngredientTextView = itemView.findViewById(R.id.ingredient_textview);
        }

        public void bind(Ingredient ingredient) {
            mIngredientTextView.setText(ingredient.nom);
        }
    }
