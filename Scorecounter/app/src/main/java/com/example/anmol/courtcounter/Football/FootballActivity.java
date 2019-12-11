package com.example.anmol.courtcounter.Football;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anmol.courtcounter.R;

public class FootballActivity extends AppCompatActivity {

    int scoreA = 0;
    int scoreB = 0;
    String winner = "";
    TextView scoreForTeamA;
    TextView scoreForTeamB;
    Button finishButton;
    Button reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_football);

        scoreForTeamA = findViewById(R.id.team_a_score);
        scoreForTeamB = findViewById(R.id.team_b_score);
        finishButton = findViewById(R.id.finishButton);
        reset = findViewById(R.id.resett);

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkGameWinner();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });

    }

    public void displayScore() {
        scoreForTeamA.setText(String.valueOf(scoreA));
        scoreForTeamB.setText(String.valueOf(scoreB));
    }

    public void teamAScore(View view) {
        scoreA += 1;
        displayScore();
    }

    public void teamBScore(View view) {
        scoreB += 1;
        displayScore();
    }


    public void checkGameWinner() {
        if(scoreA > scoreB){
            winner = "Team A wins!";
        }
        else if(scoreB > scoreA) {
            winner = "Team B wins!";
        }
        else if(scoreA == scoreB){
            winner = "It's a Tie!";
        }
        else {
            Toast.makeText(FootballActivity.this, "Error occured", Toast.LENGTH_SHORT).show();
        }
        Alert();
    }

    public void reset(){

        scoreA = 0;
        scoreB = 0;
        displayScore();
    }

    public void Alert(){
        final android.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Result: " + winner);
        builder.setPositiveButton("Play Again?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                reset();
            }
        });
        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}
