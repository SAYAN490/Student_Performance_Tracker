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

public class StudentSignUpActivity extends AppCompatActivity
{
    private FirebaseAuth mAuth;
    EditText studSignupEmailID, studSignupPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up);

        mAuth = FirebaseAuth.getInstance();
        studSignupEmailID = findViewById(R.id.studSignupEmailID);
        studSignupPassword = findViewById(R.id.studSignupPassword);
    }
    public void studSignup(View view)
    {
        String sSignupEmail = studSignupEmailID.getText().toString();
        String sSignupPassword = studSignupPassword.getText().toString();

        mAuth.createUserWithEmailAndPassword(sSignupEmail, sSignupPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    Toast.makeText(StudentSignUpActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(StudentSignUpActivity.this, studCategoryActivity.class));
                    finish();
                }
                else
                {
                    Toast.makeText(StudentSignUpActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}