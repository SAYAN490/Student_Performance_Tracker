package com.example.androidquizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidquizapp.model.studQuestion;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static com.example.androidquizapp.studSetActivity.subID;

public class studQuestionActivity extends AppCompatActivity implements View.OnClickListener
{
    TextView studQuestionTextView, studQuestionCounter, studTimer;
    List<studQuestion> questionList;
    Button option1, option2, option3, option4;
    int quesNum, score, setNum;

    CountDownTimer countDownTimer;

    FirebaseFirestore firestore;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_question);

        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);

        studQuestionTextView = findViewById(R.id.studQuestionTextView);
        studQuestionCounter = findViewById(R.id.studQuestionCounterTextview);
        studTimer = findViewById(R.id.studTimerTextView);

        getQuestionList();
        score=0;
        firestore = FirebaseFirestore.getInstance();

        setNum = getIntent().getIntExtra("setNumber",1);
    }

    public void getQuestionList()
    {
        questionList = new ArrayList<>();

        /*questionList.add(new studQuestion("Q1","a","b","c","d",2));
        questionList.add(new studQuestion("Q2","a","b","c","d",3));
        questionList.add(new studQuestion("Q3","a","b","c","d",4));*/

        firestore.collection("QUIZ").document("sub"+String.valueOf(subID))
                .collection("set"+String.valueOf(setNum))
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task)
            {
                if(task.isSuccessful())
                {
                    QuerySnapshot qs = task.getResult();
                    for(QueryDocumentSnapshot doc : qs)
                    {
                        questionList.add(new studQuestion(doc.getString("question"),
                                doc.getString("a"),
                                doc.getString("b"),
                                doc.getString("c"),
                                doc.getString("d"),
                                Integer.valueOf(doc.getString("answer"))
                        ));
                    }
                    setQuestion();
                }
                else
                {
                    Toast.makeText(studQuestionActivity.this, "Error in fetching the questions", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void setQuestion()
    {
        studTimer.setText(String.valueOf(15));

        studQuestionTextView.setText(questionList.get(0).getQuestion());
        option1.setText(questionList.get(0).getOptionA());
        option2.setText(questionList.get(0).getOptionB());
        option3.setText(questionList.get(0).getOptionC());
        option4.setText(questionList.get(0).getOptionD());

        studQuestionCounter.setText(String.valueOf(1)+"/"+questionList.size());

        startTimer();

        quesNum = 0;
    }

    public void startTimer()
    {
        countDownTimer = new CountDownTimer(17000,1000)
        {
            @Override
            public void onTick(long l)
            {
                if(l<=15000)
                studTimer.setText(String.valueOf(l/1000));
            }

            @Override
            public void onFinish()
            {
                changeQuestion();
            }
        };
        countDownTimer.start();
    }


    @Override
    public void onClick(View view)
    {
        int selectedOption=0;
        switch(view.getId())
        {
            case R.id.option1: selectedOption=1;
                break;
            case R.id.option2: selectedOption=2;
                break;
            case R.id.option3: selectedOption=3;
                break;
            case R.id.option4: selectedOption=4;
                break;
        }

        countDownTimer.cancel();
        checkAnswer(selectedOption, view);
    }

    public void checkAnswer(int selectedOption, View v)
    {
        if(selectedOption == questionList.get(quesNum).getCorrectAns())
        {
            ((Button)v).setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            score++;
        }
        else
        {
            ((Button)v).setBackgroundTintList(ColorStateList.valueOf(Color.RED));

            switch (questionList.get(quesNum).getCorrectAns())
            {
                case 1: option1.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                break;

                case 2: option2.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                break;

                case 3: option3.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                break;

                case 4: option4.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                break;
            }
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run()
            {
                changeQuestion();
            }
        },2000);

    }

    public void changeQuestion()
    {
        quesNum++;
        if(quesNum <= questionList.size()-1)
        {
            playAnim(studQuestionTextView, 0,0);
            playAnim(option1, 0,1);
            playAnim(option2, 0,2);
            playAnim(option3, 0,3);
            playAnim(option4, 0,4);

            studQuestionCounter.setText(String.valueOf(quesNum+1)+"/"+questionList.size());
            studTimer.setText(String.valueOf(15));
            startTimer();
        }
        else
        {
            //display score
            Intent intent = new Intent(studQuestionActivity.this, studScoreActivity.class);
            intent.putExtra("score", String.valueOf(score+"/"+String.valueOf(questionList.size())));
            startActivity(intent);

            studQuestionActivity.this.finish();
        }
    }

    public void playAnim(final View view, final int value, final int viewNum)
    {
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator)
                    {
                        if(value==0)
                        {
                            switch(viewNum)
                            {
                                case 0: ((TextView)view).setText(questionList.get(quesNum).getQuestion());
                                    break;
                                case 1: ((Button)view).setText(questionList.get(quesNum).getOptionA());
                                    break;
                                case 2: ((Button)view).setText(questionList.get(quesNum).getOptionB());
                                    break;
                                case 3: ((Button)view).setText(questionList.get(quesNum).getOptionC());
                                    break;
                                case 4: ((Button)view).setText(questionList.get(quesNum).getOptionD());
                                    break;
                            }

                            if(viewNum!=0)
                            {
                                ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF9800")));
                            }

                            playAnim(view, 1, viewNum);
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        countDownTimer.cancel();

    }
}