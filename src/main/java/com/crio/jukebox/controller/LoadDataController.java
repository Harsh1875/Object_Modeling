package com.crio.jukebox.controller;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.List;
import com.crio.jukebox.repositories.ISongRepository;

public class LoadDataController implements IController{

    private final ISongRepository songRepository;

    public LoadDataController(ISongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public void execute(List<String> tokens) {
        try {
            BufferedReader reader;
            reader = new BufferedReader(new FileReader(tokens.get(1)));
            String line = reader.readLine();
            while (line != null) {
                List<String> songList = Arrays.asList(line.split(","));
                songRepository.addSong(songList);
                line = reader.readLine();
            }
            reader.close();
            System.out.println("Songs Loaded successfully");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }
    
}
