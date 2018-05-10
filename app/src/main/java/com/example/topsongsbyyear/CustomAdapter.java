package com.example.topsongsbyyear;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<RowItem> {
    Context context;
    TextView songName;
    TextView artist;
    TextView rank;
    ImageView image;
    String theme;

    public CustomAdapter(@NonNull Context context, int resource, ArrayList<RowItem> rows, String theme) {
        super(context, resource, rows);
        this.theme = theme;
        this.context = context;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = convertView;
        if (convertView == null)
            switch(theme){
                case "dark":
                    customView = inflater.inflate(R.layout.row_layout_dark, parent, false);
                    break;
                case "light":
                    customView = inflater.inflate(R.layout.row_layout_light, parent, false);
                    break;
                default:
                    customView = inflater.inflate(R.layout.row_layout_light, parent, false);
                    break;
            }


        String singleSong = getItem(position).getSong();
        String singleArtist = getItem(position).getArtist();
        String singleRank = getItem(position).getRank();
        String singleImage = getItem(position).getPicUrl();
        songName = (TextView) customView.findViewById(R.id.title);
        artist = (TextView) customView.findViewById(R.id.artist);
        rank = (TextView) customView.findViewById(R.id.rank);
        image = (ImageView) customView.findViewById(R.id.list_image);
        songName.setText(singleSong);
        artist.setText(singleArtist);
        rank.setText(singleRank);

        Glide.with(context).
                load(singleImage).
                into(image);

        return customView;
    }

}
