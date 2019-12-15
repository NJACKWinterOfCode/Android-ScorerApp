package com.example.anmol.courtcounter.Cricket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.anmol.courtcounter.R;

public class SelectFormat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_format);

        Button t20 = findViewById(R.id.T20);
        Button odi = findViewById(R.id.ODI);

        final Intent matchFormat = new Intent(SelectFormat.this, FirstTeam.class);

        t20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matchFormat.putExtra("MATCH_TYPE","T20");
                startActivity(matchFormat);
            }

        });

        odi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matchFormat.putExtra("MATCH_TYPE", "ODI");
                startActivity(matchFormat);
            }
        });
    }

}
