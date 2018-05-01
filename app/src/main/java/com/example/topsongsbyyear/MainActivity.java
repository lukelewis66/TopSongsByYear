package com.example.topsongsbyyear;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView yearsView;
    private ArrayAdapter<String> yearsAdapter;
    ArrayList<String> years;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        yearsView = findViewById(R.id.yearsView);
        for(int i = 2006; i < 2018 ; i++){
            years.add(Integer.toString(i));
        }
        yearsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, years);
        yearsView.setAdapter(yearsAdapter);
        yearsView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String year = yearsAdapter.getItem(position);


    }

}
