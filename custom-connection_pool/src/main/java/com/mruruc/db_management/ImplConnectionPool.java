package com.mruruc.db_management;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ImplConnectionPool implements ConnectionPool {
    private static int INITIAL_POOL_SIZE = 10;
    private  int MAX_POOL_SIZE;
    private static List<Connection> connectionPool=new ArrayList<>(INITIAL_POOL_SIZE);
    private  List<Connection> usedConnections=new ArrayList<>();

    public ImplConnectionPool(){}
    public ImplConnectionPool(int MAX_POOL_SIZE){
        this.MAX_POOL_SIZE=MAX_POOL_SIZE;
    }

    public int getMaxPoolSize(){
        return MAX_POOL_SIZE;
    }


    static{
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            connectionPool.add(
                    DbConnection.createConnection()
            );
        }

        System.out.println(LocalDateTime.now() +
                " --> Connection Pool Initialized " + INITIAL_POOL_SIZE + " Connection Object");
    }
    @Override
    public Connection getConnection() {
        if(connectionPool.isEmpty() && usedConnections.size() < MAX_POOL_SIZE ){
            connectionPool.add(
                    DbConnection.createConnection()
            );
        }
        Connection removeFromPool = connectionPool.remove(connectionPool.size() - 1);
        usedConnections.add(removeFromPool);
        return removeFromPool;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    @Override
    public void shutDown() throws SQLException {
        usedConnections.forEach(this::releaseConnection);
        for(Connection con: connectionPool){
            con.close();
            System.out.println("Shutting Down Connection!" + con);

        }
    }
}
