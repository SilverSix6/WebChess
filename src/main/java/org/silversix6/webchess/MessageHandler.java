package org.silversix6.webchess;

import jakarta.websocket.Session;
import org.silversix6.webchess.Database.Leaderboard;
import org.silversix6.webchess.Database.Login;
import org.silversix6.webchess.Game.Game;
import org.silversix6.webchess.Game.GameManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class MessageHandler {

    public static Message process(Message msg, Session session) {
        return switch (msg.messageType) {
            case MessageType.LOGIN -> processLogin(msg, session);
            case MessageType.LEADERBOARD -> processLeaderboard(msg);
            case MessageType.NEW_GAME -> processNewGame(msg, session);
            case MessageType.JOIN_GAME -> processJoinGame(msg, session);
            default -> null;
        };
    }

    private static Message processLogin(Message msg, Session session) {
        boolean loggedIn = Login.login(msg.messages.getFirst(), msg.messages.get(1), session);


        return msg.respond(loggedIn ? "True" : "False");
    }

    private static Message processLeaderboard(Message msg) {
        return msg.respond(Leaderboard.get());
    }

    private static Message processNewGame(Message msg, Session session) {
        Game game = GameManager.newGame(User.users.get(session.getId()));
        List<String> data = new ArrayList<>();
        data.add(String.valueOf(game.getGameId())); // Game ID
        data.add(String.valueOf(0));              // Game not starting

        return msg.respond(data);
    }

    private static Message processJoinGame(Message msg, Session session) {
        Game game;
        if (Objects.equals(msg.messages.getFirst(), ""))
            game = GameManager.joinGame(User.users.get(session.getId()), -1);
        else
            game = GameManager.joinGame(User.users.get(session.getId()), Integer.parseInt(msg.messages.getFirst()));

        if (game == null) {
            return msg.respond("NOTFOUND");
        }

        List<String> data = new ArrayList<>();
        data.add(String.valueOf(game.getGameId()));    // Game ID
        data.add(String.valueOf(1));                 // Game starting

        return msg.respond(data);
    }
}
