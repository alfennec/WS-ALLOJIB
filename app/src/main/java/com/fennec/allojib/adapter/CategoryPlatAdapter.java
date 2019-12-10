package com.fennec.allojib.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.fennec.allojib.R;
import com.fennec.allojib.controller.CategoryPlat_Activity;
import com.fennec.allojib.controller.Menu_Activity;
import com.fennec.allojib.controller.Restaurant_Activity;
import com.fennec.allojib.entity.CategoryPlat;

import java.util.List;

public class CategoryPlatAdapter extends RecyclerView.Adapter<CategoryPlatAdapter.MyViewHolder> {

    public List<CategoryPlat> list;
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

    public CategoryPlatAdapter(List<CategoryPlat> list)
    {
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_plat, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position)
    {
        if(position == getItemCount()-1 && showAdd)
            return 2;
        return 1;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position)
    {
        final CategoryPlat myCategoryPlat = list.get(position);
        holder.intituler.setText(myCategoryPlat.intituler);

        if(position == 0)
        {
            holder.intituler.setBackgroundColor(ContextCompat.getColor(CategoryPlat_Activity.main, R.color.colorPrimaryDark));
            holder.intituler.setTextColor(Color.argb(255,255,255,255));
        }

        holder.parent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(Menu_Activity.main,myCategoryPlat.id+" "+myCategoryPlat.intituler, Toast.LENGTH_SHORT).show();
                Restaurant_Activity.update_adapter2(myCategoryPlat.id);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }
}