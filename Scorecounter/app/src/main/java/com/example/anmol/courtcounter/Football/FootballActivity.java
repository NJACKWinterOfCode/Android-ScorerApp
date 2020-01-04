package com.example.anmol.courtcounter.Football;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anmol.courtcounter.R;
import com.example.anmol.courtcounter.SaveResults.Result;
import com.example.anmol.courtcounter.SaveResults.ResultViewModel;

import java.util.Locale;

public class FootballActivity extends AppCompatActivity {

    int scoreA = 0;
    int scoreB = 0;
    TextView teamAHeading;
    TextView teamBHeading;
    String winner = "";
    TextView scoreForTeamA;
    TextView scoreForTeamB;
    Button finishButton;
    Button reset;
    Button redCard_teamA;
    Button redCard_teamB;
    Button yellowCard_teamA;
    Button yellowCard_teamB;
    Button ButtonscoreA;
    Button ButtonscoreB;
    private TextView redA;
    private TextView redB;
    private TextView yellowA;
    private TextView yellowB;
    int redCountA = 0;
    int redCountB = 0;
    int yellowCountA = 0;
    int yellowCountB = 0;
    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long START_TIME_IN_MILLIS = 5400000;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    MediaPlayer mediaPlayer;
    private ResultViewModel resultViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_football );

        resultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);
        scoreForTeamA = findViewById( R.id.team_a_score );
        scoreForTeamB = findViewById( R.id.team_b_score );
        finishButton = findViewById( R.id.finish );
        reset = findViewById( R.id.reset );
        redCard_teamA = findViewById( R.id.redCard_TeamA );
        redCard_teamB = findViewById( R.id.redCard_TeamB );
        yellowCard_teamA = findViewById( R.id.yellowCard_TeamA );
        yellowCard_teamB = findViewById( R.id.yellowCard_TeamB );
        mTextViewCountDown = findViewById( R.id.text_view_countdown );
        mButtonStartPause = findViewById( R.id.button_start_pause );
        ButtonscoreA = findViewById( R.id.scoreA );
        ButtonscoreB = findViewById( R.id.scoreB );
        mediaPlayer = MediaPlayer.create( this, R.raw.tick );
        teamAHeading = findViewById(R.id.headA);
        teamBHeading = findViewById(R.id.headB);
        redA = findViewById(R.id.redA);
        redB = findViewById(R.id.redB);
        yellowA = findViewById(R.id.yellowA);
        yellowB = findViewById(R.id.yellowB);

        buttonDisablebeforeMatch();

        redCard_teamA.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redCardA();
            }
        } );

        redCard_teamB.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redCardB();
            }
        } );

        yellowCard_teamA.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yellowCardA();
            }
        } );

        yellowCard_teamB.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yellowCardB();
            }
        } );

        finishButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkGameWinner();
                mediaPlayer.stop();
            }
        } );
        reset.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
                mediaPlayer.pause();
            }
        } );

        mButtonStartPause.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        } );
        updateCountDownText();

        mTextViewCountDown.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder( FootballActivity.this );
                builder.setTitle( "Edit Timer" );
                final EditText input = new EditText( FootballActivity.this );
                input.setInputType( InputType.TYPE_CLASS_NUMBER );
                builder.setView( input );

                builder.setPositiveButton( "Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (input.length() > 0) {
                            mTimeLeftInMillis = Integer.parseInt( input.getText().toString() ) * 60000;
                            updateCountDownText();
                        } else if (input.length() == 0) {
                            Toast.makeText( FootballActivity.this, "Please enter a value", Toast.LENGTH_SHORT ).show();
                        }
                    }
                } );
                builder.setNegativeButton( "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                } );
                builder.show();
            }
        } );
        teamAHeading.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.appcompat.app.AlertDialog.Builder alertBuilder = new androidx.appcompat.app.AlertDialog.Builder( FootballActivity.this );
                alertBuilder.setTitle( "Edit Team Name" );

                final EditText input = new EditText( FootballActivity.this );
                input.setInputType( InputType.TYPE_CLASS_TEXT );
                alertBuilder.setView( input );
                alertBuilder.setPositiveButton( "Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        teamAHeading.setText( input.getText() );
                    }
                } );
                alertBuilder.setNegativeButton( "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                } );
                alertBuilder.show();
            }
        } );

        teamBHeading.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.appcompat.app.AlertDialog.Builder alertBuilder = new androidx.appcompat.app.AlertDialog.Builder( FootballActivity.this );
                alertBuilder.setTitle( "Edit Team Name" );

                final EditText input = new EditText( FootballActivity.this );
                input.setInputType( InputType.TYPE_CLASS_TEXT );
                alertBuilder.setView( input );
                alertBuilder.setPositiveButton( "Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        teamBHeading.setText( input.getText() );
                    }
                } );
                alertBuilder.setNegativeButton( "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                } );
                alertBuilder.show();
            }
        } );
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer( mTimeLeftInMillis, 1000 ) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
                mediaPlayer.start();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                mButtonStartPause.setText( "Start" );
                mediaPlayer.pause();
            }
        }.start();

        mTimerRunning = true;
        mButtonStartPause.setText( "pause" );
        buttonEnable();
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText( "Start" );
        buttonDisableinMatch();
        mediaPlayer.pause();
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format( Locale.getDefault(), "%02d:%02d", minutes, seconds );

        mTextViewCountDown.setText( timeLeftFormatted );
    }

    public void displayScore() {
        scoreForTeamA.setText( String.valueOf( scoreA ) );
        scoreForTeamB.setText( String.valueOf( scoreB ) );
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
        if (scoreA > scoreB) {
            winner = teamAHeading.getText().toString();
        } else if (scoreB > scoreA) {
            winner = teamBHeading.getText().toString();
        } else if (scoreA == scoreB) {
            winner = "Tie";
        } else {
            Toast.makeText( FootballActivity.this, "Error occured", Toast.LENGTH_SHORT ).show();
        }
        Alert();
    }

    public void reset() {
        scoreA = 0;
        scoreB = 0;
        displayScore();
        redCountA = 0;
        redCountB = 0;
        yellowCountA = 0;
        yellowCountB = 0;
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        buttonDisablebeforeMatch();
    }

    public void Alert() {
        final android.app.AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setTitle( "Winner : " + winner );
        builder.setMessage( "Final Score line: "+"\n["+teamAHeading.getText().toString() +"] " + scoreA +" - "+scoreB+" ["+teamBHeading.getText().toString()+"]" + "\n" + "\nRed Cards for "+teamAHeading.getText().toString()+" = " + redCountA + "\nRed Cards for "+teamBHeading.getText().toString()+" = " + redCountB + "\nYellow card for "+teamAHeading.getText().toString()+" = " + yellowCountA + "\nYellow card for "+teamBHeading.getText().toString()+" = " + yellowCountB );
        builder.setPositiveButton( "Play Again?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                reset();
            }
        } );
        builder.setNegativeButton( "Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        } );
        builder.setNeutralButton("Save and Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                addItems();
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void redCardA() {

        if (redCountA > 10) {
            Toast.makeText( this, "Team A Red Card limit reached", Toast.LENGTH_SHORT ).show();
        } else {
            redCountA += 1;
            redA.setText(String.valueOf(redCountA));
        }
    }

    public void redCardB() {

        if (redCountB > 10) {
            Toast.makeText( this, "Team B Red Card limit reached", Toast.LENGTH_SHORT ).show();
        } else {
            redCountB += 1;
            redB.setText(String.valueOf(redCountB));
        }
    }

    public void yellowCardA() {

        if (yellowCountA > 10) {
            Toast.makeText( this, "Team A Yellow Card limit reached", Toast.LENGTH_SHORT ).show();
        } else {
            yellowCountA += 1;
            yellowA.setText(String.valueOf(yellowCountA));
        }
    }

    public void yellowCardB() {
        if (yellowCountB > 10) {
            Toast.makeText( this, "Team B Yellow Card limit reached", Toast.LENGTH_SHORT ).show();
        } else {
            yellowCountB += 1;
            yellowB.setText(String.valueOf(yellowCountB));
        }
    }

    public void buttonDisableinMatch() {

        ButtonscoreA.setEnabled( false );
        ButtonscoreB.setEnabled( false );

    }

    public void buttonDisablebeforeMatch() {

        redCard_teamA.setEnabled( false );
        redCard_teamB.setEnabled( false );
        yellowCard_teamA.setEnabled( false );
        yellowCard_teamB.setEnabled( false );
        ButtonscoreA.setEnabled( false );
        ButtonscoreB.setEnabled( false );

    }

    public void buttonEnable() {

        redCard_teamA.setEnabled( true );
        redCard_teamB.setEnabled( true );
        yellowCard_teamA.setEnabled( true );
        yellowCard_teamB.setEnabled( true );
        ButtonscoreA.setEnabled( true );
        ButtonscoreB.setEnabled( true );

    }

    public void addItems(){
        String title = "Football";
        String outcome = "Winner : " + winner ;
        String scoreTwo = "Red Cards for "+teamAHeading.getText().toString()+" = " + redCountA;
        String scoreOne = teamAHeading.getText().toString() +": " + scoreA + "-" +scoreB + " :" +teamBHeading.getText().toString();
        String scoreThree ="Red Cards for "+teamBHeading.getText().toString()+" = " + redCountB;
        String scoreFour = "Yellow Cards for "+teamAHeading.getText().toString()+" = " + yellowCountA ;
        String scoreFive = "Yellow Cards for "+teamBHeading.getText().toString()+" = " + yellowCountB ;
        Result result = new Result(title,outcome,scoreOne,scoreTwo,scoreThree,scoreFour,scoreFive);
        resultViewModel.insert(result);
    }
}
