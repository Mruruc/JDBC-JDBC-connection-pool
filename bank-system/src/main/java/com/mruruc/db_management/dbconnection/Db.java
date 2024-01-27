package com.mruruc.db_management.dbconnection;

import com.mruruc.db_management.dbExceptions.PropertyFileNotFoundException;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Db {


    private Connection con;
    private static Properties dbProperties;

    static {
        dbProperties=new Properties();
        try(InputStream inputStream=Db.class.getClassLoader().getResourceAsStream("db/db.properties")){
            if(inputStream == null) {
                throw new PropertyFileNotFoundException("Unable to find db.properties");
            }
            dbProperties.load(inputStream);
        }catch (IOException ioException){
            throw new RuntimeException("Failed to load database properties", ioException);
        }
    }


    public Db(){
       con=null;
    }

    public Connection connection()  {


        try {
            if (con == null || con.isClosed()) {
                String url = dbProperties.getProperty("url");
                String user=dbProperties.getProperty("user");
                String password=dbProperties.getProperty("password");
                con = DriverManager.getConnection(url,user,password);
                System.out.println("Successfully Connected!");
            }
        }
        catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return con;
    }

    public void closeConnection() throws SQLException{
        if(con != null){
            con.close();
        }
    }

}
