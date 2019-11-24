package com.fennec.allojib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.fennec.allojib.R;
import com.fennec.allojib.entity.CategoryPlat;
import com.fennec.allojib.entity.Plat;

import java.util.ArrayList;
import java.util.List;

public class ExCategoryPlatAdapter extends ExpandableRecyclerAdapter<CategoryPlat, Plat, CategoryPlatViewHolder, PlatViewHolder> {

    private LayoutInflater mInflater;

    public ExCategoryPlatAdapter(Context context, @NonNull ArrayList<CategoryPlat> categoryPlatList)
    {
        super(categoryPlatList);
        mInflater = LayoutInflater.from(context);
    }

    // onCreate ...
    @Override
    public CategoryPlatViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType)
    {
        View categoryPlatView = mInflater.inflate(R.layout.item_category_plat2, parentViewGroup, false);
        return new CategoryPlatViewHolder(categoryPlatView);
    }

    @Override
    public PlatViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType)
    {
        View platViewHolder = mInflater.inflate(R.layout.item_plat, childViewGroup, false);
        return new PlatViewHolder(platViewHolder);
    }

    @Override
    public void onBindParentViewHolder(@NonNull CategoryPlatViewHolder categoryPlatViewHolder, int parentPosition, @NonNull CategoryPlat categoryPlat)
    {
        categoryPlatViewHolder.bind(categoryPlat);
    }

    @Override
    public void onBindChildViewHolder(@NonNull PlatViewHolder platViewHolder, int parentPosition, int childPosition, @NonNull Plat plat)
    {
        platViewHolder.bind(plat);
    }
}
