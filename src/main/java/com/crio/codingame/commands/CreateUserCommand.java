package com.crio.codingame.commands;

import java.util.List;

import com.crio.codingame.entities.User;
import com.crio.codingame.exceptions.NoSuchCommandException;
import com.crio.codingame.services.IUserService;

public class CreateUserCommand implements ICommand{

    private final IUserService userService;
    
    public CreateUserCommand(IUserService userService) {
        this.userService = userService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute create method of IUserService and print the result.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["CREATE_QUESTION","Ross"]

    // Sample Input "CREATE-USER","name"
    @Override
    public void execute(List<String> tokens) {
        int size = tokens.size();

        for (int i=0; i < size; i=i+2) {
            try {
                if("create-user".equals(tokens.get(i).toLowerCase())) {
                    User user = userService.create(tokens.get(i+1));
                    System.out.println(user);
                }
            } catch (NoSuchCommandException e) {
                System.out.println(e);
            } 
        }
    }
    
}
