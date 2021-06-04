package com.example.androidquizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;
import com.example.androidquizapp.model.studCatGridAdapter;
import java.util.ArrayList;
import java.util.List;

import static com.example.androidquizapp.Splash.studCatList;

public class studCategoryActivity extends AppCompatActivity
{
    private GridView catGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_category);

        Toolbar toolbar = findViewById(R.id.studCatToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Subjects");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        catGridView = findViewById(R.id.catGridView);


        studCatGridAdapter adapter = new studCatGridAdapter(studCatList);
        catGridView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(item.getItemId()==android.R.id.home)
        {
            studCategoryActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}