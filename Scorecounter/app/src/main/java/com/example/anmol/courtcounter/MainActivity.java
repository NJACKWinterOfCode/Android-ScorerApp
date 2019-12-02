package com.example.anmol.courtcounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.anmol.courtcounter.Basketball.BasketballActivity;
import com.example.anmol.courtcounter.Volleyball.VolleyballActivity;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout basketball;
    ConstraintLayout volleyball;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        basketball = findViewById(R.id.basketball);
        volleyball = findViewById(R.id.volleyball);


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
    }
}
