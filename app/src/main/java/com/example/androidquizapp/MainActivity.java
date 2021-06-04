package com.example.androidquizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void professor(View view)
    {
        startActivity(new Intent(MainActivity.this, ProfessorActivity.class));
    }

    public void student(View view)
    {
        startActivity(new Intent(MainActivity.this, StudentActivity.class));
    }
}