package com.mruruc.application;


import com.mruruc.db_management.dbconnection.Db;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class App3 {
    public static void main(String[] args) throws SQLException {

        Properties properties = System.getProperties();
        Set<String> strings = properties.stringPropertyNames();
        Iterator<String> iterator = strings.iterator();

        while (iterator.hasNext()){
            String next = iterator.next();
            String property = properties.getProperty(next);
            System.out.println(next + "---->" +property);
        }

    }
}
