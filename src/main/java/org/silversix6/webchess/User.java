package org.silversix6.webchess;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {

    public static final Map<String, User> users = new HashMap<String, User>();

    String username;
    int userId;

    public User(String username, int userId) {
        this.username = username;
        this.userId = userId;
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
