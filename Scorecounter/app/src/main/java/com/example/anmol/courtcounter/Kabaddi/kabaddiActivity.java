package com.example.anmol.courtcounter.Kabaddi;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anmol.courtcounter.Football.footballActivity;
import com.example.anmol.courtcounter.R;

import java.util.Locale;

public class kabaddiActivity extends AppCompatActivity {

    int scoreA = 0;
    int scoreB = 0;
    String winner = "";
    TextView scoreForTeamA;
    TextView scoreForTeamB;
    Button finishButton;
    Button reset;
    Button multiPointsA;
    Button multiPointsB;
    Button ButtonscoreA;
    Button ButtonscoreB;
    Button ButtonbonusA;
    Button ButtonbonusB;
    Button ButtonallOutA;
    Button ButtonallOutB;
    MediaPlayer mediaPlayer;

    private int multiPoints = 0;
    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long START_TIME_IN_MILLIS = 2400000;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kabaddi);

        scoreForTeamA = findViewById(R.id.team_a_score);
        scoreForTeamB = findViewById(R.id.team_b_score);
        finishButton = findViewById(R.id.finish);
        reset = findViewById(R.id.reset);
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
        multiPointsA = findViewById(R.id.multiPointsA);
        multiPointsB = findViewById(R.id.multiPointsB);
        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        ;        mButtonStartPause = findViewById(R.id.button_start_pause);
        ButtonscoreA = findViewById(R.id.ButtonscoreA);
        ButtonscoreB = findViewById(R.id.ButtonscoreB);
        ButtonbonusA = findViewById(R.id.redCard_TeamA);
        ButtonbonusB = findViewById(R.id.redCard_TeamB);
        ButtonallOutA = findViewById(R.id.yellowCard_TeamA);
        ButtonallOutB = findViewById(R.id.yellowCard_TeamB);
        mediaPlayer = MediaPlayer.create(this,R.raw.tick);

        buttonDisable();

        mTextViewCountDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(kabaddiActivity.this);
                builder.setTitle("Edit Timer");
                final EditText input = new EditText(kabaddiActivity.this);
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(input);

                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(input.length() > 0){
                        mTimeLeftInMillis = Integer.parseInt(input.getText().toString())*60000;
                        updateCountDownText();
                    }
                        else if(input.length()==0){
                            Toast.makeText(kabaddiActivity.this, "Please enter a value", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();
            }
        });

        multiPointsA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(kabaddiActivity.this);
                builder.setTitle("Multi-Points");
                final EditText input = new EditText(kabaddiActivity.this);
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(input);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        multiPoints = Integer.parseInt(input.getText().toString());
                        scoreA += multiPoints;
                        displayScore();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();
            }
        });
        multiPointsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(kabaddiActivity.this);
                builder.setTitle("Multi-Points");
                final EditText input = new EditText(kabaddiActivity.this);
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(input);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        multiPoints = Integer.parseInt(input.getText().toString());
                        scoreB += multiPoints;
                        displayScore();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();
            }
        });

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });
        updateCountDownText();
    }
    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
                mediaPlayer.start();
            }
            @Override
            public void onFinish() {
                mTimerRunning = false;
                mButtonStartPause.setText("Start");
                mediaPlayer.pause();
            }
        }.start();

        mTimerRunning = true;
        mButtonStartPause.setText("pause");
        buttonEnable();
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("Start");
        buttonDisable();
        mediaPlayer.pause();
    }
    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }

    public void displayScore() {
        scoreForTeamA.setText(String.valueOf(scoreA));
        scoreForTeamB.setText(String.valueOf(scoreB));
    }
    public void teamAScore(View view) {
        scoreA += 1;
        displayScore();
    }
    public void allOutA(View view){
        scoreA +=2;
        displayScore();
    }
    public void teamBScore(View view) {
        scoreB += 1;
        displayScore();
    }
    public void allOutB(View view){
        scoreB += 2;
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
            Toast.makeText(kabaddiActivity.this, "Error occured", Toast.LENGTH_SHORT).show();
        }
        Alert();
    }
    public void reset(){
        scoreA = 0;
        scoreB = 0;
        displayScore();
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        buttonDisable();
        mediaPlayer.pause();
    }
    public void Alert(){
        final android.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Result: " + winner);
        builder.setMessage("Final Scoreline : " +"\n[Team A] : " + scoreA +" - "+scoreB+" : [Team B]");
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

    public void buttonDisable(){

        multiPointsA.setEnabled(false);
        multiPointsB.setEnabled(false);
        ButtonscoreA.setEnabled(false);
        ButtonscoreB.setEnabled(false);
        ButtonbonusA.setEnabled(false);
        ButtonbonusB.setEnabled(false);
        ButtonallOutA.setEnabled(false);
        ButtonallOutB.setEnabled(false);
    }

    public void buttonEnable(){
        multiPointsA.setEnabled(true);
        multiPointsB.setEnabled(true);
        ButtonscoreA.setEnabled(true);
        ButtonscoreB.setEnabled(true);
        ButtonbonusA.setEnabled(true);
        ButtonbonusB.setEnabled(true);
        ButtonallOutA.setEnabled(true);
        ButtonallOutB.setEnabled(true);
    }
}
