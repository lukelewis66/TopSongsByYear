package com.example.topsongsbyyear;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView yearsView;
    private ArrayAdapter<String> yearsAdapter;
    ArrayList<String> years = new ArrayList<>();
    String theme;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.dark_theme:
                theme = "dark";
                setTheme();
                return true;
            case R.id.light_theme:
                theme = "light";
                setTheme();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        theme = "light";
        setContentView(R.layout.activity_main);
        yearsView = findViewById(R.id.yearsView);
        for(int i = 2017; i > 2005 ; i--){
            years.add(Integer.toString(i));
        }
        yearsAdapter = new ArrayAdapter<>(this, R.layout.simple_list_item, years);
        yearsView.setAdapter(yearsAdapter);
        yearsView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String year = yearsAdapter.getItem(position);
        Intent intent = new Intent(this, SongList.class);
        intent.putExtra("year", year);
        intent.putExtra("theme", theme);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        editor.putString("theme", theme);
        editor.commit();

        super.onPause();
    }


    @Override
    protected void onResume() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        theme = prefs.getString("theme", "light");
        setTheme();
        super.onResume();
    }

    void setTheme(){
        switch (theme) {
            case "dark":
                yearsAdapter = new ArrayAdapter<>(this, R.layout.simple_list_item_dark, years);
                yearsView.setAdapter(yearsAdapter);
                theme = "dark";
                Log.i("--------------", "theme is now " + theme);
                break;
            case "light":
                yearsAdapter = new ArrayAdapter<>(this, R.layout.simple_list_item_light, years);
                yearsView.setAdapter(yearsAdapter);
                theme = "light";
                Log.i("--------------", "theme is now " + theme);
                break;
            default:
                yearsAdapter = new ArrayAdapter<>(this, R.layout.simple_list_item_light, years);
                yearsView.setAdapter(yearsAdapter);
                theme = "light";
                Log.i("--------------", "theme is now " + theme);
                break;
        }
    }
}
