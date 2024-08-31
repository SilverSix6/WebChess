package org.silversix6.webchess.Game;

import org.silversix6.webchess.User;

import java.util.ArrayList;
import java.util.List;

public class GameManager {

    static List<Game> games = new ArrayList<>();
    static List<Game> matching = new ArrayList<>();

    public static Game joinGame(User user, int n) {
        if (matching.isEmpty()) {
            return newGame(user);
        }

        // Find requested game
        if (n != -1) {
            for (Game game: matching) {
                if (game.gameId == n) {
                    matching.remove(game);
                    return game;
                }
            }
            return null;
        }

        Game game = matching.removeLast();
        game.addUser(user);
        return game;
    }

    public static Game newGame(User user) {
        Game game = new Game(user);

        games.add(game);
        matching.add(new Game(user));

        return game;
    }
}
