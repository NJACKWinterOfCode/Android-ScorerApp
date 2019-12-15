package com.example.anmol.courtcounter.Cricket;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.anmol.courtcounter.MainActivity;
import com.example.anmol.courtcounter.R;

public class SuperOver extends AppCompatActivity {

    boolean isWideA=false;
    boolean isNoBallA=false;
    boolean isWideB = false;
    boolean isNoBallB = false;
    boolean teamABat=false;
    boolean teamBBat=false;
    int wicketA=0;
    int wicketB=0;
    int ScoreBallA=0;
    int ScoreBallB=0;
    boolean wickA=false;
    boolean wickB=false;
    private int scoreTeamA;
    private int ballTeamA = 1;
    private int overTeamA = 0;
    private int scoreTeamB;
    private int ballTeamB = 1;
    private int overTeamB = 0;
    String team;
    Button resButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_over);

        team = getIntent().getStringExtra("BAT_FIRST");
        resButton = findViewById(R.id.resButton);
        resButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
        if(team.equals("A"))
        {
            teamABat=true;
            teamBBat=false;
        }
        else {
            teamABat = false;
            teamBBat = true;
        }

        scoreTeamA = 0;
        scoreTeamB = 0;
        (findViewById(R.id.team_a_ball_1)).setBackgroundResource(R.color.colorSuccess);
        (findViewById(R.id.team_b_ball_1)).setBackgroundResource(R.color.colorSuccess);
    }
    public void addRunsTeamA(View view) {
        if(teamABat==false) {
            invalid();
            return;
        }
        TextView runField = findViewById(R.id.team_a_runs);
        int score = Integer.parseInt(runField.getText().toString());
        if(isNoBallA==true || isWideA==true)
            score++;
        scoreTeamA += score;
        ScoreBallA += score;
        runField.setText("0");
        displayOverScoreTeamA();
        displayScoreTeamA(scoreTeamA);
    }
    public void incrementRunsTeamA(View view) {
        if(teamABat==false) {
            invalid();
            return;
        }
        TextView runField = findViewById(R.id.team_a_runs);
        int score = Integer.parseInt(runField.getText().toString());
        score += 1;
        runField.setText("" + score);
    }
    public void decrementRunsTeamA(View view) {
        if(teamABat==false){
            invalid();
            return;
        }
        TextView runField = findViewById(R.id.team_a_runs);
        int score = Integer.parseInt(runField.getText().toString());
        if (score > 0) score -= 1;
        runField.setText("" + score);
    }
    public void addFourTeamA(View view) {
        if(teamABat==false)
        {
            invalid();
            return;
        }
        scoreTeamA += 4;
        ScoreBallA +=4;
        displayOverScoreTeamA();
        displayScoreTeamA(scoreTeamA);
    }
    public void addSixTeamA(View view) {
        if(teamABat==false)
        {
            invalid();
            return;
        }
        scoreTeamA += 6;
        ScoreBallA += 6;
        displayOverScoreTeamA();
        displayScoreTeamA(scoreTeamA);
    }
    public void outTeamA(View view) {
        if(teamABat==false)
        {
            invalid();
            return;
        }
        wickA=true;
        displayOverScoreTeamA();
    }
    private void displayOverScoreTeamA() {
        String displayScore="";
        TextView currentView = null;
        TextView swap = null;
        displayScore = ""+ScoreBallA;
        switch (ballTeamA) {
            case 1:
                currentView = findViewById(R.id.team_a_ball_1);
                if(isWideA==false && isNoBallA==false)
                    findViewById(R.id.team_a_ball_2).setBackgroundResource(R.color.colorSuccess);
                break;
            case 2:
                currentView = findViewById(R.id.team_a_ball_2);
                if(isWideA==false && isNoBallA==false)
                    findViewById(R.id.team_a_ball_3).setBackgroundResource(R.color.colorSuccess);
                break;
            case 3:
                currentView = findViewById(R.id.team_a_ball_3);
                if(isWideA==false && isNoBallA==false)
                    findViewById(R.id.team_a_ball_4).setBackgroundResource(R.color.colorSuccess);
                break;
            case 4:
                currentView = findViewById(R.id.team_a_ball_4);
                if(isWideA==false && isNoBallA==false)
                    findViewById(R.id.team_a_ball_5).setBackgroundResource(R.color.colorSuccess);
                break;
            case 5:
                currentView = findViewById(R.id.team_a_ball_5);
                if(isWideA==false && isNoBallA==false)
                    findViewById(R.id.team_a_ball_6).setBackgroundResource(R.color.colorSuccess);
                break;
            case 6:
                currentView = findViewById(R.id.team_a_ball_6);
                if(isWideA==false && isNoBallA==false)
                    findViewById(R.id.team_a_ball_1).setBackgroundResource(R.color.colorSuccess);
                break;
            default :
        }
        currentView.setText(displayScore);
        if (wickA==true) {
            wicketA++;
            currentView.setBackgroundResource(R.color.colorDanger);
            swap = findViewById(R.id.team_a_wickets);
            swap.setText(String.format("%d", Integer.parseInt(swap.getText().toString()) + 1));
        }
        else {
            if(isWideA==false && isNoBallA==false)
                currentView.setBackgroundResource(R.color.transparent);
        }
        if(isWideA==false && isNoBallA==false) {
            swap = findViewById(R.id.team_a_over);
            if (ballTeamA != 6)
                swap.setText(String.format("%d.%d", overTeamA, ballTeamA));
            else {
                overTeamA++;
                swap.setText(String.format("%d", overTeamA));
            }
            ballTeamA++;
            ScoreBallA=0;
        }
        isWideA=false;
        isNoBallA=false;
        wickA=false;
        if((overTeamA==1 || wicketA==10)&&(overTeamB==0 && wicketB==0))
        {
            teamABat=false;
            teamBBat=true;
        }
        if(team.equals("B")&&scoreTeamA>scoreTeamB)
        {
            result();
        }
        if((overTeamA==1 || wicketA==10)&&(overTeamB==1 || wicketB==10))
        {
            result();
        }
    }
    private void displayScoreTeamA(int score) {
        TextView scoreField = findViewById(R.id.team_a_score);
        scoreField.setText("" + score);
    }
    public void addRunsTeamB(View view) {
        if(teamBBat==false)
        {
            invalid();
            return;
        }
        TextView runField = findViewById(R.id.team_b_runs);
        int score = Integer.parseInt(runField.getText().toString());
        if(isWideB==true || isNoBallB==true)
        {
            score++;
        }
        scoreTeamB += score;
        ScoreBallB += score;
        runField.setText("0");
        displayOverScoreTeamB();
        displayScoreTeamB(scoreTeamB);
    }
    public void incrementRunsTeamB(View view) {
        if(teamBBat==false){
            invalid();
            return;
        }
        TextView runField = findViewById(R.id.team_b_runs);
        int score = Integer.parseInt(runField.getText().toString());
        score += 1;
        runField.setText("" + score);
    }
    public void decrementRunsTeamB(View view) {
        if(teamBBat==false)
        {
            invalid();
            return;
        }
        TextView runField = findViewById(R.id.team_b_runs);
        int score = Integer.parseInt(runField.getText().toString());
        if (score > 0) score -= 1;
        runField.setText("" + score);
    }
    public void addFourTeamB(View view) {
        if(teamBBat==false)
        {
            invalid();
            return;
        }
        scoreTeamB += 4;
        ScoreBallB += 4;
        displayOverScoreTeamB();
        displayScoreTeamB(scoreTeamB);
    }
    public void addSixTeamB(View view) {
        if(teamBBat==false)
        {
            invalid();
            return;
        }
        scoreTeamB += 6;
        ScoreBallB += 6;
        displayOverScoreTeamB();
        displayScoreTeamB(scoreTeamB);
    }
    public void outTeamB(View view) {
        if(teamBBat==false)
        {
            invalid();
            return;
        }
        wickB=true;
        displayOverScoreTeamB();
    }

    private void displayOverScoreTeamB() {
        String displayScore;
        TextView currentView = null;
        TextView swap = null;
        displayScore = "" + ScoreBallB;
        switch (ballTeamB) {
            case 1:
                currentView = findViewById(R.id.team_b_ball_1);
                if(isNoBallB==false && isWideB==false)
                    findViewById(R.id.team_b_ball_2).setBackgroundResource(R.color.colorSuccess);
                break;
            case 2:
                currentView = findViewById(R.id.team_b_ball_2);
                if(isNoBallB==false && isWideB==false)
                    findViewById(R.id.team_b_ball_3).setBackgroundResource(R.color.colorSuccess);
                break;
            case 3:
                currentView = findViewById(R.id.team_b_ball_3);
                if(isNoBallB==false && isWideB==false)
                    findViewById(R.id.team_b_ball_4).setBackgroundResource(R.color.colorSuccess);
                break;
            case 4:
                currentView = findViewById(R.id.team_b_ball_4);
                if(isNoBallB==false && isWideB==false)
                    findViewById(R.id.team_b_ball_5).setBackgroundResource(R.color.colorSuccess);
                break;
            case 5:
                currentView = findViewById(R.id.team_b_ball_5);
                if(isNoBallB==false && isWideB==false)
                    findViewById(R.id.team_b_ball_6).setBackgroundResource(R.color.colorSuccess);
                break;
            case 6:
                currentView = findViewById(R.id.team_b_ball_6);
                break;
            default:
        }
        currentView.setText(displayScore);
        if (wickB==true) {
            wicketB++;
            currentView.setBackgroundResource(R.color.colorDanger);
            swap = findViewById(R.id.team_b_wickets);
            swap.setText(String.format("%d", Integer.parseInt(swap.getText().toString()) + 1));
        }
        else {
            if(isNoBallB==false && isWideB==false)
                currentView.setBackgroundResource(R.color.transparent);
        }
        if(isNoBallB==false && isWideB==false) {
            swap = findViewById(R.id.team_b_over);
            if (ballTeamB != 6)
                swap.setText(String.format("%d.%d", overTeamB, ballTeamB));
            else {
                overTeamB++;
                swap.setText(String.format("%d", overTeamB ));
            }
            ballTeamB++;
            ScoreBallB=0;
        }
        wickB=false;
        isWideB=false;
        isNoBallB=false;
        if((overTeamB==1 || wicketB==10)&&(overTeamA==0 && wicketA==0))
        {
            teamBBat=false;
            teamABat=true;
        }
        if(team.equals("A")&&scoreTeamB>scoreTeamA)
        {
            result();
        }
        if((overTeamA==1 || wicketA==10)&&(overTeamB==1 || wicketB==10))
        {
            result();
        }
    }
    private void displayScoreTeamB(int score) {
        TextView scoreField = findViewById(R.id.team_b_score);
        scoreField.setText("" + score);
    }

    public void wideA(View view)
    {
        isWideA=true;
    }

    public void noBallA(View view)
    {
        isNoBallA=true;
    }

    public void wideB(View view) {
        isWideB=true;
    }
    public void noBallB (View view){
        isNoBallB = true;
    }
    public void invalid(){
        if(teamABat)
        {
            Toast.makeText((getApplicationContext()),"Team A's batting!",(Toast.LENGTH_SHORT)).show();
        }
        else if(teamBBat)
        {
            Toast.makeText((getApplicationContext()),"Team B's batting!",(Toast.LENGTH_SHORT)).show();
        }
        else
        {
            Toast.makeText((getApplicationContext()),"Match Over!",(Toast.LENGTH_SHORT)).show();
        }

    }
    public void result()
    {
        ((TextView)findViewById(R.id.resButton)).setText("EXIT");
        teamABat=false;
        teamBBat=false;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if(scoreTeamA>scoreTeamB)
        {
            builder.setMessage("Team A Wins");
        }
        if(scoreTeamA<scoreTeamB)
        {
            builder.setMessage("Team B Wins");
        }
        if(scoreTeamA==scoreTeamB)
        {
            builder.setMessage("Match Drawn");
        }
        builder.setNegativeButton("ANOTHER GAME", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(dialog!=null){
                    Intent newGame = new Intent(SuperOver.this, SelectFormat.class);
                    startActivity(newGame);
                    dialog.dismiss();
                }
            }
        });

        builder.setPositiveButton("EXIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(dialog!=null){
                    Intent exit = new Intent(SuperOver.this, MainActivity.class);
                    startActivity(exit);
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    public void reset() {
        if(!teamABat && !teamBBat)
        {
            Intent exit = new Intent(SuperOver.this, MainActivity.class);
            startActivity(exit);
        }
        // Reset Scores
        ((TextView) findViewById(R.id.team_a_score)).setText("0");
        ((TextView) findViewById(R.id.team_b_score)).setText("0");
        scoreTeamA = 0;
        scoreTeamB = 0;

        // Reset Over
        ((TextView) findViewById(R.id.team_a_over)).setText("0");
        ((TextView) findViewById(R.id.team_b_over)).setText("0");
        overTeamA = 0;
        overTeamB = 0;
        ballTeamA = 1;
        ballTeamB = 1;

        isWideA=false;
        isWideB=false;
        isNoBallA=false;
        isNoBallB=false;
        wicketA=0;
        wicketB=0;
        ScoreBallA=0;
        ScoreBallB=0;

        //Reset Wickets
        ((TextView) findViewById(R.id.team_a_wickets)).setText("0");
        ((TextView) findViewById(R.id.team_b_wickets)).setText("0");

        // Reset Team A over scores
        ((TextView) findViewById(R.id.team_a_ball_1)).setText("0");
        ((TextView) findViewById(R.id.team_a_ball_2)).setText("0");
        ((TextView) findViewById(R.id.team_a_ball_3)).setText("0");
        ((TextView) findViewById(R.id.team_a_ball_4)).setText("0");
        ((TextView) findViewById(R.id.team_a_ball_5)).setText("0");
        ((TextView) findViewById(R.id.team_a_ball_6)).setText("0");
        findViewById(R.id.team_a_ball_1).setBackgroundResource(R.color.colorSuccess);
        findViewById(R.id.team_a_ball_2).setBackgroundResource(R.color.transparent);
        findViewById(R.id.team_a_ball_3).setBackgroundResource(R.color.transparent);
        findViewById(R.id.team_a_ball_4).setBackgroundResource(R.color.transparent);
        findViewById(R.id.team_a_ball_5).setBackgroundResource(R.color.transparent);
        findViewById(R.id.team_a_ball_6).setBackgroundResource(R.color.transparent);

        // Reset Team B over scores
        ((TextView) findViewById(R.id.team_b_ball_1)).setText("0");
        ((TextView) findViewById(R.id.team_b_ball_2)).setText("0");
        ((TextView) findViewById(R.id.team_b_ball_3)).setText("0");
        ((TextView) findViewById(R.id.team_b_ball_4)).setText("0");
        ((TextView) findViewById(R.id.team_b_ball_5)).setText("0");
        ((TextView) findViewById(R.id.team_b_ball_6)).setText("0");
        findViewById(R.id.team_b_ball_1).setBackgroundResource(R.color.colorSuccess);
        findViewById(R.id.team_b_ball_2).setBackgroundResource(R.color.transparent);
        findViewById(R.id.team_b_ball_3).setBackgroundResource(R.color.transparent);
        findViewById(R.id.team_b_ball_4).setBackgroundResource(R.color.transparent);
        findViewById(R.id.team_b_ball_5).setBackgroundResource(R.color.transparent);
        findViewById(R.id.team_b_ball_6).setBackgroundResource(R.color.transparent);
    }
}


