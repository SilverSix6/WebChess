package org.silversix6.webchess.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
