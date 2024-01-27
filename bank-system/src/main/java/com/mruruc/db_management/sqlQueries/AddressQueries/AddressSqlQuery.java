package com.mruruc.db_management.sqlQueries.AddressQueries;

public class AddressSqlQuery {
    public static final String insertIntoAddress="INSERT INTO Address(country,city,street,zip_code,door_no)" +
                                                  "VALUES(?,?,?,?,?)";
    public static final String getAllAddress="SELECT * FROM Address";

    public static final String findAddress="SELECT * FROM Address WHERE address_id= ? ";
    public static final String updateAddress="UPDATE Address " +
            "SET country= ? ,city= ? ,street= ? ,zip_code= ?,door_no= ? " +
            "WHERE address_id = ? " +
            "RETURNING * ";
    public static final String deleteAddress="DELETE FROM Address WHERE address_id = ? RETURNING address_id";
    public static final String isAddressExists="SELECT true FROM Address WHERE address_id = ? ";

    private AddressSqlQuery(){}
}
