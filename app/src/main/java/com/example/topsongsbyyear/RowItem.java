package com.example.topsongsbyyear;

public class RowItem {
    private String rank;
    private String song;
    private String artist;
    private String picUrl;

    public RowItem(String rank, String song, String artist, String picUrl){
        this.rank = rank;
        this.song = song;
        this.artist = artist;
        this.picUrl = picUrl;
    }

    public String getRank() {return rank;}
    public String getSong() {return song;}
    public String getArtist() {return artist;}
    public String getPicUrl() {return picUrl;}

}
