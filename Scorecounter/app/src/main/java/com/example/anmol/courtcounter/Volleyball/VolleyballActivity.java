package com.example.anmol.courtcounter.Volleyball;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anmol.courtcounter.R;

import org.w3c.dom.Text;

public class VolleyballActivity extends AppCompatActivity {

    private static final String TAG = "VolleyballActivity";

    private Button resetButton;
    private Button switchButton;
    private TextView teamAname;
    private TextView teamBname;
    private TextView teamA_setScoreTextView;
    private TextView teamB_setScoreTextView;
    private TextView teamB_ScoreTextView;
    private TextView teamA_ScoreTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volleyball);

        resetButton = findViewById(R.id.resetButton);
        switchButton = findViewById(R.id.switchButton);

        teamAname = findViewById(R.id.teamAname);
        teamA_ScoreTextView = findViewById(R.id.teamA_ScoreTextView);
        teamA_setScoreTextView = findViewById(R.id.teamA_setScoreTextView);

        teamBname = findViewById(R.id.teamBname);
        teamB_ScoreTextView = findViewById(R.id.teamB_ScoreTextView);
        teamB_setScoreTextView = findViewById(R.id.teamB_setScoreTextView);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });

        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchSides();
            }
        });

        teamAname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(VolleyballActivity.this);
                alertBuilder.setTitle("Edit Team Name");

                final EditText input = new EditText(VolleyballActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                alertBuilder.setView(input);
                alertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        teamAname.setText(input.getText());
                    }
                });
                alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertBuilder.show();
            }
        });

        teamBname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(VolleyballActivity.this);
                alertBuilder.setTitle("Edit Team Name");

                final EditText input = new EditText(VolleyballActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                alertBuilder.setView(input);
                alertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        teamBname.setText(input.getText());
                    }
                });
                alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertBuilder.show();
            }
        });

        teamA_ScoreTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: teamAscore incremented");
                int score = Integer.parseInt(teamA_ScoreTextView.getText().toString()) + 1;
                if(score == 25) {
                    Log.d(TAG, "onClick: now the score = 25");
                    teamA_setScoreTextView.setText(Integer.toString(Integer.parseInt(teamA_setScoreTextView.getText().toString()) + 1));
                    score = 0;
                    teamB_ScoreTextView.setText(Integer.toString(score));
                    Toast.makeText(VolleyballActivity.this, "Set Completed", Toast.LENGTH_SHORT).show();
                    isWinner();
                }
                teamA_ScoreTextView.setText(Integer.toString(score));
            }
        });

        teamB_ScoreTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: teamBscore incremented");
                int score = Integer.parseInt(teamB_ScoreTextView.getText().toString()) + 1;
                if(score == 25) {
                    Log.d(TAG, "onClick: now the score = 25");
                    teamB_setScoreTextView.setText(Integer.toString(Integer.parseInt(teamB_setScoreTextView.getText().toString()) + 1));
                    score = 0;
                    teamA_ScoreTextView.setText(Integer.toString(score));
                    Toast.makeText(VolleyballActivity.this, "Set Completed", Toast.LENGTH_SHORT).show();
                    isWinner();
                }
                teamB_ScoreTextView.setText(Integer.toString(score));
            }
        });
    }

    void isWinner() {

        Log.d(TAG, "isWinner called");

        if (teamA_setScoreTextView.getText().toString().equals("3")) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Winner");
            dialog.setMessage("Team A won");
            dialog.show();
            reset();
        } else if (teamB_setScoreTextView.getText().toString().equals("3")) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Winner");
            dialog.setMessage("Team B won");
            dialog.show();
            reset();
        }
    }

    void reset() {
        teamB_setScoreTextView.setText("0");
        teamA_setScoreTextView.setText("0");
        teamB_ScoreTextView.setText("0");
        teamA_ScoreTextView.setText("0");
        teamAname.setText("Team A");
        teamBname.setText("Team B");
    }

    void switchSides() {
        String nameA = teamAname.getText().toString();
        int colorA = teamAname.getCurrentTextColor();
        String scoreA = teamA_ScoreTextView.getText().toString();
        String setScoreA = teamA_setScoreTextView.getText().toString();

        String nameB = teamBname.getText().toString();
        int colorB = teamBname.getCurrentTextColor();
        String scoreB = teamB_ScoreTextView.getText().toString();
        String setScoreB = teamB_setScoreTextView.getText().toString();

        teamBname.setText(nameA);
        teamBname.setTextColor(colorA);
        teamB_ScoreTextView.setText(scoreA);
        teamB_ScoreTextView.setTextColor(colorA);
        teamB_setScoreTextView.setText(setScoreA);
        teamB_setScoreTextView.setTextColor(colorA);

        teamAname.setText(nameB);
        teamAname.setTextColor(colorB);
        teamA_ScoreTextView.setText(scoreB);
        teamA_ScoreTextView.setTextColor(colorB);
        teamA_setScoreTextView.setText(setScoreB);
        teamA_setScoreTextView.setTextColor(colorB);

    }
}
