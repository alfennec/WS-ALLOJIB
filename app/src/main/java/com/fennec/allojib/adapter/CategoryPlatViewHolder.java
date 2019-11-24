package com.fennec.allojib.adapter;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.fennec.allojib.R;
import com.fennec.allojib.entity.CategoryPlat;
import com.fennec.allojib.lab.Recipe;

public class CategoryPlatViewHolder extends ParentViewHolder {

    private TextView intituler_category;

    public CategoryPlatViewHolder(View itemView)
    {
        super(itemView);
        intituler_category = itemView.findViewById(R.id.intituler_category);
    }

    public void bind(CategoryPlat categoryPlat)
    {
        intituler_category.setText(categoryPlat.intituler);
    }
}

