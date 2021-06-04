package com.example.androidquizapp.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidquizapp.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>
{
    private List<String> cat_list;
    public Adapter(List<String> cat_list)
    {
        this.cat_list = cat_list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prof_cat_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        String title = cat_list.get(position);
        holder.setData(title);

    }

    @Override
    public int getItemCount() {
        return cat_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView profCatName;
        private ImageView profCatDelete;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            profCatName = itemView.findViewById(R.id.profCatName);
            profCatDelete = itemView.findViewById(R.id.profDeleteCatButton);
        }

        public void setData(String title)
        {
            profCatName.setText(title);
            profCatDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }
    }


}
