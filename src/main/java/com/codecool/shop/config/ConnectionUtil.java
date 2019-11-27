package com.codecool.shop.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    public static Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("src/main/resources/connection.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(
                String.format("jdbc:postgresql://%s/%s", properties.getProperty("url"), properties.getProperty("db")),
                properties.getProperty("user"),
                properties.getProperty("pw"));
    }
}
