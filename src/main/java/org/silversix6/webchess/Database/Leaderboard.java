package org.silversix6.webchess.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Leaderboard {

    public static List<String> get() {
        List<String> leaderboard = new ArrayList<String>();

        Connection con = DatabaseManager.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT win.username, win.wins, loss.losses FROM (SELECT U.username, COUNT(G.winnerUserId) AS wins FROM game AS G JOIN User U on U.userID = G.winnerUserId GROUP BY U.username) as win JOIN\n" +
                    "(SELECT U.username, COUNT(G.loserUserId) AS losses FROM game AS G JOIN User U on U.userID = G.loserUserId GROUP BY U.username) as loss ON win.username = loss.username LIMIT 10;");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                leaderboard.add(rs.getString("username"));
                leaderboard.add(String.format("%.2f", (double) rs.getInt("wins") / rs.getInt("losses")));
            }

        } catch (SQLException e) {
            System.out.println("SQL error while getting leaderboard" + e.getMessage());
        }
        return leaderboard;
    }

}
