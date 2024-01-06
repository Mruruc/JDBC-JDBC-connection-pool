package com.mruruc.db_management;


import com.mruruc.db_management.dbconnection.DataSource;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static com.mruruc.db_management.dbconnection.DataSource.getConnection;

public class DbUtil {
    private DbUtil(){}
    public static <T> Array convertListToSqlArray( List<T> list,String sqlArrayType) throws SQLException {
        T[] array= (T[]) list.toArray();
        try(Connection connection = getConnection()){
            return connection.createArrayOf(sqlArrayType,array);
        }
    }
}
