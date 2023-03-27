package com.crio.jukebox.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.crio.jukebox.exceptions.NoSuchCommandException;

public class ControllerInvoker {

    private static final Map<String, IController> controllerMap = new HashMap<>();

    // register the command
    public void register(String commandName, IController controller) {
        controllerMap.put(commandName, controller);
    }

    // get the command
    private IController get(String commandName) {
        return controllerMap.get(commandName);
    }

    // execute the command
    public void executeCommand(String commandName, List<String> tokens) throws NoSuchCommandException {
        IController controller = get(commandName);
        if (controller == null) {
            throw new NoSuchCommandException();
        }
        controller.execute(tokens);
    }
}
