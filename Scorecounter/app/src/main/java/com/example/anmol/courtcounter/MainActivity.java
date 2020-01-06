package com.example.anmol.courtcounter;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.anmol.courtcounter.Basketball.BasketballActivity;

import com.example.anmol.courtcounter.LawnTennis.LawnTennisActivity;
import com.example.anmol.courtcounter.SaveResults.ResultActivity;
import com.example.anmol.courtcounter.TableTennis.TableTennisAcitivity;

import com.example.anmol.courtcounter.Football.FootballActivity;
import com.example.anmol.courtcounter.Kabaddi.KabaddiActivity;

public class MainActivity extends AppCompatActivity {

    Button basketball;
    Button volleyball;
    Button badminton;
    Button cricket;
    Button tableTennis;
    Button football;
    Button kabaddi;
    Button lawnTennis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        basketball = findViewById(R.id.button_basketball);
        volleyball = findViewById(R.id.button_volleyball);
        badminton =  findViewById(R.id.button_badminton);
        cricket = findViewById(R.id.button_cricket);
        tableTennis =findViewById(R.id.button_tableTennis);
        football = findViewById(R.id.button_football);
        kabaddi = findViewById(R.id.button_kabaddi);
        lawnTennis = findViewById(R.id.button_lawnTennis);


        basketball.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity( new Intent( MainActivity.this, BasketballActivity.class ) );
            }
        } );

        volleyball.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( MainActivity.this, com.example.anmol.courtcounter.Volleyball.VolleyballActivity.class ) );
            }
        } );

        badminton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( MainActivity.this, com.example.anmol.courtcounter.Badminton.BadmintonActivity.class ) );
            }
        } );

        cricket.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( MainActivity.this, com.example.anmol.courtcounter.Cricket.SelectFormat.class ) );
            }
        } );

        tableTennis.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( MainActivity.this, TableTennisAcitivity.class ) );
            }
        } );

        football.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( MainActivity.this, FootballActivity.class ) );
            }
        } );

        kabaddi.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( MainActivity.this, KabaddiActivity.class ) );
            }
        });

        lawnTennis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LawnTennisActivity.class));
            }
        }); 
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.main_menu, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_about:
                startActivity( new Intent( MainActivity.this, AboutActivity.class ) );
                return true;

            case R.id.action_savedResults:
                startActivity(new Intent(MainActivity.this, ResultActivity.class));
                return true;
            case R.id.action_share:
                shareApp();
                return true;
            default:
                return super.onOptionsItemSelected( item );
        }

    }

    public void shareApp(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String shareBody = "Hey! I found a really cool Sports Scoring App. Have a look https://github.com/NJACKWinterOfCode/Android-ScorerApp ";
        String shareTitle = "Real Time Scorer";
        intent.putExtra(Intent.EXTRA_SUBJECT,shareTitle);
        intent.putExtra(Intent.EXTRA_TEXT,shareBody);

        startActivity(intent.createChooser(intent,"Share Using.."));
    }

}