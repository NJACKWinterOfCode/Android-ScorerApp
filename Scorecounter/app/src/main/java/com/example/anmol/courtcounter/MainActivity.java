package com.example.anmol.courtcounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.anmol.courtcounter.Badminton.BadmintonActivity;
import com.example.anmol.courtcounter.Basketball.BasketballActivity;
import com.example.anmol.courtcounter.TableTennis.tableTennisAcitivity;
import com.example.anmol.courtcounter.Volleyball.VolleyballActivity;


public class MainActivity extends AppCompatActivity{

    ImageView basketball;
    ImageView volleyball;
    ImageView badminton;
    ImageView cricket;
    ImageView tableTennis;
    ImageView football;
    ImageView kabaddi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        basketball = findViewById(R.id.button_basketball);
        volleyball = findViewById(R.id.button_volleyball);
        badminton =  findViewById(R.id.button_badminton);
        cricket = findViewById(R.id.button_cricket);
        tableTennis =findViewById(R.id.button_tableTennis);
        football = findViewById(R.id.button_football);
        kabaddi = findViewById(R.id.button_kabaddi);

        Glide.with(this)
                .load(R.drawable.basketball)
                .into(basketball);
        Glide.with(this)
                .load(R.drawable.volleyball)
                .into(volleyball);
        Glide.with(this)
                .load(R.drawable.badminton)
                .into(badminton);
        Glide.with(this)
                .load(R.drawable.tabletennis)
                .into(tableTennis);
        Glide.with(this)
                .load(R.drawable.cricket)
                .into(cricket);
        Glide.with(this)
                .load(R.drawable.football)
                .into(football);
        Glide.with(this)
                .load(R.drawable.kabaddi)
                .into(kabaddi);
        /*Glide.with(this)
                .load(R.drawable.tenn)
                .into(lawnTennis);*/


        basketball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BasketballActivity.class));
            }
        });

        volleyball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, com.example.anmol.courtcounter.Volleyball.VolleyballActivity.class));
            }
        });

        badminton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, com.example.anmol.courtcounter.Badminton.BadmintonActivity.class));
            }
        });

        cricket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, com.example.anmol.courtcounter.Cricket.SelectFormat.class));
            }
        });

        tableTennis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, com.example.anmol.courtcounter.TableTennis.tableTennisAcitivity.class));
            }
        });

        football.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, com.example.anmol.courtcounter.Football.footballActivity.class));
            }
        });

        kabaddi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, com.example.anmol.courtcounter.Kabaddi.kabaddiActivity.class));
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about) {
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}