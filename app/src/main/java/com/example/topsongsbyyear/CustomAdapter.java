package com.example.topsongsbyyear;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.Log;
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

    public CustomAdapter(@NonNull Context context, int resource, ArrayList<RowItem> rows) {
        super(context, resource, rows);
        this.context = context;
        notifyDataSetChanged();
        //setData(rows);
        Log.i("--------------", "row filled with " + getCount() + " elements");
    }

//    public void setData(ArrayList<RowItem> rows){
//        //addAll(rows);
//        Log.i("----------------", "setData called, cleared and then addAll(rows) with row count: " + rows.size());
//        notifyDataSetChanged();
//    }


    @Override
    public int getCount() {
        Log.i("----------------", "CustomAdapter getCount called, returning rows.size() which is: " + super.getCount());
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("---------------", "entered getView function");
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = convertView;
        if (convertView == null)
            customView = inflater.inflate(R.layout.row_layout, parent, false);

        String singleSong = getItem(position).getSong();
        String singleArtist = getItem(position).getArtist();
        String singleRank = getItem(position).getRank();
        String singleImage = getItem(position).getPicUrl();
        songName = (TextView) customView.findViewById(R.id.title);
        artist = (TextView) customView.findViewById(R.id.artist);
        rank = (TextView) customView.findViewById(R.id.rank);
        Log.i("----------------", "ADDED: " + singleRank + " " + singleSong + " by " + singleArtist +" TO LIST");
        image = (ImageView) customView.findViewById(R.id.list_image);
        //image.setImageResource(R.drawable.black_keys_turn_blue_album_cover);
        songName.setText(singleSong);
        artist.setText(singleArtist);
        rank.setText(singleRank);

        Glide.with(context).
                load(singleImage).
                into(image);

        return customView;
    }

}
