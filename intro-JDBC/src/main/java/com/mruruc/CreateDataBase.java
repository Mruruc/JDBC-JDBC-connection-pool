package com.mruruc;

import com.mruruc.db_connection.DatabaseConnection;
import com.mruruc.exceptions.TableAlreadyExists;
import com.mruruc.repository.DbUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CreateDataBase {
    public static void main(String[] args)  {

        Connection connection = DatabaseConnection.getConnection();
        String sql= """
                    CREATE TABLE  student(
                       Id serial PRIMARY KEY ,
                       full_name VARCHAR(50),
                       email VARCHAR(60)
                    );
                    """;
        try{
            DbUtil.createRelation(connection,sql);

        } catch (SQLException | TableAlreadyExists e) {
            System.out.println(e.getMessage());
        }
        finally {
           try{
               connection.close();
               System.out.println("Connection Closed: " + LocalDateTime.now());
           } catch (SQLException e) {
              e.printStackTrace();
           }
        }
    }
}
