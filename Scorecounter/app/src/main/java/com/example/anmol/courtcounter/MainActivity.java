package com.example.anmol.courtcounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.anmol.courtcounter.Basketball.BasketballActivity;
import com.example.anmol.courtcounter.Volleyball.VolleyballActivity;

public class MainActivity extends AppCompatActivity {

    Button basketball;
    Button volleyball;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        basketball = findViewById(R.id.button_basketball);
        volleyball = findViewById(R.id.Volleyball);


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
