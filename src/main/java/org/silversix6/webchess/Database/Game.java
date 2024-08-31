package org.silversix6.webchess.Database;

import org.silversix6.webchess.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Game {

    public static void insert(String winnerId, String loserId) {
        Connection con = DatabaseManager.getConnection();

        try {
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO game (winnerUserId, loserUserId) VALUES(?,?)");

            preparedStatement.setString(1, winnerId);
            preparedStatement.setString(2, loserId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Failed to insert new game results: " + e.getMessage() );
        }
    }
}
