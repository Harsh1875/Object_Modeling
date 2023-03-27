package com.crio.jukebox.repositories;

import java.util.HashMap;
import com.crio.jukebox.entities.User;

public class UserRepository implements IUserRepository {
    
    private final HashMap<String, User> userRepositoryMap;
    private Integer autoId = 0;

    public UserRepository() {
        this.userRepositoryMap = new HashMap<>();
    }

    public UserRepository (HashMap<String, User> userRepositoryMap) {
        this.userRepositoryMap = userRepositoryMap;
        this.autoId = userRepositoryMap.size();
    }

    public User saveUser(User entity) {
        if (entity.getId() == null) {
            autoId++;
            User user = new User(Integer.toString(autoId), entity.getUserName());
            userRepositoryMap.put(user.getId(), user);
            return user;
        }
        userRepositoryMap.put(entity.getId(), entity);
        return entity;
    }

    public User findById(String id) {
        return userRepositoryMap.get(id);
    }
}
