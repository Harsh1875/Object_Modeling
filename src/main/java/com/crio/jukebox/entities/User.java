package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;

public class User extends BaseEnitity{
    
    private String userName;
    private List<Playlist> listOfUserPlaylist;

    // Constructor
    public User (User user) {
        this.id = user.id;
        this.userName = user.userName;
        this.listOfUserPlaylist = user.listOfUserPlaylist;
    }

    public User (String id, String userName) {
        this.id = id;
        this.userName = userName;
        this.listOfUserPlaylist = new ArrayList<>();
    }

    public User (String userName) {
        this.userName = userName;
        this.listOfUserPlaylist = new ArrayList<>();
    }

    // Getters
    public String getUserName() {
        return userName;
    }

    public List<Playlist> getListOfUserPlaylist() {
        return listOfUserPlaylist;
    }

    @Override
    public String toString() {
        return this.id + " " + this.userName;
    }
}
