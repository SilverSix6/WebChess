package org.silversix6.webchess;

import jakarta.websocket.Session;
import org.silversix6.webchess.Database.Leaderboard;
import org.silversix6.webchess.Database.UserDatabase;
import org.silversix6.webchess.Game.Game;
import org.silversix6.webchess.Game.GameManager;
import org.silversix6.webchess.Game.Move;

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
            case MessageType.SIGNUP -> processSignUp(msg, session);
            case MessageType.MOVE ->  processMove(msg, session);
            default -> null;
        };
    }

    private static Message processLogin(Message msg, Session session) {
        int userId = UserDatabase.login(msg.messages.getFirst(), msg.messages.get(1), session);

        List<String> data = new ArrayList<>();
        data.add(userId != 0 ? "True" : "False");
        data.add(String.valueOf(userId));

        return msg.respond(data);
    }

    private static Message processLeaderboard(Message msg) {
        return msg.respond(Leaderboard.get());
    }

    private static Message processNewGame(Message msg, Session session) {
        Game game = GameManager.newGame(org.silversix6.webchess.User.users.get(session.getId()));
        List<String> data = new ArrayList<>();
        data.add(String.valueOf(game.getGameId())); // Game ID
        data.add(String.valueOf(0));              // Game not starting

        return msg.respond(data);
    }

    private static Message processJoinGame(Message msg, Session session) {
        Game game;
        if (Objects.equals(msg.messages.getFirst(), ""))
            game = GameManager.joinGame(org.silversix6.webchess.User.getUser(session), -1);
        else
            game = GameManager.joinGame(org.silversix6.webchess.User.getUser(session), Integer.parseInt(msg.messages.getFirst()));

        if (game == null) {
            return msg.respond("NOTFOUND");
        }

        List<String> data = new ArrayList<>();
        data.add(String.valueOf(game.getGameId()));    // Game ID
        data.add(String.valueOf(1));                 // Game starting

        return msg.respond(data);
    }

    private static Message processSignUp(Message msg, Session session) {
        String username = msg.messages.getFirst();
        String password = msg.messages.get(1);

        if (username.isEmpty() || password.isEmpty()) {
            return msg.respond("FALSE");
        } else {
            UserDatabase.add(username, password);
            return msg.respond("True");
        }

    }

    private static Message processMove(Message msg, Session session) {
        Move move = new Move(msg.messages);

        boolean valid = GameManager.processMove(User.getUser(session), move);

        return msg.respond(valid ? "True" : "False");
    }
}
