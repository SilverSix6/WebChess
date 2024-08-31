package org.silversix6.webchess.Game;

import org.silversix6.webchess.User;

public class Game {
    static int gameCount = 0;
    int gameId;

    User user1;
    User user2;
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
        gameId = gameCount++;
    }

    public void addUser(User user) {
        user1 = user;
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
}
