package com.example.anmol.courtcounter.Basketball;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anmol.courtcounter.Kabaddi.kabaddiActivity;
import com.example.anmol.courtcounter.R;

import java.util.Locale;

public class BasketballActivity extends AppCompatActivity {

    private ImageView editNameA;
    private ImageView editNameB;
    private TextView nameTeamA;
    private TextView nameTeamB;
    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long START_TIME_IN_MILLIS = 2400000;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private Button threePointerTeamA;
    private Button threePointerTeamB;
    private Button twoPointerTeamA;
    private Button twoPointerTeamB;
    private Button freeThrowTeamA;
    private Button freeThrowTeamB;
    private Button undoTeamA;
    private Button undoTeamB;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basketball);
        getSupportActionBar().setTitle("Basketball");

        nameTeamA = findViewById(R.id.teamA_nameTextView);
        nameTeamB = findViewById(R.id.teamB_nameTextView);
        editNameA = findViewById(R.id.edit_teamA);
        editNameB = findViewById(R.id.edit_teamB);
        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        mButtonStartPause = findViewById(R.id.button_start_pause);
        threePointerTeamA = findViewById(R.id.threePointerTeamA);
        threePointerTeamB = findViewById(R.id.threePointerTeamB);
        twoPointerTeamA = findViewById(R.id.twoPointerTeamA);
        twoPointerTeamB = findViewById(R.id.twoPointerTeamB);
        freeThrowTeamA = findViewById(R.id.freeTeamA);
        freeThrowTeamB = findViewById(R.id.freeTeamB);
        undoTeamA = findViewById(R.id.undoTeamA);
        undoTeamB = findViewById(R.id.undoTeamB);
        mediaPlayer = MediaPlayer.create(this,R.raw.tick);



        buttonDisable();

        mTextViewCountDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(BasketballActivity.this);
                builder.setTitle("Edit Timer");
                final EditText input = new EditText(BasketballActivity.this);
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
                            Toast.makeText(BasketballActivity.this, "Please enter a value", Toast.LENGTH_SHORT).show();
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

        editNameA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(BasketballActivity.this);
                alertBuilder.setTitle("Edit Team Name");

                final EditText input = new EditText(BasketballActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                alertBuilder.setView(input);

                alertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        nameTeamA.setText(input.getText());
                    }
                });

                alertBuilder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                alertBuilder.show();
            }
        });

        editNameB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(BasketballActivity.this);
                alertBuilder.setTitle("Edit Team Name");

                final EditText input = new EditText(BasketballActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                alertBuilder.setView(input);

                alertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        nameTeamB.setText(input.getText());
                    }
                });

                alertBuilder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                alertBuilder.show();
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

    int a = 0, b = 0, i = 0, j = 0;
    String w = "";
    int as[] = new int[100];
    int bs[] = new int[100];

    public void displayForTeamA(int score) {
        TextView scoreView = findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    public void displayForTeamB(int score) {
        TextView scoreView = findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    public void one(View view) {
        a = a + 1;
        as[i] = 1;
        i++;
        displayForTeamA(a);
    }

    public void two(View view) {
        a = a + 2;
        as[i] = 2;
        i++;
        displayForTeamA(a);
    }

    public void three(View view) {
        a = a + 3;
        as[i] = 3;
        i++;
        displayForTeamA(a);

    }
    public void oneb(View view) {
        b = b + 1;
        bs[j] = 1;
        j++;
        displayForTeamB(b);
    }

    public void twob(View view) {
        b = b + 2;
        bs[j] = 2;
        j++;
        displayForTeamB(b);
    }

    public void threeb(View view) {
        b = b + 3;
        bs[j] = 3;
        j++;
        displayForTeamB(b);
    }


    public void undoa(View view) {
        if (a > 0) {
            a = a - as[i - 1];
            i--;
        }
        displayForTeamA(a);
    }

    public void undob(View view) {
        if (b > 0) {
            b = b - bs[j - 1];
            j--;
        }
        displayForTeamB(b);
    }

    public void reset() {
        a = 0;
        b = 0;
        displayForTeamA(a);
        displayForTeamB(b);
        nameTeamA.setText("Team A");
        nameTeamB.setText("Team B");
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        buttonDisable();
        mediaPlayer.pause();
    }

    public void finish(View view) {
        if (a > b)
            w = "Team A wins!";
        if (b > a)
            w = "Team B wins!";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (w != "") {
            builder.setTitle("Result : " + w);
            builder.setMessage("Final score line : \n"+"\n"+"[Team A] : " + a + "-" + b + " : [Team B]");
        }
        else{
            builder.setTitle("Result : It's a Tie");
            builder.setMessage("Final score line : \n"+"\n"+"[Team A] : " + a + "-" + b + " : [Team B]");

        }
        builder.setNegativeButton("Play Again?", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Keep editing" button, so dismiss the dialog
                // and continue editing the pet.
                if (dialog != null) {
                    reset();
                    dialog.dismiss();
                }
            }
        });
        builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Keep editing" button, so dismiss the dialog
                // and continue editing the pet.
                if (dialog != null) {
                    finish();

                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        mediaPlayer.pause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.reset:
                reset();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void buttonDisable(){

        threePointerTeamA.setEnabled(false);
        threePointerTeamB.setEnabled(false);
        twoPointerTeamA.setEnabled(false);
        twoPointerTeamB.setEnabled(false);
        freeThrowTeamB.setEnabled(false);
        freeThrowTeamA.setEnabled(false);
        undoTeamA.setEnabled(false);
        undoTeamB.setEnabled(false);
    }

    public void buttonEnable(){
        threePointerTeamA.setEnabled(true);
        threePointerTeamB.setEnabled(true);
        twoPointerTeamA.setEnabled(true);
        twoPointerTeamB.setEnabled(true);
        freeThrowTeamB.setEnabled(true);
        freeThrowTeamA.setEnabled(true);
        undoTeamA.setEnabled(true);
        undoTeamB.setEnabled(true);
    }
}
