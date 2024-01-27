package com.mruruc.repository;

import com.mruruc.exceptions.TableAlreadyExists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbUtil {

    private DbUtil(){}

    public static void createRelation(Connection connection,String sql) throws SQLException {

        try(PreparedStatement preparedStatement =
                    connection.prepareStatement(sql)){
            preparedStatement.executeUpdate();
        }catch (SQLException exception){
            if(exception.getMessage().contains("ERROR: relation  already exists") ){
                throw new TableAlreadyExists("Relation Already Exists!");
              }
            throw exception;
        }
        finally {
            connection.close();
        }
    }
}
