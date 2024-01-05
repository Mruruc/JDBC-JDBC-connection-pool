package com.mruruc.db_management;

import com.mruruc.exceptions.PropertyFileNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Properties;

public class DbConnection {
    static Properties dbProperties;
    private DbConnection(){}

    static {
        dbProperties = new Properties();

        try (InputStream inputStream
                     = DbConnection.class
                           .getClassLoader()
                           .getResourceAsStream("db_connection/db_connection.properties")) {

            if (inputStream == null) {
                throw new PropertyFileNotFoundException("Property File Not Found!");
            }
            dbProperties.load(inputStream);
            System.out.println(LocalDateTime.now() +" --> property file read");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected static Connection createConnection() {
        String url = dbProperties.getProperty("url");
        String user = dbProperties.getProperty("user");
        String password = dbProperties.getProperty("password");
        try {
            System.out.println(LocalDateTime.now() +"--> New Connection Created.");
            return DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
