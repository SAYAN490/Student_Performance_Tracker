package com.example.androidquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class studScoreActivity extends AppCompatActivity
{
    TextView studScore;
    Button scoreDone;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_score);

        studScore = findViewById(R.id.studScoreTextView);


        String score_str = getIntent().getStringExtra("score");
        studScore.setText(score_str);
    }

    public void doneButton(View view)
    {
        startActivity(new Intent(studScoreActivity.this, studCategoryActivity.class));
        finish();
    }
}