package org.silversix6.webchess.Database;

import jakarta.websocket.Session;
import org.silversix6.webchess.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

    public static boolean login(String username, String password, Session session) {

        Connection con = DatabaseManager.getConnection();

        try {
            PreparedStatement preparedStatement = con.prepareStatement("SELECT userId FROM User WHERE username = ? AND password = ?");

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            User.users.put(session.getId(), new User(username, resultSet.getInt(1)));

            return resultSet.getInt(1) != 0;

        } catch (SQLException e) {
            System.out.println("Database query failed " + e.getMessage() );
            return false;
        }
    }


}
