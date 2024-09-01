package org.silversix6.webchess.Game;

import jakarta.validation.constraints.NotNull;
import org.silversix6.webchess.Message;
import org.silversix6.webchess.MessageType;
import org.silversix6.webchess.User;

import java.util.*;

public class GameManager {

    static List<Game> games = new ArrayList<>();
    static Queue<Game> matching = new LinkedList<>();

    /**
    *   Creates a new game and adds the game to the match making pool
    *
    *   @param user     The user that is requesting a new game
    *   @return         The game that has been created
     */
    public static Game newGame(User user) {
        Game game = new Game(user);

        games.add(game);
        matching.add(game);

        return game;
    }

    /**
     *  Finds a game for the given user to join. If requestedGame is >= 0
     *  it will find a game with the given ID. If the game is not found it will return null.
     *  If there are no games to join a new one will be made.
     *  The user is then added to the game and the game is started
     *
     *  @param user             The User joining a game
     *  @param requestedGame    -1: Not looking for a game<br>
     *                          >=0: The Game ID
     *  @return                 The game that it has joined <br> Null if the requested game is not found
     */
    public static Game joinGame(@NotNull User user, int requestedGame) {
        Game game;
        // if game is request by id
        if (requestedGame != -1) {
            game = findGame(requestedGame);

            if (game == null)
                return null;
        } else {
            if (matching.isEmpty())
                return newGame(user);

            game = matching.poll();
        }

        game.addUser(user);
        start(game);
        return game;
    }


    /**
     * Starts a given game by informing the two users that the game is starting.
     * The userID of the starting user is sent to both users.
     * @param game The game being started
     */
    private static void start(@NotNull Game game) {
        List<String> data = new ArrayList<String>();

        data.add(String.valueOf(game.user1.getUserId()));
        game.broadcast(new Message(data, MessageType.GAME_START));

        game.state = GameState.TURN_1;
    }

    /**
     *  Validates and performs a given move.
     *  <p>
     *  If valid both users are informed that it is valid and the players turn is switched
     *
     *
     * @param user  The user requesting to perform a move
     * @param move  The requested move
     * @return      True: The move is valid and both users have been informed <br>
     *              False: The move is invalid
     * @see Game
     * @see Move
     * @see User
     */
    public static boolean processMove(@NotNull User user, @NotNull Move move) {
        Game game = findGame(user);
        assert game != null;

        //  If current player turn
        System.out.println(game);

        if (game.user1 == user && game.state == GameState.TURN_1 || game.user2 == user && game.state == GameState.TURN_2 ) {

            // validate move

            if (!move.valid(game, user)) {
                return false;
            }

            // send move to both players
            game.broadcast(new Message(move));

            // update server game board
            game.board.move(move);

            // change turns
            game.changeTurn();
            List<String> data = new ArrayList<>();
            data.add(String.valueOf(game.state == GameState.TURN_1 ? game.user1.getUserId() : game.user2.getUserId()));

            game.broadcast(new Message(data, MessageType.TURN));
            return true;
        }

        return false;
    }

    public static void removeGame(User user) {
        games.removeIf(game -> Objects.equals(game.user1, user) || Objects.equals(game.user2, user));
    }

    private static Game findGame(User user) {
        for (Game game: games) {
            if (Objects.equals(game.user1, user) || Objects.equals(game.user2, user)) {
                return game;
            }
        }
        return null;
    }

    private static Game findGame(int gameId) {
        for (Game game: games) {
            if (game.gameId == gameId) {
                return game;
            }
        }
        return null;
    }
}
