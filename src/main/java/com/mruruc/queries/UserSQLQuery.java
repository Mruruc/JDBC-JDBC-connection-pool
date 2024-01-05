package com.mruruc.queries;

public class UserSQLQuery {

    public static final String insertIntoUsers= """
                                                INSERT INTO users(user_name,password)
                                                VALUES(?,?);
                                                """;
    public static final String selectAllUser= """
                                               SELECT * FROM users;
                                               """;

    private UserSQLQuery(){}
}
