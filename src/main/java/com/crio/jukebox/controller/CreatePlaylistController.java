package com.crio.jukebox.controller;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.exceptions.NoSuchCommandException;
import com.crio.jukebox.services.PlayListInterface;

public class CreatePlaylistController implements IController{

    private final PlayListInterface playlistService;

    public CreatePlaylistController(PlayListInterface playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        try {
            int n = tokens.size();
        
            List<String> songsList = new ArrayList<>();

            for (int i=3; i < n; i++) {
                songsList.add(tokens.get(i));
            }
            Playlist playlist = playlistService.create(tokens.get(1), tokens.get(2), songsList);
            System.out.println(playlist);
        } catch (NoSuchCommandException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
