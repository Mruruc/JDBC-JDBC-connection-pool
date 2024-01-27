package com.mruruc;

import com.mruruc.db_connection.DatabaseConnection;
import com.mruruc.model.Student;
import com.mruruc.service.StudentService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Update {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Scanner scan=new Scanner(System.in);

        Connection conect=DatabaseConnection.getConnection();

        StudentService service=new StudentService(conect);

        System.out.println("Enter the id of Student that you want to update: ");
        int id= scan.nextInt();
        scan.nextLine();

        System.out.println("Enter the Full Name:");
        String fullName= scan.nextLine();

        System.out.println("Enter the new email:");
        String email= scan.nextLine();


        Student update = service.update(new Student(id, fullName, email));
        System.out.println(update);


    }
}
