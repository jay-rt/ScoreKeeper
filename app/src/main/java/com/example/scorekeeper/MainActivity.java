package com.example.scorekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewHomeScore, textViewAwayScore;
    private Button decHomeButton, incHomeButton, decAwayButton, incAwayButton;
    private RadioGroup scoreChange;
    private RadioButton score1, score2, score3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewHomeScore = findViewById(R.id.textViewHomeScore);
        textViewAwayScore = findViewById(R.id.textViewAwayScore);
        decHomeButton = findViewById(R.id.decHomeButton);
        incHomeButton = findViewById(R.id.incHomeButton);
        decAwayButton = findViewById(R.id.decAwayButton);
        incAwayButton = findViewById(R.id.incAwayButton);
        scoreChange = findViewById(R.id.scoreChange);
        score1 = findViewById(R.id.score1);
        score2 = findViewById(R.id.score2);
        score3 = findViewById(R.id.score3);

        decHomeButton.setOnClickListener(this);
        incHomeButton.setOnClickListener(this);
        decAwayButton.setOnClickListener(this);
        incAwayButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        int score = 0;
        switch (scoreChange.getCheckedRadioButtonId()){
            case R.id.score1:
                score = Integer.parseInt(score1.getText().toString().replaceAll("[\\D]",""));
                break;
            case R.id.score2:
                score = Integer.parseInt(score2.getText().toString().replaceAll("[\\D]",""));
                break;
            case R.id.score3:
                score = Integer.parseInt(score3.getText().toString().replaceAll("[\\D]",""));
                break;
        }

        switch (v.getId()){
            case R.id.decHomeButton:
                textViewHomeScore.setText(Integer.toString(Integer.parseInt(textViewHomeScore.getText().toString())-score));
                break;

            case R.id.incHomeButton:
                textViewHomeScore.setText(Integer.toString(Integer.parseInt(textViewHomeScore.getText().toString())+score));
                break;

            case R.id.decAwayButton:
                textViewAwayScore.setText(Integer.toString(Integer.parseInt(textViewAwayScore.getText().toString())-score));
                break;

            case R.id.incAwayButton:
                textViewAwayScore.setText(Integer.toString(Integer.parseInt(textViewAwayScore.getText().toString())+score));
        }

    }
}
