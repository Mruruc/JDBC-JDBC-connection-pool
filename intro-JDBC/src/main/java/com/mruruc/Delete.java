package com.mruruc;

import com.mruruc.db_connection.DatabaseConnection;
import com.mruruc.service.StudentService;

import java.sql.Connection;
import java.util.Scanner;

public class Delete {
    public static void main(String[] args) {

        Scanner scan=new Scanner(System.in);

        Connection connect=DatabaseConnection.getConnection();
        StudentService service=new StudentService(connect);

        System.out.println("Enter Student id that you want to delete: ");
        Integer id=scan.nextInt();
        Integer delete = service.delete(id);

        if(delete == id){
            System.out.println("Delete operation is Successful !");
        }
        else {
            System.out.println("Try again!");
        }



    }
}
