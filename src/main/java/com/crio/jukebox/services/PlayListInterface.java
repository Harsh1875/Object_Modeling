package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exceptions.NoSuchCommandException;

public interface PlayListInterface {
    public Playlist create(String id, String playlistName, List<String> songsId) throws NoSuchCommandException;
    public boolean delete(String id, String pid);
    public Playlist findPlaylist(String pid);
    public Playlist modifyAdd(String id, String pid, List<String> songsId) throws NoSuchCommandException;
    public Playlist modifyDelete(String id, String pid, List<String> songsId) throws NoSuchCommandException;
    public Song playthePlaylist(String user_id, String pid) throws NoSuchCommandException;
    public Song playPreviousSong(String user_id) throws NoSuchCommandException;
    public Song playNextSong(String user_id) throws NoSuchCommandException;
    public Song playSelectSong(String user_id, String songId) throws NoSuchCommandException;
}
