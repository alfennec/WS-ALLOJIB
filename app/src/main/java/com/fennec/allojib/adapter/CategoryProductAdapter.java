package com.fennec.allojib.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.fennec.allojib.R;
import com.fennec.allojib.controller.Market_Activity;
import com.fennec.allojib.entity.CategoryProduct;

import java.util.List;

public class CategoryProductAdapter extends RecyclerView.Adapter<CategoryProductAdapter.MyViewHolder> {

    public List<CategoryProduct> list;
    public boolean showAdd = false;

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView intituler;
        public View parent;
        public RecyclerView recyclerView;

        public MyViewHolder(View view)
        {
            super(view);
            parent=view;
            intituler = (TextView) view.findViewById(R.id.intituler_category);
        }
    }

    public CategoryProductAdapter(List<CategoryProduct> list)
    {
        this.list = list;
    }

    @Override
    public CategoryProductAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView;
        if(viewType == 1)
        {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_plat3, parent, false);
        }else{
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_plat, parent, false);
        }

        return new CategoryProductAdapter.MyViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position)
    {
        /*if(position == getItemCount()-1 && showAdd)
            return 2;
        return 1;*/

        if (position == 0) return 1;
        else return 2;
    }

    @Override
    public void onBindViewHolder(final CategoryProductAdapter.MyViewHolder holder, final int position)
    {
        final CategoryProduct myCategoryProduct = list.get(position);
        holder.intituler.setText(myCategoryProduct.intituler);

        holder.parent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(Market_Activity.main,""+myCategoryProduct.intituler, Toast.LENGTH_SHORT).show();
                Market_Activity.update_adapter2(myCategoryProduct.id);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }
}