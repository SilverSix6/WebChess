package org.silversix6.webchess;

import com.google.gson.Gson;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;


@ServerEndpoint(value = "/interface")
public class ClientInterface {

    static final Gson gson = new Gson();

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connection Opened. ID: " + session.getId());
    }

    @OnMessage
    public void onMessage(String txt, Session session) throws IOException {
        Message request = gson.fromJson(txt, Message.class);

        Message response = MessageHandler.process(request, session);

        session.getBasicRemote().sendText(gson.toJson(response));
    }

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        System.out.println("Connection Closed. Reason: " + reason.getReasonPhrase());
    }

    @OnError
    public void onError(Session session, Throwable t) {
        System.out.println("Connection Error: " + t.getMessage() +"\n"+ Arrays.toString(t.getStackTrace()));

    }
}
