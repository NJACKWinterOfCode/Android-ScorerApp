package com.example.anmol.courtcounter.Cricket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.anmol.courtcounter.R;

public class FirstTeam extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_team);

        Button teamAFirst = findViewById(R.id.TeamAFirst);
        Button teamBFirst = findViewById(R.id.TeamBFirst);

        final String matchFormat = getIntent().getStringExtra("MATCH_TYPE");
        final Intent match;
            match = new Intent(FirstTeam.this, CricketActivity.class);


        teamAFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                match.putExtra("BAT_FIRST","A");
                match.putExtra("MATCH_TYPE",matchFormat);
                startActivity(match);
            }
        });

        teamBFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                match.putExtra("BAT_FIRST","B");
                match.putExtra("MATCH_TYPE",matchFormat);
                startActivity(match);
            }
        });
    }
}
