package com.mruruc.db_management.sqlQueries.clientQueries;

public class ClientSQLQuery {
    public static final String insertIntoClient="INSERT INTO Client(first_name, last_name, dob, gender, phones, email, address_id) " +
            "VALUES(?,?,?,?,?,?,?)";
    public static final String selectAllClient="SELECT client_id,first_name,last_name, dob, gender, phones, email" +
                                               " FROM Client";

    public static final String findClientById="SELECT client_id,first_name,last_name, dob, gender, phones, email" +
                                              " FROM Client WHERE client_id= ?";
    public static final String deleteClient="DELETE FROM Client" +
                                            " WHERE client_id= ? " +
                                            " RETURNING client_id ";
    public static final String isClientExists = "SELECT true FROM Client WHERE client_id = ?";
    public static final String updateClient = "UPDATE Client " +
            "SET first_name = ? , " +
            " last_name = ? , " +
            " dob = ? , " +
            " gender = ? , " +
            " phones = ? , " +
            " email = ? , " +
            " address_id = ? " +
            " WHERE client_id = ? " +
            " RETURNING * ";

    public static final String checkClientBasedOnEmail="SELECT 1 FROM Client WHERE email= ? ";



}
