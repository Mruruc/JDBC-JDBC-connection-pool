package com.mruruc.service;

import com.mruruc.db_management.DbConnection;
import com.mruruc.model.User;
import com.mruruc.queries.UserSQLQuery;
import com.mruruc.repository.CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserService implements CRUD<User,Long> {
    Connection connection;
    public UserService(Connection connection){
        this.connection=connection;
    }
    @Override
    public boolean save(User user) {
        try(PreparedStatement preparedStatement =
                    connection.prepareStatement(UserSQLQuery.insertIntoUsers)){
           preparedStatement.setString(1,user.getUserName());
           preparedStatement.setString(2,user.getPassword());
            int noOfEffectedRow = preparedStatement.executeUpdate();
            return noOfEffectedRow == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAll()  {
       List<User> list=new LinkedList<>();

        try(PreparedStatement preparedStatement =
                    connection.prepareStatement(UserSQLQuery.selectAllUser)){
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    list.addFirst(
                            new User(
                                    resultSet.getLong(1),
                                    resultSet.getString(2),
                                    resultSet.getString(3)
                            )
                    );
                }
                return list;
            }

       }catch (SQLException exception){
            throw new RuntimeException(exception);
        }
    }


}
