package com.crio.jukebox.controller;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.exceptions.NoSuchCommandException;
import com.crio.jukebox.services.PlayListInterface;

public class ModifyPlaylistController implements IController{

    private final PlayListInterface playlistService;

    public ModifyPlaylistController(PlayListInterface playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        try {
            String modificationType = tokens.get(1);
            int n = tokens.size();

            if ("add-song".equalsIgnoreCase(modificationType)) {
                String userId = tokens.get(2);
                String playlistId = tokens.get(3);
                List<String> songId = new ArrayList<>();
                for (int i=4; i<n; i++) {
                    songId.add(tokens.get(i));
                }
                Playlist addSongs = playlistService.modifyAdd(userId, playlistId, songId);
                System.out.println("Playlist ID - " + addSongs.getPid());
                System.out.println("Playlist Name - " + addSongs.getPlaylistName());
                System.out.println("Song IDs - " + addSongs.getAllSongsIdinString(addSongs.getSongsId()));
            }
            else if ("delete-song".equalsIgnoreCase(modificationType)) {
                String userId = tokens.get(2);
                String playlistId = tokens.get(3);
                List<String> songId = new ArrayList<>();
                for (int i=4; i<n; i++) {
                    songId.add(tokens.get(i));
                }
                Playlist deleteSongs = playlistService.modifyDelete(userId, playlistId, songId);
                System.out.println("Playlist ID - " + deleteSongs.getPid());
                System.out.println("Playlist Name - " + deleteSongs.getPlaylistName());
                System.out.println("Song IDs - " + deleteSongs.getAllSongsIdinString(deleteSongs.getSongsId()));
            }
        } catch (NoSuchCommandException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
}
