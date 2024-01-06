package com.mruruc.db_management.dbconnection;

import com.mruruc.db_management.dbExceptions.PropertyFileNotFoundException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource dataSource;
    private static Properties dbProperties;


    static {
        dbProperties = new Properties();
        try (InputStream inputStream
                     = DataSource.class.getClassLoader().getResourceAsStream("db/db.properties")) {
            if (inputStream == null) {
                throw new PropertyFileNotFoundException("Unable to find db.properties");
            }
            dbProperties.load(inputStream);

            String url = dbProperties.getProperty("url");
            String user = dbProperties.getProperty("user");
            String password = dbProperties.getProperty("password");
            String poolName = dbProperties.getProperty("poolName");
            int maxPoolSize = Integer.parseInt(dbProperties.getProperty("maxPoolSize"));

            config.setPoolName(poolName);
            config.setJdbcUrl(url);
            config.setUsername(user);
            config.setPassword(password);
            config.setMaximumPoolSize(maxPoolSize);

            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

            dataSource = new HikariDataSource(config);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private DataSource() {
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void shutDownPool() {
        dataSource.close();
    }

}
