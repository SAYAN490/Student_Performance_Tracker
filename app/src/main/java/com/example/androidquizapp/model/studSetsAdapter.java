package com.example.androidquizapp.model;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.androidquizapp.R;
import com.example.androidquizapp.studQuestionActivity;

public class studSetsAdapter extends BaseAdapter
{
    int noOfSets;

    public studSetsAdapter(int noOfSets)
    {
        this.noOfSets = noOfSets;
    }

    @Override
    public int getCount() {
        return noOfSets;
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
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.stud_sets_item_layout,viewGroup,false);
        }
        else
        {
            v=view;
        }

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(viewGroup.getContext(), studQuestionActivity.class);
                intent.putExtra("setNumber", i+1);
                viewGroup.getContext().startActivity(intent);
            }
        });

        ((TextView)v.findViewById(R.id.studSetItemTextView)).setText(String.valueOf(i+1));
        return v;
    }


}
