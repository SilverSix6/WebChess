package org.silversix6.webchess;

import jakarta.websocket.Session;
import org.silversix6.webchess.Database.Leaderboard;
import org.silversix6.webchess.Database.Login;

import java.util.ArrayList;

public class MessageHandler {

    public static Message process(Message msg, Session session) {
        switch (msg.messageType) {
            case MessageType.LOGIN:
                return processLogin(msg, session);
            case MessageType.LEADERBOARD:
                return processLeaderboard(msg);
            default:
                return null;
        }
    }

    private static Message processLogin(Message msg, Session session) {
        Message response = new Message(msg.messageId, new ArrayList<>(), MessageType.LOGIN);
        response.messages.add(Login.login(msg.messages.getFirst(), msg.messages.get(1), session) ? "True" : "False");
        return response;
    }

    private static Message processLeaderboard(Message msg) {
        return new Message(msg.messageId, Leaderboard.get(), MessageType.LEADERBOARD);
    }
}
