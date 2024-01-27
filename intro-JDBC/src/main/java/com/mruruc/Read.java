package com.mruruc;


import com.mruruc.db_connection.DatabaseConnection;
import com.mruruc.model.Student;
import com.mruruc.service.StudentService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class Read {
    public static void main(String[] args) {

        Connection connect=DatabaseConnection.getConnection();
        StudentService service =new StudentService(connect);
        List<Student> all = service.getAll();

        all.forEach(System.out::println);

    }
}