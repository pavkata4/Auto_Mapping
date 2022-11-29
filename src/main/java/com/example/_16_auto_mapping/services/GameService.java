package com.example._16_auto_mapping.services;

import com.example._16_auto_mapping.entities.Game;

import java.util.List;

public interface GameService {


    String addGame(Game game);

    String editGame(long id, List<String> values);

    String deleteGame(long id);

    void viewAllGames();

    String findGame(String title);
}
