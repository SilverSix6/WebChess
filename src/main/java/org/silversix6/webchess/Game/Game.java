package org.silversix6.webchess.Game;

import jakarta.validation.constraints.NotNull;
import org.silversix6.webchess.Message;
import org.silversix6.webchess.User;

public class Game {
    static int gameCount = 0;
    int gameId;

    User user1;
    User user2;
    GameState state;
    Board board;

    public Game(User user1, User user2) {
        board = new Board();
        this.user1 = user1;
        this.user2 = user2;
        gameId = gameCount++;
    }

    public Game(User user) {
        board = new Board();
        this.user1 = user;
        state = GameState.GAME_WAITING;
        gameId = gameCount++;
    }

    public void addUser(@NotNull User user) {
        user2 = user;
        state = GameState.GAME_STARTING;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public void broadcast(Message message) {
        System.out.printf("Game (%d) Broadcast message: %s\n", gameId, message.toJson());
        assert user1 != null;
        assert user2 != null;
        user1.message(message);
        user2.message(message);
    }

    public void changeTurn() {
        this.state = this.state == GameState.TURN_1 ? GameState.TURN_2 : GameState.TURN_1;
    }

    @Override
    public String toString() {
        String output = "Game ID: " + gameId + "\n" +
                "Game Count: " + gameCount + "\n";
        if (user1 != null)
            output += "User1: " + user1 + "\n";
        else
            output += "User1: null\n";

        if (user2 != null)
            output += "User2: " + user2 + "\n";
        else
            output += "User2: null\n";

        if (board != null)
            output += "Board: " + board + "\n";
        else
            output += "Board: null\n";

        return output;
    }
}
