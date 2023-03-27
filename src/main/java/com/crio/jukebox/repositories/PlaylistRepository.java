package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;

public class PlaylistRepository implements IPlaylistRepository {

    private final HashMap<String,Playlist> playlistMap;
    private Integer autoPid = 0;

    public PlaylistRepository() {
        this.playlistMap = new HashMap<>();
    }

    public PlaylistRepository(HashMap<String,Playlist> playlistMap) {
        this.playlistMap = playlistMap;
        this.autoPid = playlistMap.size();
    }

    @Override
    public Playlist create(Playlist entity) {
        autoPid++;
        Playlist pl = new Playlist(entity.getUser(), entity.getPlaylistName(), entity.getSongsId(), Integer.toString(autoPid));
        playlistMap.put(pl.getPid(), pl);
        return pl;
    }

    @Override
    public boolean delete(User id, String pid) {
        for (Map.Entry<String,Playlist> it : playlistMap.entrySet()) {
            String key = it.getKey();
            User user = it.getValue().getUser();
            if (key.equals(pid) && user == id) {
                return playlistMap.remove(it.getKey(), it.getValue());
            }
        }
        return false;
    }
    
    @Override
    public Playlist modifyAdd(User user, String pid, List<Song> songs) {
        Playlist addSongs = playlistMap.get(pid);
        List<Song> songOfPid = addSongs.getSongsId();
        for (Song it : songs) {
            if (!songOfPid.contains(it)) {
                songOfPid.add(it);
            }
        }
        Playlist addTheSong = new Playlist(user, addSongs.getPlaylistName(), songOfPid, pid);
        playlistMap.put(addTheSong.getPid(), addTheSong);
        return addTheSong;
    }

    public Playlist modifyDelete(User user, String pid, List<Song> songsId) {
        Playlist deleteSongs = playlistMap.get(pid);
        List<Song> songOfPid = deleteSongs.getSongsId();
        for (Song it : songsId) {
            songOfPid.remove(it);
        }
        Playlist playlist = new Playlist(user, deleteSongs.getPlaylistName(), songOfPid, pid);
        playlistMap.put(playlist.getPid(), playlist);
        return playlist;
    }

    public Playlist findPlaylist(String pid) {
        return playlistMap.get(pid);
    }
    
}
