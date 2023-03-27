package com.crio.jukebox.entities;

import java.util.List;

public class ArtistGroup extends SongOwner {
    private String artistGroup;
    private List<Artist> groupOfAtrtist;

    public ArtistGroup(String artistGroup, List<Artist> groupOfArtists) {
        super(artistGroup);
        this.groupOfAtrtist = groupOfArtists;
    }

    public String getArtistGroup() {
        return artistGroup;
    }

    public List<Artist> getGroupOfAtrtist() {
        return groupOfAtrtist;
    }

}
