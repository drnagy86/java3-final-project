package com.nagy.songs;

import java.util.ArrayList;
import java.util.List;

public class PlayList {
    private List<Song> playList;

    private static Song SONG_1 = new Song("Barbie Girl", "Aqua", "ZyhrYis509A");
    private static Song SONG_2 = new Song("Truly Madly Deeply", "Savage Garden", "WQnAxOQxQIU");
    private static Song SONG_3 = new Song("Ironic", "Alanis Morissette", "Jne9t8sHpUc");
    private static Song SONG_4 = new Song("Kiss Me", "Sixpence None the Richer", "8N-qO3sPMjc");
    private static Song SONG_5 = new Song("One Headlight", "The Wallflowers", "Zzyfcys1aLM");
    private static Song SONG_6 = new Song("A Long December", "Counting Crows", "1D5PtyrewSs");
    private static Song SONG_7 = new Song("November Rain", "Guns N'Roses", "8SbUC-UaAxE");


    public PlayList() {
        playList = new ArrayList<>();
        playList.add(SONG_1);
        playList.add(SONG_2);
        playList.add(SONG_3);
        playList.add(SONG_4);
        playList.add(SONG_5);
        playList.add(SONG_6);
        playList.add(SONG_7);
    }

    public PlayList(Song song){
        playList = new ArrayList<>();
        playList.add(song);
    }

    public Song addSong(Song song){
        playList.add(song);
        return song;
    }







}
