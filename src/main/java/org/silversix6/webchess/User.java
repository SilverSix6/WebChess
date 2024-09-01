package org.silversix6.webchess;

import jakarta.websocket.Session;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {

    public static final Map<String, User> users = new HashMap<String, User>();

    String username;
    int userId;
    public Session session;

    public User(String username, int userId, Session session) {
        this.username = username;
        this.userId = userId;
        this.session = session;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void message(Message message) {
        this.session.getAsyncRemote().sendText(message.toJson());
    }

    public static User getUser(Session session) {
        User user = users.get(session.getId());
        assert user != null;
        return users.get(session.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, userId);
    }
}
