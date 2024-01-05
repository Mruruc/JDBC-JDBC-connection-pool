package com.mruruc.db_management;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionPool {
    Connection getConnection();
    boolean releaseConnection(Connection connection);
    void shutDown() throws SQLException;
}
