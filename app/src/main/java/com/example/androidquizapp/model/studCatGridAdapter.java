package com.example.androidquizapp.model;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.androidquizapp.R;
import com.example.androidquizapp.studSetActivity;

import java.util.List;
import java.util.Random;

public class studCatGridAdapter extends BaseAdapter
{
    List<String> studCatList;
    public studCatGridAdapter(List<String> studCatList)
    {
        this.studCatList = studCatList;
    }

    @Override
    public int getCount() {
        return studCatList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, final ViewGroup viewGroup)
    {
        View v;
        if(view==null)
        {
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.stud_cat_item_layout, viewGroup, false);
        }
        else
        {
            v = view;
        }

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(viewGroup.getContext(), studSetActivity.class);
                intent.putExtra("subjectName", studCatList.get(i));
                intent.putExtra("subjectID", i+1);
                viewGroup.getContext().startActivity(intent);
            }
        });

        ((TextView) v.findViewById(R.id.studCatName)).setText(studCatList.get(i));

        Random rand = new Random();
        int color = Color.argb(255, rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        v.setBackgroundColor(color);

        return v;
    }
}
