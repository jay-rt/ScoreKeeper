package com.example.scorekeeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewHomeScore, textViewAwayScore;
    private Button decHomeButton, incHomeButton, decAwayButton, incAwayButton;
    private RadioGroup scoreChange;
//    private RadioButton score1, score2, score3;
    private int score = 0;
    private int homeScore = 0, awayScore = 0;
    private SharedPreferences savedValues;
    private boolean isSaveChecked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        savedValues = getSharedPreferences("saveValue", MODE_PRIVATE);
        savedValues = PreferenceManager.getDefaultSharedPreferences(this);

        textViewHomeScore = findViewById(R.id.textViewHomeScore);
        textViewAwayScore = findViewById(R.id.textViewAwayScore);
        decHomeButton = findViewById(R.id.decHomeButton);
        incHomeButton = findViewById(R.id.incHomeButton);
        decAwayButton = findViewById(R.id.decAwayButton);
        incAwayButton = findViewById(R.id.incAwayButton);
        scoreChange = findViewById(R.id.scoreChange);
//        score1 = findViewById(R.id.score1);
//        score2 = findViewById(R.id.score2);
//        score3 = findViewById(R.id.score3);

        decHomeButton.setOnClickListener(this);
        incHomeButton.setOnClickListener(this);
        decAwayButton.setOnClickListener(this);
        incAwayButton.setOnClickListener(this);

        isSaveChecked = savedValues.getBoolean("pref_save",false);

        if(isSaveChecked) {
            homeScore= savedValues.getInt("HomeScore",0);
            awayScore = savedValues.getInt("AwayScore",0);
            score = savedValues.getInt("Score",2);
            display();
//            textViewHomeScore.setText(Integer.toString(homeScore));
//            textViewAwayScore.setText(Integer.toString(awayScore));
        }else{
            SharedPreferences.Editor editor = savedValues.edit();
//            homeScore = Integer.parseInt(textViewHomeScore.getText().toString());
//            awayScore = Integer.parseInt(textViewAwayScore.getText().toString());
            editor.putInt("HomeScore", 0);
            editor.putInt("AwayScore", 0);
            editor.putInt("Score", 2);
            editor.putBoolean("pref_save",savedValues.getBoolean("pref_save",false));
            editor.apply();
        }

        if(score == 1){
            scoreChange.check(R.id.score1);
        }else if(score == 2){
            scoreChange.check(R.id.score2);
        }else if(score == 3){
            scoreChange.check(R.id.score3);
        }

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(
                R.menu.activity_score_keeper, menu
        );
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_settings:
                Intent myIntent = new Intent(getApplicationContext(), SettingsActivity.class);
                myIntent.putExtra("title","scoreKeeperSetting");
                startActivity(myIntent);
                break;
            case R.id.menu_about:
                Toast.makeText(this, "Written by Jay", Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public void onClick(View v) {

        switch (scoreChange.getCheckedRadioButtonId()){
            case R.id.score1:
//                score = Integer.parseInt(score1.getText().toString().replaceAll("[\\D]",""));
                score = 1;
                scoreChangeSave(score);
                break;
            case R.id.score2:
                score = 2;
                scoreChangeSave(score);
                break;
            case R.id.score3:
                score = 3;
                scoreChangeSave(score);
                break;
        }

        switch (v.getId()){
            case R.id.decHomeButton:
                homeScore = Integer.parseInt(textViewHomeScore.getText().toString())-score;
                scoreSave("Home",homeScore);
                break;

            case R.id.incHomeButton:
                homeScore = Integer.parseInt(textViewHomeScore.getText().toString())+score;
                scoreSave("Home",homeScore);
                break;

            case R.id.decAwayButton:
                awayScore = Integer.parseInt(textViewAwayScore.getText().toString())-score;
                scoreSave("Away",awayScore);
                break;

            case R.id.incAwayButton:
                awayScore = Integer.parseInt(textViewAwayScore.getText().toString())+score;
                scoreSave("Away",awayScore);
                break;
            }
            display();


    }

    public void scoreChangeSave(int score){
        if (isSaveChecked){
            SharedPreferences.Editor editor = savedValues.edit();
            editor.putInt("Score", score);
            editor.apply();
        }
    }

    public void scoreSave(String team, int score){
        if (isSaveChecked){
            SharedPreferences.Editor editor = savedValues.edit();
            if (team == "Home"){
                editor.putInt("HomeScore",score);
            } else {
                editor.putInt("AwayScore",score);
            }
            editor.apply();
        }
    }

    public void display(){
        textViewHomeScore.setText(Integer.toString(homeScore));
        textViewAwayScore.setText(Integer.toString(awayScore));
    }
}
