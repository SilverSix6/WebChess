package org.silversix6.webchess.Database;

import jakarta.websocket.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDatabase {

    public static void add(String username, String password) {
        Connection con = DatabaseManager.getConnection();

        try {
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO User (username, password) VALUES(?,?)");

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Failed to insert new game results: " + e.getMessage() );
        }
    }

    public static int login(String username, String password, Session session) {

        Connection con = DatabaseManager.getConnection();

        try {
            PreparedStatement preparedStatement = con.prepareStatement("SELECT userId FROM User WHERE username = ? AND password = ?");

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            org.silversix6.webchess.User.users.put(session.getId(), new org.silversix6.webchess.User(username, resultSet.getInt(1), session));

            return resultSet.getInt(1);

        } catch (SQLException e) {
            System.out.println("Database query failed " + e.getMessage() );
            return 0;
        }
    }
}
