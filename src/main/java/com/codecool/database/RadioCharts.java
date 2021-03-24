package com.codecool.database;

import java.sql.*;

public class RadioCharts {
    private final String url;
    private final String user;
    private final String pw;

    public RadioCharts(String url, String user, String pw) {
        this.url = url;
        this.user = user;
        this.pw = pw;
    }


    public String getMostPlayedSong() {
        String query = "SELECT artist, song, SUM(times_aired) AS count FROM music_broadcast GROUP BY artist ORDER BY count DESC LIMIT 1";

        try (Connection connection = DriverManager.getConnection(url, user, pw)) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(2);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return "";
    }

    public String getMostActiveArtist() {
        String query = "SELECT artist, COUNT(DISTINCT song) AS song_number FROM music_broadcast GROUP BY artist ORDER BY song_number DESC LIMIT 1";

        try (Connection connection = DriverManager.getConnection(url, user, pw)) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return "";
    }
}
