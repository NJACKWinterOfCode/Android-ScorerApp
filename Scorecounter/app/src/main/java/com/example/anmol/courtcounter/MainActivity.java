package com.example.anmol.courtcounter;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.anmol.courtcounter.Badminton.BadmintonActivity;
import com.example.anmol.courtcounter.Basketball.BasketballActivity;
import com.example.anmol.courtcounter.Cricket.SelectFormat;
import com.example.anmol.courtcounter.LawnTennis.LawnTennisActivity;
import com.example.anmol.courtcounter.SaveResults.ResultActivity;
import com.example.anmol.courtcounter.TableTennis.TableTennisAcitivity;

import com.example.anmol.courtcounter.Football.FootballActivity;
import com.example.anmol.courtcounter.Kabaddi.KabaddiActivity;
import com.example.anmol.courtcounter.Volleyball.VolleyballActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<menuItem> items = new ArrayList<menuItem>();
        items.add(new menuItem(R.drawable.basketball, R.string.basketball));
        items.add(new menuItem(R.drawable.volleyball, R.string.volleyball));
        items.add(new menuItem(R.drawable.badminton, R.string.badminton));
        items.add(new menuItem(R.drawable.tabletennis, R.string.tableTennis));
        items.add(new menuItem(R.drawable.cricket, R.string.cricket));
        items.add(new menuItem(R.drawable.football, R.string.football));
        items.add(new menuItem(R.drawable.kabaddi, R.string.kabaddi));
        items.add(new menuItem(R.drawable.tenn, R.string.tennis));

        menuAdapter adapter = new menuAdapter(this, items);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                menuItem item = items.get(position);

                if(item.getTitle()==R.string.basketball)
                    startActivity(new Intent(MainActivity.this, BasketballActivity.class));
                if(item.getTitle()==R.string.volleyball)
                    startActivity(new Intent(MainActivity.this, VolleyballActivity.class));
                if(item.getTitle()==R.string.badminton)
                    startActivity(new Intent(MainActivity.this, BadmintonActivity.class));
                if(item.getTitle()==R.string.tableTennis)
                    startActivity(new Intent(MainActivity.this, TableTennisAcitivity.class));
                if(item.getTitle()==R.string.cricket)
                    startActivity(new Intent(MainActivity.this, SelectFormat.class));
                if(item.getTitle()==R.string.football)
                    startActivity(new Intent(MainActivity.this, FootballActivity.class));
                if(item.getTitle()==R.string.kabaddi)
                    startActivity(new Intent(MainActivity.this, KabaddiActivity.class));
                if(item.getTitle()==R.string.tennis)
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

            default:
                return super.onOptionsItemSelected( item );
        }

    }

}