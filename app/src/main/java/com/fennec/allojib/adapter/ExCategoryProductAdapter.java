package com.fennec.allojib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.fennec.allojib.R;
import com.fennec.allojib.entity.CategoryProduct;
import com.fennec.allojib.entity.Product;

import java.util.ArrayList;

public class ExCategoryProductAdapter extends ExpandableRecyclerAdapter<CategoryProduct, Product, CategoryProductViewHolder, ProductViewHolder> {

    private LayoutInflater mInflater;

    public ExCategoryProductAdapter(Context context, @NonNull ArrayList<CategoryProduct> categoryProductList)
    {
        super(categoryProductList);
        mInflater = LayoutInflater.from(context);
    }

    // onCreate ...
    @Override
    public CategoryProductViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType)
    {
        View categoryProductView = mInflater.inflate(R.layout.item_category_plat2, parentViewGroup, false);
        return new CategoryProductViewHolder(categoryProductView);
    }

    @Override
    public ProductViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType)
    {
        View productViewHolder = mInflater.inflate(R.layout.item_plat, childViewGroup, false);
        return new ProductViewHolder(productViewHolder);
    }

    @Override
    public void onBindParentViewHolder(@NonNull CategoryProductViewHolder categoryProductViewHolder, int parentPosition, @NonNull CategoryProduct categoryProduct)
    {
        categoryProductViewHolder.bind(categoryProduct);
    }

    @Override
    public void onBindChildViewHolder(@NonNull ProductViewHolder productViewHolder, int parentPosition, int childPosition, @NonNull Product product)
    {
        productViewHolder.bind(product);
    }
}
