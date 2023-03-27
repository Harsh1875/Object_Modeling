package com.crio.jukebox.appConfig;

import com.crio.jukebox.controller.ControllerInvoker;
import com.crio.jukebox.controller.CreatePlaylistController;
import com.crio.jukebox.controller.CreateUserController;
import com.crio.jukebox.controller.DeletePlaylistController;
import com.crio.jukebox.controller.LoadDataController;
import com.crio.jukebox.controller.ModifyPlaylistController;
import com.crio.jukebox.controller.PlayPlaylistController;
import com.crio.jukebox.controller.PlaySonngController;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.repositories.PlaylistRepository;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.repositories.UserRepository;
import com.crio.jukebox.services.PlayListInterface;
import com.crio.jukebox.services.PlaylistServices;
import com.crio.jukebox.services.UserInterface;
import com.crio.jukebox.services.UserServices;

public class ApplicationConfig {
    
    // Initialize Data Repository
    private final IUserRepository userRepository = new UserRepository();
    private final IPlaylistRepository playlistRepository = new PlaylistRepository();
    private final ISongRepository songRepository = new SongRepository();

    // Initialize services with dependency
    private final UserInterface userService = new UserServices(userRepository);
    private final PlayListInterface playlistService = new PlaylistServices(userRepository, playlistRepository, songRepository);

    // Initialize the command and add the service dependency
    private final LoadDataController loadDataController = new LoadDataController(songRepository);
    private final CreateUserController createUserController = new CreateUserController(userService);
    private final CreatePlaylistController createPlaylistController = new CreatePlaylistController(playlistService);
    private final DeletePlaylistController deletePlaylistController = new DeletePlaylistController(playlistService);
    private final ModifyPlaylistController modifyPlaylistController = new ModifyPlaylistController(playlistService);
    private final PlayPlaylistController playPlaylistController = new PlayPlaylistController(playlistService);
    private final PlaySonngController playSonngController = new PlaySonngController(playlistService);

    // Initialize the Controller Invoker
    private final ControllerInvoker controllerInvoker = new ControllerInvoker();

    public ControllerInvoker getControllerInvoker() {
        controllerInvoker.register("LOAD-DATA", loadDataController);
        controllerInvoker.register("CREATE-USER", createUserController);
        controllerInvoker.register("CREATE-PLAYLIST", createPlaylistController);
        controllerInvoker.register("DELETE-PLAYLIST", deletePlaylistController);
        controllerInvoker.register("PLAY-PLAYLIST", playPlaylistController);
        controllerInvoker.register("MODIFY-PLAYLIST", modifyPlaylistController);
        controllerInvoker.register("PLAY-SONG", playSonngController);

        return controllerInvoker;
    }
}
