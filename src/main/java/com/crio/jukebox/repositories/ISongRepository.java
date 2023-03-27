package com.crio.jukebox.repositories;

import java.util.List;
import com.crio.jukebox.entities.Song;

public interface ISongRepository {

    public void addSong(List<String> songList);

    public Song check(String sId);

    public List<Song> convertSong(List<String> songs);
    
}
