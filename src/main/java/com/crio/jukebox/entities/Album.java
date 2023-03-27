package com.crio.jukebox.entities;

import java.util.List;

public class Album extends BaseEnitity{
    private String albumName;
    private Song songAlbum;
    private List<Song> albumContainsallSong;

    public Album (String albumName, Song song) {
        this.albumName = albumName;
        this.songAlbum = song;        
    }

    public String getAlbumName() {
        return albumName;
    }
    
    public Song getSongAlbum() {
        return songAlbum;
    }

    public List<Song> getAlbumContainsallSong() {
        return albumContainsallSong;
    }
 
    @Override
    public String toString() {
        return this.albumName;
    }
}
