package com.mruruc;

import com.mruruc.db_management.ConnectionPool;
import com.mruruc.db_management.ImplConnectionPool;
import com.mruruc.model.User;
import com.mruruc.service.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class App {
    public static void main(String[] args) throws SQLException {

        ImplConnectionPool pool=new ImplConnectionPool(20);
        Connection connection = pool.getConnection();

        User user=new User("Ms.mSIKOO","MSIKSOBDJBKNCkcn");

        UserService service=new UserService(connection);

        List<User> all = service.getAll();
        all.stream().forEach(System.out::println);

        pool.releaseConnection(connection);
        System.out.println(pool.getMaxPoolSize());

        pool.shutDown();


   /*

        DbConnection.getConnection().prepareStatement("CREATE TABLE users(\n" +
                "    user_id BIGSERIAL PRIMARY KEY,\n" +
                "    user_name VARCHAR(30) NOT NULL,\n" +
                "    password VARCHAR(150) NOT NULL,\n" +
                "    UNIQUE (user_name)\n" +
                ");\n").execute(); */



    }
}
