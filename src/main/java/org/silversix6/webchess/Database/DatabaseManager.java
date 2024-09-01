package org.silversix6.webchess.Database;

import jakarta.annotation.PreDestroy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
                newConnection();
        }
        return connection;
    }

    private static void newConnection() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/WebChess",
                    "webserver", "password"
            );
        } catch (ClassNotFoundException e) {
            System.out.println("Database driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }

    }

    @PreDestroy
    public void closeConnection() throws SQLException {
        connection.close();
    }
}
