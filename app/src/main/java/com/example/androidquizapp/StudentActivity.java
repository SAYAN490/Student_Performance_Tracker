package com.example.androidquizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class StudentActivity extends AppCompatActivity
{
    EditText studSigninEmailID, studSigninPassword;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        mAuth = FirebaseAuth.getInstance();
        studSigninEmailID = findViewById(R.id.studEmailIDEdittext);
        studSigninPassword = findViewById(R.id.studPasswordEdittext);
    }

    public void studSignUp(View view)
    {
        startActivity(new Intent(StudentActivity.this, StudentSignUpActivity.class));
    }

    public void studSignin(View view)
    {
        String sSigninEmail = studSigninEmailID.getText().toString();
        String sSigninPassword = studSigninPassword.getText().toString();

        mAuth.signInWithEmailAndPassword(sSigninEmail, sSigninPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(StudentActivity.this, "Welcome Student!!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(StudentActivity.this, studCategoryActivity.class));
                    finish();
                }
                else
                {
                    Toast.makeText(StudentActivity.this, "Failed to Sign-in", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}