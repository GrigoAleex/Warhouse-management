package com.example.pt2024_30223_grigorescu_alexandru_assignment_3.data.database;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    public static Connection connect() throws IOException, SQLException {
        Properties props = new Properties();
        InputStream input = Files.newInputStream(Paths.get("database.properties"));
        props.load(input);

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.username");
        String password = props.getProperty("db.password");

        return DriverManager.getConnection(url, user, password);
    }

    public static void main(String[] args) {
        try ( Connection connection = connect()) {
            if (connection == null) System.err.println("Could not connect to the database");
            else System.out.println("Connected to the database");
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }

    }
}