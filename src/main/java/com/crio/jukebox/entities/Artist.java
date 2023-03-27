package com.crio.jukebox.entities;

public class Artist extends SongOwner {
    private String artistName;

    public Artist(String artistName) {
        super(artistName);
    }

    public String getArtistName() {
        return artistName;
    }

}
