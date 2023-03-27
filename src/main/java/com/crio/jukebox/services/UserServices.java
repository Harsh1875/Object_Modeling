package com.crio.jukebox.services;

import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.IUserRepository;

public class UserServices implements UserInterface {
    
    private final IUserRepository userRepository;

    public UserServices (IUserRepository userRepository) {
        this.userRepository = userRepository; 
    }

    public User createUser(String userName) {
        User user = new User(userName);
        return userRepository.saveUser(user);
    }

}
