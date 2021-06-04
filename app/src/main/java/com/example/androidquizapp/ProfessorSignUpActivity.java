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

public class ProfessorSignUpActivity extends AppCompatActivity
{
    private FirebaseAuth mAuth;
    EditText profSignupEmail, profSignupPswd;
    String pSignupEmail, pSignupPswd;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_sign_up);

        profSignupEmail = findViewById(R.id.profSignupEmailEdittext);
        profSignupPswd = findViewById(R.id.profSignupPasswordEdittext);

        mAuth = FirebaseAuth.getInstance();
    }

    public void profSignup(View view)
    {
        pSignupEmail = profSignupEmail.getText().toString();
        pSignupPswd = profSignupPswd.getText().toString();

        mAuth.createUserWithEmailAndPassword(pSignupEmail, pSignupPswd).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(ProfessorSignUpActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ProfessorSignUpActivity.this, profCategoryActivity.class));
                            finish();
                        }
                        else
                        {
                            Toast.makeText(ProfessorSignUpActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                }
        });
    }

}