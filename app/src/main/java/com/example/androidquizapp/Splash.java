package com.example.androidquizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class Splash extends AppCompatActivity
{
    public static List<String> studCatList = new ArrayList<>();
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        firestore = FirebaseFirestore.getInstance();

        Toast.makeText(this, "Welcome User!!", Toast.LENGTH_LONG).show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run()
            {

                loadData();
            }
        },2000);
    }

    public void loadData()
    {
        studCatList.clear();
        firestore.collection("QUIZ").document("subjects").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task)
            {
                if(task.isSuccessful())
                {
                    DocumentSnapshot doc = task.getResult();
                    if(doc.exists())
                    {
                        long count = (long)doc.get("count");
                        for(int i=1;i<=count;i++)
                        {
                            String subName = doc.getString("sub"+String.valueOf(i));
                            studCatList.add(subName);
                        }
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                }
                else
                {
                    Toast.makeText(Splash.this, "Error in loading the data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}