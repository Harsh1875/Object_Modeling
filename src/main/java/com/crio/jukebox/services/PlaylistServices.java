package com.crio.jukebox.services;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.NoSuchCommandException;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;

public class PlaylistServices implements PlayListInterface {

    private final IUserRepository userRepository;
    private final IPlaylistRepository playlistRepository;
    private final ISongRepository songRepository;
    private Playlist currSongPlaylist;
    private Song currSong;

    public PlaylistServices (IUserRepository userRepository, IPlaylistRepository playlistRepository, ISongRepository songRepository) {
        this.userRepository = userRepository;
        this.playlistRepository = playlistRepository;
        this.songRepository = songRepository;
    }

    @Override
    public Playlist create(String id, String playlistName, List<String> songsId) throws NoSuchCommandException{
        List<Song> songs = validateSongs(songsId);
        User user = userRepository.findById(id);
        Playlist playlist = new Playlist(user, playlistName, songs);
        return playlistRepository.create(playlist);
    }

    private List<Song> validateSongs(List<String> songsId) throws NoSuchCommandException {
        List<Song> songs = new ArrayList<>();
        for (String it : songsId) {
            Song songValidate = songRepository.check(it);
            if (songValidate == null) {
                throw new NoSuchCommandException("Some Requested Songs Not Available. Please try again.");
            }
            songs.add(songValidate);
        }
        return songs;
    }

    @Override
    public boolean delete(String id, String pid) {
        if (findPlaylist(pid) == null) {
            return false;
        }
        User user = userRepository.findById(id);
        return playlistRepository.delete(user , pid);
    }

    public Playlist findPlaylist(String pid) {
        return playlistRepository.findPlaylist(pid);
    }

    @Override
    public Playlist modifyAdd(String id, String pid, List<String> songsId) throws NoSuchCommandException{
        User user = userRepository.findById(id);
        List<Song> conversion = validateSongs(songsId);
        Playlist playlist = playlistRepository.modifyAdd(user, pid, conversion);
        return playlist;
    }

    @Override
    public Playlist modifyDelete(String id, String pid, List<String> songsId) throws NoSuchCommandException{
        User user = userRepository.findById(id);
        List<Song> songs = validationOfDeleteSong(songsId, pid);
        if (songs == null) {
            throw new NoSuchCommandException("Some Requested Songs for Deletion are not present in the playlist. Please try again.");
        }
        Playlist playlist = playlistRepository.modifyDelete(user, pid, songs);
        return playlist;
    }

    private List<Song> validationOfDeleteSong(List<String> songsId, String pid) {
        List<Song> songs = songRepository.convertSong(songsId);
        Playlist playlistCheck = playlistRepository.findPlaylist(pid);
        for (Song it : songs) {
            if (!playlistCheck.getSongsId().contains(it)) {
                return null;
            }
        }
        return songs;
    }

    @Override
    public Song playthePlaylist(String user_id, String pid) throws NoSuchCommandException{
        Playlist playlist = playlistRepository.findPlaylist(pid);
        List<Song> totalSongs = playlist.getSongsId();
        if (totalSongs == null) {
            throw new NoSuchCommandException("Playlist is empty");
        }
        Song song = totalSongs.get(0);
        currSongPlaylist = playlist;
        currSong = song;
        return song;
    }

    @Override
    public Song playPreviousSong(String user_id) throws NoSuchCommandException {
        if (currSongPlaylist == null) {
            throw new NoSuchCommandException("Please first select Playlist to play the song.");
        }
        List<Song> totalSongs = currSongPlaylist.getSongsId();
        int n = totalSongs.size();
        Song prevSong = totalSongs.get(n-1);

        for (int i=0; i < n; i++) {
            if (i == 0 && currSong == totalSongs.get(i)) {
                currSong = prevSong;
                return prevSong;
            }
            else if (i == 0) {
                prevSong = totalSongs.get(0);
            }
            else {
                if (currSong == totalSongs.get(i)) {
                    currSong = prevSong;
                    return prevSong;
                }
                prevSong = totalSongs.get(i);
            }
        }
        return null;
    }

    @Override
    public Song playNextSong(String user_id) throws NoSuchCommandException {
        if (currSongPlaylist == null) {
            throw new NoSuchCommandException("Please first select Playlist to play the song.");
        }
        List<Song> totalSongs = currSongPlaylist.getSongsId();
        int n = totalSongs.size();
        Song nextSong;

        for (int i=0; i < n; i++) {
            if (i == n-1 && currSong == totalSongs.get(i)) {
                nextSong = totalSongs.get(0);
                currSong = nextSong;
                return nextSong;
            }
            else {
                if (currSong == totalSongs.get(i)) {
                    nextSong = totalSongs.get(i+1);
                    currSong = nextSong;
                    return nextSong;
                }
            }
        }
        return null;
    }

    @Override
    public Song playSelectSong(String user_id, String songId) throws NoSuchCommandException {
        Song song_id = songRepository.check(songId);
        List<Song> totalSongs = currSongPlaylist.getSongsId();
        if (!totalSongs.contains(song_id)) {
            throw new NoSuchCommandException("Given song id is not a part of the active playlist");
        }
        int n = totalSongs.size();
        for (int i=0; i < n; i++) {
            if (song_id == totalSongs.get(i)) {
                currSong = totalSongs.get(i);
                return currSong;
            }
        }
        return null;
    }
    

    
}
