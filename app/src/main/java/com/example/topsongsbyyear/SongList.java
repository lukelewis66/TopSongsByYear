package com.example.topsongsbyyear;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.commons.lang3.StringUtils.*;


import java.util.ArrayList;

public class SongList extends AppCompatActivity implements Response.ErrorListener, Response.Listener<String> {
    String webUrl;
    String year;
    RequestQueue requestQueue;
    StringRequest stringRequest;
    ArrayList<RowItem> allRows;
    RowItem row;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        year = getIntent().getStringExtra("year");
        webUrl = "https://www.billboard.com/charts/year-end/" + year + "/hot-100-songs";
        setContentView(R.layout.activity_song_list);
        requestQueue = Volley.newRequestQueue(this);
        stringRequest = new StringRequest(Request.Method.GET, webUrl, this, this);
        //create adapter and then set listview to it
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(String response){
        int rank = 1;
        int pos = 0;
        while(pos != -1){
            pos = response.indexOf("ye-chart-item__image", pos);
            int startPic = response.indexOf("https://", pos);
            int endPic = response.lastIndexOf("\"", startPic);
            String picUrl = response.substring(startPic, endPic); //picUrl extracted
            int startSong = response.indexOf("ye-chart-item__title", pos);
            startSong += 22;
            int endSong = response.indexOf("<", startSong);
            String song = response.substring(startSong, endSong);
            int startArtist = response.indexOf("ye-chart-item__artist", pos);
            startArtist += 23;
            int endArtist = response.indexOf("<", startArtist);
            String artist = response.substring(startArtist, endArtist);
            row = new RowItem(Integer.toString(rank), song, artist, picUrl);
            allRows.add(row);
            rank++;
        }
        //call setData function in adapter
        //in setData, save the data and call notifyDataSetChanged (adapter method), which updates list view
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
