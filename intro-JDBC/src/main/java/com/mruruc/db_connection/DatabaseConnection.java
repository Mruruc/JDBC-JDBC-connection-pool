package com.mruruc.db_connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class DatabaseConnection{
    private static Connection connection;
    private static Properties properties;
    private DatabaseConnection(){}

    static {
         properties =new Properties();
        try(InputStream stream=new FileInputStream("src/main/resources/db.properties")){

            properties.load(stream);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
           throw new RuntimeException("Failed to load database properties!");
        }

    }

    public static Connection getConnection()  {
        try{
            Class.forName("org.postgresql.Driver");
            if(connection == null || connection.isClosed()){
                String url=properties.getProperty("url");
                String userName=properties.getProperty("userName");
                String password=properties.getProperty("password");
               connection= DriverManager.getConnection(url,userName,password);
                System.out.println("Connected : "+ LocalDateTime.now());
            }
            return connection;

        }
        catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error : "+ LocalDateTime.now());
            e.printStackTrace();
        }

        return connection;
    }


}
