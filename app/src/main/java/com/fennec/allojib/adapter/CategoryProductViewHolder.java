package com.fennec.allojib.adapter;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.fennec.allojib.R;
import com.fennec.allojib.entity.CategoryProduct;

public class CategoryProductViewHolder extends ParentViewHolder {

    private TextView intituler_category;

    public CategoryProductViewHolder(View itemView)
    {
        super(itemView);
        intituler_category = itemView.findViewById(R.id.intituler_category);
    }

    public void bind(CategoryProduct categoryProduct)
    {
        intituler_category.setText(categoryProduct.intituler);
    }
}
