package com.mruruc.service;

import com.mruruc.model.Student;
import com.mruruc.repository.CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class StudentService implements CRUD<Student,Integer> {
    private Connection connection;
    public StudentService(Connection connection){
        this.connection=connection;
    }
    @Override
    public boolean save(Student student) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO student(full_name,email)" +
                                                                               " VALUES (?,?) ")){

            preparedStatement.setString(1,student.getName());
            preparedStatement.setString(2,student.getEmail());

            int i = preparedStatement.executeUpdate();
            return i == 1;
        } catch (SQLException e) {
           e.printStackTrace();
        }
        finally {
            closeConnection();
        }
        return false;
    }

    @Override
    public List<Student> getAll() {
        List<Student> studentList=new LinkedList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM  student")){

            try(ResultSet resultSet = preparedStatement.executeQuery()){
               while (resultSet.next()){
                   studentList.addFirst(
                           new Student(
                                   resultSet.getInt(1),
                                   resultSet.getString(2),
                                   resultSet.getString(3)
                           )
                   );
               }
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
               closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return studentList;
    }

    @Override
    public Optional<Student> get(Integer integer) {
        return Optional.empty();
    }

    @Override
    public Integer delete(Integer id) {
        try(PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM student WHERE Id= ? RETURNING Id")){
            preparedStatement.setInt(1,id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return  resultSet.getInt(1);
                }
                /// handle in case of the deletion is not successful
            }

        } catch (SQLException e) {
           e.printStackTrace();
        }
        finally {
            try {
                closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return -1;
    }

    @Override
    public Student update(Student student) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(" UPDATE Student " +
                                        " SET email= ? " +
                                        " WHERE id= ?  " +
                                        " RETURNING * ")){

            preparedStatement.setString(1,student.getEmail());
            preparedStatement.setInt(2,student.getId());

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return new Student(
                      resultSet.getInt(1),
                      resultSet.getString(2),
                      resultSet.getString(3)
                    );
                }
                ///exception handling
            }

        }
        catch (SQLException exception){
            exception.printStackTrace();
        }
        finally {
            try {
                closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // handle later
        return null;
    }

    private void closeConnection() throws SQLException {
        connection.close();
        System.out.println("Connection Closed!");
    }
}
