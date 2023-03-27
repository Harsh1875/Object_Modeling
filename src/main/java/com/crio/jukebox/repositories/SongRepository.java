package com.crio.jukebox.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.SongOwner;

public class SongRepository implements ISongRepository{
    private final Map<String, Song> songMap;

    public SongRepository() {
        this.songMap = new HashMap<>();
    }

    public SongRepository(Map<String, Song> songMap) {
        this.songMap = songMap;
    }

    public void addSong(List<String> songList) {
        String songId = songList.get(0);
        String songName = songList.get(1);
        String songGenre = songList.get(2);
        String albumName = songList.get(3);
        String artistName = songList.get(4);
        List<String> allArtist = Arrays.asList(songList.get(5).split("#"));
        List<Artist> featArtist = new ArrayList<>();

        SongOwner songOwner = new Artist(artistName);  
        for (String it : allArtist) {
            Artist artist = new Artist(it);
            featArtist.add(artist);
        }
        Song song = new Song(songId, songName, songGenre, songOwner);
        Album album = new Album(albumName, song);
        Song songAlbum = new Song(album, song, featArtist);
        songMap.put(songAlbum.getSongId(), songAlbum); 
    }

    public Song check(String sId) {
        return songMap.get(sId);
    }

    @Override
    public List<Song> convertSong(List<String> songs) {
        List<Song> songsId = new ArrayList<>();
        for (String it : songs) {
            songsId.add(songMap.get(it));
        }
        return songsId;
    }
}
