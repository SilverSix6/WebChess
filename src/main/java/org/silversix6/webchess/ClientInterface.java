package org.silversix6.webchess;

import com.google.gson.Gson;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import org.silversix6.webchess.Game.GameManager;

import java.io.IOException;
import java.util.Arrays;


@ServerEndpoint(value = "/interface")
public class ClientInterface {

    static final Gson gson = new Gson();
    private final Object lock = new Object();

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connection Opened. ID: " + session.getId());
    }

    @OnMessage
    public synchronized void onMessage(String txt, Session session) throws IOException {
        Message request = gson.fromJson(txt, Message.class);

        Message response = MessageHandler.process(request, session);
        assert response != null;

        session.getBasicRemote().sendText(response.toJson());

    }

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        System.out.println("Connection Closed. Reason: " + reason.getReasonPhrase());
        GameManager.removeGame(User.getUser(session));
        User.removeUser(session);
    }

    @OnError
    public void onError(Session session, Throwable t) {
        System.out.println("Connection Error: " + t.getMessage() + "\n" + Arrays.toString(t.getStackTrace()));

    }
}
