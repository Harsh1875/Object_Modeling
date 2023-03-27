package com.crio.jukebox.entities;

import java.util.List;

public class Song extends BaseEnitity{
    
    private String songId;
    private String songName;
    private String songGenre;
    private Album album;
    private SongOwner songOwner;
    private List<Artist> featArtist;

    public Song (Album album, Song song, List<Artist> featArtist) {
        this.album = album;
        this.songId = song.songId;
        this.songName = song.songName;
        this.songGenre = song.songGenre;
        this.songOwner = song.songOwner;
        this.featArtist = featArtist;
    }

    public Song (String songId, String songName, String songGenre, SongOwner songOwner) {
        this.songId = songId;
        this.songName = songName;
        this.songGenre = songGenre;
        this.songOwner = songOwner;
    }
    
    public String getSongId() {
        return songId;
    }
    public SongOwner getSongOwner() {
        return songOwner;
    }
    public String getSongName() {
        return songName;
    }
    public String getSongGenre() {
        return songGenre;
    }

    public Album getAlbum() {
        return album;
    }

    public String getAlbumName(Album album) {
        return album.getAlbumName();
    }

    public List<Artist> getFeatArtist() {
        return featArtist;
    }
    
    public String getArtistwithFeat(List<Artist> featArtist) {
        StringBuilder sb = new StringBuilder();
        for (Artist it : featArtist) {
            sb.append(it + ",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
