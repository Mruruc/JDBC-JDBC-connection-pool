package com.mruruc.db_management;


import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DbUtil {
    private DbUtil(){}
    public static <T> Array convertListToSqlArray(Connection connection, List<T> list,String sqlArrayType) throws SQLException {
        T[] array= (T[]) list.toArray();
        return connection.createArrayOf(sqlArrayType,array);
    }
}
