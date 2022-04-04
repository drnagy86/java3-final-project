package com.nagy.songs;

public class Song implements Comparable<Song> {
    private int songId;
    private String name;
    private String artist;
    private String youtube;

    private static int AUTO_INCREMENT_ID = 1;

    public Song(String name, String artist, String youtube) {
        this.songId = AUTO_INCREMENT_ID;
        AUTO_INCREMENT_ID++;
        this.name = name;
        this.artist = artist;
        this.youtube = youtube;
    }

    public int getSongId() {
        return songId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }


    @Override
    public int compareTo(Song o) {
        int result = this.artist.compareTo(o.artist);
        if (result == 0){
             result = this.name.compareTo(o.name);
        }
        return result;
    }
}
