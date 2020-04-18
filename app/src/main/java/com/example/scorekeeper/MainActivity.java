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
    private RadioButton score1, score2, score3;
    private int score = 0;
//    private int homeScore =0, awayScore = 0;
    private SharedPreferences savedValues;
    private boolean isSaveChecked;


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
//
//        savedValues = getSharedPreferences("saveValue", MODE_PRIVATE);
        savedValues = PreferenceManager.getDefaultSharedPreferences(this);
        isSaveChecked = savedValues.getBoolean("pref_save",false);

        if(isSaveChecked) {
            String homeScore= savedValues.getString("HomeScore","");
            String awayScore = savedValues.getString("AwayScore","");
            score = savedValues.getInt("score",2);
            textViewHomeScore.setText(homeScore);
            textViewAwayScore.setText(awayScore);
        }else{
            SharedPreferences.Editor editor = savedValues.edit();
            editor.putString("HomeScore", textViewHomeScore.getText().toString());
            editor.putString("AwayScore", textViewAwayScore.getText().toString());
            editor.putInt("score", score);
            editor.apply();
        }
////
//        if (score == 1) {
//            scoreChange.check(R.id.score1);
//        } else if (score == 2) {
//            scoreChange.check(R.id.score2);
//        } else {
//            scoreChange.check(R.id.score3);
//        }


//        savedValues = getSharedPreferences("saveValue", MODE_PRIVATE);
//        PreferenceManager.setDefaultValues(this,R.xml.root_preferences, false);
//        isSaveChecked = PreferenceManager.getDefaultSharedPreferences(this);

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



//    public void save(View view){
//        SharedPreferences savedValues = getSharedPreferences("title", MODE_PRIVATE);
//        SharedPreferences.Editor editor = savedValues.edit();
//    }

//    public void onPause(){
//        SharedPreferences.Editor editor = savedValues.edit();
//
//        if(isSaveChecked.getBoolean("pref_save",false)){
//
//            editor.putString("HomeScore",textViewHomeScore.toString());
//            editor.putString("AwayScore",textViewAwayScore.toString());
//            editor.putInt("score",score);
//            editor.apply();
//
//        }else{
//            editor.clear();
//            savedValues.getBoolean("pref_save",false);
//        }
//        super.onPause();
//    }
//
//    public void onResume(){
//        super.onResume();
//        if(savedValues.getBoolean("pref_save",false)){
//            textViewHomeScore.setText(savedValues.getString("HomeScore",""));
//            textViewAwayScore.setText(savedValues.getString("AwayScore",""));
//            score = savedValues.getInt("score",0);
//
//            if(score == 1){
//                scoreChange.check(R.id.score1);
//            }else if(score == 2){
//                scoreChange.check(R.id.score2);
//            }else{
//                scoreChange.check(R.id.score3);
//            }
//        }
//    }

    @Override
    public void onClick(View v) {

//        score = 0;
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

//        switch (v.getId()){
//            case R.id.decHomeButton:
//                homeScore = Integer.parseInt(textViewHomeScore.getText().toString())-score;
//                display();
//                break;
//
//            case R.id.incHomeButton:
//                homeScore = Integer.parseInt(textViewHomeScore.getText().toString())+score;
//                display();
//                break;
//
//            case R.id.decAwayButton:
//                awayScore = Integer.parseInt(textViewAwayScore.getText().toString())-score;
//                display();
//                break;
//
//            case R.id.incAwayButton:
//                awayScore = Integer.parseInt(textViewAwayScore.getText().toString())+score;
//                display();
//                break;
//        }

    }

//    public void display(){
//        textViewHomeScore.setText(Integer.toString(homeScore));
//        textViewAwayScore.setText(Integer.toString(awayScore));
//    }
}
