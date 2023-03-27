package com.crio.jukebox.controller;

import java.util.List;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.services.UserInterface;

public class CreateUserController implements IController {

    private final UserInterface userService;

    public CreateUserController(UserInterface userService) {
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens) {
        User user = userService.createUser(tokens.get(1));
        System.out.println(user);
    }
    
}
