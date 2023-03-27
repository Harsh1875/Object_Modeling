package com.crio.jukebox.entities;

public class SongOwner extends BaseEnitity {
    private String songOwner;

    public SongOwner(String songOwner) {
        this.songOwner = songOwner;
    }

    public String getSongOwner() {
        return songOwner;
    }

    @Override
    public String toString() {
        return this.songOwner;
    }
}
