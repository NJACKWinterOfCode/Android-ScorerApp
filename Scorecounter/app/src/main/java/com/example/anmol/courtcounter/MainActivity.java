package com.example.anmol.courtcounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.anmol.courtcounter.Badminton.BadmintonActivity;
import com.example.anmol.courtcounter.Basketball.BasketballActivity;
import com.example.anmol.courtcounter.Cricket.CricketActivity;
import com.example.anmol.courtcounter.Cricket.SelectFormat;
import com.example.anmol.courtcounter.TableTennis.tableTennisAcitivity;
import com.example.anmol.courtcounter.Volleyball.VolleyballActivity;

public class MainActivity extends AppCompatActivity {

    Button basketball;
    Button volleyball;
    Button badminton;
    Button cricket;
    Button tableTennis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        basketball = (Button) findViewById(R.id.button_basketball);
        volleyball = (Button) findViewById(R.id.button_volleyball);
        badminton = (Button) findViewById(R.id.button_badminton);
        cricket = (Button) findViewById(R.id.button_cricket);
        tableTennis = (Button) findViewById(R.id.button_tableTennis);


        basketball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BasketballActivity.class));
            }
        });

        volleyball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, VolleyballActivity.class));
            }
        });

        badminton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BadmintonActivity.class));
            }
        });

        cricket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SelectFormat.class));
            }
        });

        tableTennis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, tableTennisAcitivity.class));
            }
        });

    }
}
