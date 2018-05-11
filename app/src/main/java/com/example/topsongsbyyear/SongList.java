package com.example.topsongsbyyear;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.util.ArrayList;

public class SongList extends AppCompatActivity implements Response.ErrorListener, Response.Listener<String> {
    String webUrl;
    String year;
    RequestQueue requestQueue;
    StringRequest stringRequest;
    RowItem row;
    ListView list;
    ArrayList<RowItem> rows;
    ArrayList<String> songList;
    ArrayList<String> artistList;
    ArrayList<String> imageUrls;
    ListAdapter customAdapter;
    String theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        year = getIntent().getStringExtra("year");
        theme = getIntent().getStringExtra("theme");
        Log.i("-------------", "Theme: " + theme);
        setTitle("Top Songs of " + year);
        webUrl = "https://www.billboard.com/charts/year-end/" + year + "/hot-100-songs";
        setContentView(R.layout.activity_song_list);
        rows = new ArrayList<RowItem>();
        list = (ListView) findViewById(R.id.all_list);
        //list.setAdapter(customAdapter);
        requestQueue = Volley.newRequestQueue(this);
        stringRequest = new StringRequest(Request.Method.GET, webUrl, this, this);
        requestQueue.add(stringRequest);
        requestQueue.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int lastPos;
        switch (item.getItemId()) {
            case R.id.dark_theme:
                theme = "dark";
                setTheme();
                lastPos = list.getLastVisiblePosition();
                list.setAdapter(customAdapter);
                list.setSelection(lastPos-7);
                Log.i("------------", "Dark theme applied");
                return true;
            case R.id.light_theme:
                theme = "light";
                setTheme();
                lastPos = list.getLastVisiblePosition();
                list.setAdapter(customAdapter);
                list.setSelection(lastPos-7);
                Log.i("------------", "Light theme applied");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.i("----------------", "onErrorResponse executed");
    }

    @Override
    public void onResponse(String response){
        int rank = 1;
        Log.i("----------------", "onResponse executed");
        Document doc = Jsoup.parse(response);
        parseHtml(doc);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void parseHtml(Document doc){
        songList = new ArrayList<>();
        artistList = new ArrayList<>();
        imageUrls = new ArrayList<>();
        rows = new ArrayList<RowItem>();
        Elements songs = doc.select(".ye-chart-item__title");
        for(Element song : songs){
            songList.add(song.text().toString());
        }
        Elements artists = doc.select(".ye-chart-item__artist");
        for(Element artist : artists){
            artistList.add(artist.text().toString());
        }
        Elements images = doc.select("div.ye-chart-item__image > img");
        for (Element image : images) {
            imageUrls.add(image.attr("src"));
        }
        for(int i = 0; i < songList.size(); i++){
            RowItem row = new RowItem(Integer.toString(i+1), songList.get(i), artistList.get(i), imageUrls.get(i));
            rows.add(row);
        }
        Log.i("-------------", "Before we set out theme, with theme: " + theme);
        setTheme();
        list.setAdapter(customAdapter);
        Log.i("----------------","Adapter set after html parsed, with row count " + rows.size());
    }

    void setTheme(){
        switch (theme){
            case "dark":
                Log.i("--------------", "Theme is dark");
                customAdapter = new CustomAdapter(this, R.layout.row_layout_dark, rows, theme);
                break;
            case "light":
                Log.i("--------------", "Theme is light");
                customAdapter = new CustomAdapter(this, R.layout.row_layout_light, rows, theme);
                break;
            default:
                customAdapter = new CustomAdapter(this, R.layout.row_layout_light, rows, theme);
                break;
        }
    }
}
