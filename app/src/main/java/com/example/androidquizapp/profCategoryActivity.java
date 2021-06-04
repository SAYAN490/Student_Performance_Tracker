package com.example.androidquizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.androidquizapp.model.Adapter;

import java.util.ArrayList;
import java.util.List;

public class profCategoryActivity extends AppCompatActivity
{
    RecyclerView profCategoryRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_category);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Subject");

        profCategoryRecyclerView = findViewById(R.id.profCategoryRecyclerView);

        List<String> cat_list = new ArrayList<>();
        cat_list.add("SEPA");
        cat_list.add("UNP");
        cat_list.add("DM");

        Adapter adapter = new Adapter(cat_list);
        profCategoryRecyclerView.setAdapter(adapter);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        profCategoryRecyclerView.setLayoutManager(layoutManager);

    }
}