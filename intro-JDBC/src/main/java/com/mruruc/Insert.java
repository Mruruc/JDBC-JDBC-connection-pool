package com.mruruc;

import com.mruruc.db_connection.DatabaseConnection;
import com.mruruc.model.Student;
import com.mruruc.service.StudentService;

import java.sql.*;
import java.util.Scanner;

public class Insert {

    public static void main(String[] args) throws SQLException {
        Student student =new Student();

        Scanner scan=new Scanner(System.in);

        Connection connect=DatabaseConnection.getConnection();

        StudentService service=new StudentService(connect);


        System.out.println("Please enter the Student Full Name:");
        String name=scan.nextLine();
        student.setName(name);

        System.out.println("Please enter the Student email:");
        String email= scan.nextLine();
        student.setEmail(email);

        boolean save = service.save(student);
        if(save){
            System.out.println("Student Successfully Saved!");
        }
        else {
            System.out.println("Try again!");
        }

    }
}
