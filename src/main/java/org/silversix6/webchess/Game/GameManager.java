package org.silversix6.webchess.Game;

import org.silversix6.webchess.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameManager {

    List<Game> games;
    List<Game> matching;

    public GameManager() {
        games = new ArrayList<>();
        matching = new ArrayList<>();
    }

    private Game joinGame(User user) {

        Game game;

        if (matching.isEmpty()) {
            // No games waiting on player
            game = new Game(user);
            matching.add(game);
        } else {
            // Use pre-existing game
            game = matching.removeLast();
            game.user2 = user;
            games.add(game);
        }

        return game;
    }

    public Game getGameInstance(int gameId) {
        for (Game game : games) {
            if (game.gameId == gameId)
                return game;
        }

        return null;
    }

    public Game getGameInstance(User user) {
        for (Game game : games) {
            if (Objects.equals(game.user1, user) || Objects.equals(game.user2, user))
                return game;
        }

        return joinGame(user);
    }

}
