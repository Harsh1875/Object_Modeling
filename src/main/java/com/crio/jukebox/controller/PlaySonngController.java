package com.crio.jukebox.controller;

import java.util.List;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exceptions.NoSuchCommandException;
import com.crio.jukebox.services.PlayListInterface;

public class PlaySonngController implements IController {

    private final PlayListInterface playlistService;

    public PlaySonngController(PlayListInterface playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        try {
            String user_id = tokens.get(1);
            String switchSong = tokens.get(2);
    
            if ("back".equalsIgnoreCase(switchSong)) {
                Song prevSong = playlistService.playPreviousSong(user_id);
                System.out.println("Current Song Playing");
                System.out.println("Song  - " + prevSong.getSongName());
                System.out.println("Album - " + prevSong.getAlbumName(prevSong.getAlbum()));
                System.out.println("Artists - " + prevSong.getArtistwithFeat(prevSong.getFeatArtist()));
            }
            else if ("next".equalsIgnoreCase(switchSong)) {
                Song nextSong = playlistService.playNextSong(user_id);
                System.out.println("Current Song Playing");
                System.out.println("Song  - " + nextSong.getSongName());
                System.out.println("Album - " + nextSong.getAlbumName(nextSong.getAlbum()));
                System.out.println("Artists - " + nextSong.getArtistwithFeat(nextSong.getFeatArtist()));
            }
            else{
                Song selectedSong = playlistService.playSelectSong(user_id, switchSong);
                System.out.println("Current Song Playing");
                System.out.println("Song  - " + selectedSong.getSongName());
                System.out.println("Album - " + selectedSong.getAlbumName(selectedSong.getAlbum()));
                System.out.println("Artists - " + selectedSong.getArtistwithFeat(selectedSong.getFeatArtist()));
            }
        } catch (NoSuchCommandException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
