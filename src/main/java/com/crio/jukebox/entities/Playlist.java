package com.crio.jukebox.entities;

import java.util.List;

public class Playlist extends BaseEnitity{
    private User user;
    private String pid;
    private String playlistName;
    private List<Song> songsId;

    // Constructors
    public Playlist (User id, String playlistName, List<Song> songsId) {
        this.user = id;
        this.playlistName = playlistName;
        this.songsId = songsId;
    }

    public Playlist (User id, String playlistName, List<Song> songsId, String pid) {
        this.user = id;
        this.playlistName = playlistName;
        this.songsId = songsId;
        this.pid = pid;
    }

    // Getters
    public String getPlaylistName() {
        return playlistName;
    }

    public List<Song> getSongsId() {
        return songsId;
    }
    
    public String getAllSongsIdinString(List<Song> songs) {
        StringBuilder sb = new StringBuilder();
        for (Song it : songs) {
            sb.append(" " + it.getSongId());
        }
        return sb.toString().trim();
    }

    // Setter for adding songs in the playlist
    public void addSongs(List<Song> addedSongs) {
        for (Song it : addedSongs) {
            this.songsId.add(it);
        }
    }

    public String getPid() {
        return pid;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString () {
        return "Playlist ID - " + this.pid;
    }

}
