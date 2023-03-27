package com.crio.jukebox.controller;

import java.util.List;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exceptions.NoSuchCommandException;
import com.crio.jukebox.services.PlayListInterface;

public class PlayPlaylistController implements IController{

    private final PlayListInterface playlistService;

    public PlayPlaylistController(PlayListInterface playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        try {
            String user = tokens.get(1);
            String pID = tokens.get(2);
            Song currSong = playlistService.playthePlaylist(user, pID);
            System.out.println("Current Song Playing");
            System.out.println("Song  - " + currSong.getSongName());
            System.out.println("Album - " + currSong.getAlbumName(currSong.getAlbum()));
            System.out.println("Artists - " + currSong.getArtistwithFeat(currSong.getFeatArtist()));
        } catch (NoSuchCommandException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
