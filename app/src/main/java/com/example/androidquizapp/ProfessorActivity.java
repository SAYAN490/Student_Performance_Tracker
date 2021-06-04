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

public class ProfessorActivity extends AppCompatActivity
{
    EditText profSigninEmailID, profSigninPassword;
    String pSigninEmail, pSigninPswd;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);

        profSigninEmailID = findViewById(R.id.profEmailIDEditext);
        profSigninPassword = findViewById(R.id.profPasswordEdittext);

        mAuth = FirebaseAuth.getInstance();

        //mAuth.signInWithEmailAndPassword(pSigninEmail, pSigninPswd)
    }

    public void profSignUp(View view)
    {
        startActivity(new Intent(ProfessorActivity.this, ProfessorSignUpActivity.class));
    }

    public void profSignin(View view)
    {
        pSigninEmail = profSigninEmailID.getText().toString();
        pSigninPswd = profSigninPassword.getText().toString();

        mAuth.signInWithEmailAndPassword(pSigninEmail, pSigninPswd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    Toast.makeText(ProfessorActivity.this, "Welcome Professor!!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ProfessorActivity.this, profCategoryActivity.class));
                    finish();
                }
                else
                {
                    Toast.makeText(ProfessorActivity.this, "Failed to Sign-in", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}