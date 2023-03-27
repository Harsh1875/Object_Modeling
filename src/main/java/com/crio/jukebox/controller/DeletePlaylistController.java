package com.crio.jukebox.controller;

import java.util.List;
import com.crio.jukebox.exceptions.NoSuchCommandException;
import com.crio.jukebox.services.PlayListInterface;

public class DeletePlaylistController implements IController{
    
    private final PlayListInterface playlistService;

    public DeletePlaylistController(PlayListInterface playListService) {
        this.playlistService = playListService;
    }


    @Override
    public void execute(List<String> tokens) {
        try {
            String userId = tokens.get(1);
            String playlistId = tokens.get(2);
            Boolean deletePlaylist = playlistService.delete(userId, playlistId);
            if (deletePlaylist == true) {
                System.out.println("Delete Successful");
            } else {
                System.out.println("Playlist Not Found");
            }
        } catch (NoSuchCommandException e) {
            System.out.println(e.getMessage());
        }
    }


}
