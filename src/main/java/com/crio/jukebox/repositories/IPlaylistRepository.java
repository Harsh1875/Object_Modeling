package com.crio.jukebox.repositories;


import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;

public interface IPlaylistRepository {
    public Playlist create(Playlist entity);
    public boolean delete(User id, String pid);
    public Playlist findPlaylist(String pid);
    public Playlist modifyAdd(User user, String pid, List<Song> songs);
    public Playlist modifyDelete(User user, String pid, List<Song> songsId);
}
