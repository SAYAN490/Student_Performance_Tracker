package com.example.androidquizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import com.example.androidquizapp.model.studSetsAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class studSetActivity extends AppCompatActivity
{
    private GridView studSetsGridView;
    FirebaseFirestore firestore;
    public static int subID;
    public String subName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_set);

        studSetsGridView = findViewById(R.id.studSetsGridView);

        firestore=FirebaseFirestore.getInstance();

        Toolbar toolbar = findViewById(R.id.studSetsToolbar);
        setSupportActionBar(toolbar);
        subName = getIntent().getStringExtra("subjectName");
        subID = getIntent().getIntExtra("subjectID",1);

        getSupportActionBar().setTitle(subName);

        loadSubjects();
    }

    public void loadSubjects()
    {
        firestore.collection("QUIZ").document("sub"+String.valueOf(subID)).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task)
            {
                if(task.isSuccessful())
                {
                    DocumentSnapshot doc = task.getResult();
                    if(doc.exists())
                    {
                        long setCount = (long)doc.get("sets");
                        studSetsAdapter adapter = new studSetsAdapter((int)setCount);
                        studSetsGridView.setAdapter(adapter);
                    }
                    else
                    {
                        Toast.makeText(studSetActivity.this, "No sets exists!", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(studSetActivity.this, "Error in loading the data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}