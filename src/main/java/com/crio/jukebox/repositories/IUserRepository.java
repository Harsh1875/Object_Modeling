package com.crio.jukebox.repositories;

import com.crio.jukebox.entities.User;

public interface IUserRepository {
    public User saveUser(User user);

    public User findById(String id);
}
