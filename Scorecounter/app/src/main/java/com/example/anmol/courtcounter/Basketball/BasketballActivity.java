package com.example.anmol.courtcounter.Basketball;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anmol.courtcounter.R;

public class BasketballActivity extends AppCompatActivity {

    private ImageView editNameA;
    private ImageView editNameB;
    private TextView nameTeamA;
    private TextView nameTeamB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basketball);
        getSupportActionBar().setTitle("Basketball");

        nameTeamA = findViewById(R.id.teamA_nameTextView);
        nameTeamB = findViewById(R.id.teamB_nameTextView);
        editNameA = findViewById(R.id.edit_teamA);
        editNameB = findViewById(R.id.edit_teamB);

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
    }

    public void finish(View view) {
        if (a > b)
            w = "TEAM A";
        if (b > a)
            w = "TEAM B";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (w != "")
            builder.setMessage("Winner is  " + w);
        else
            builder.setMessage(" Oooh  It's a TIE");
        builder.setNegativeButton("ANOTHER GAME", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Keep editing" button, so dismiss the dialog
                // and continue editing the pet.
                if (dialog != null) {
                    reset();
                    dialog.dismiss();
                }
            }
        });
        builder.setPositiveButton("EXIT", new DialogInterface.OnClickListener() {
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
}
