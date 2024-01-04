package com.mruruc.service;

import com.mruruc.db_management.dbconnection.Db;
import com.mruruc.db_management.sqlQueries.AddressQueries.AddressSqlQuery;
import com.mruruc.exceptions.AddressNotFoundException;
import com.mruruc.exceptions.TransactionException;
import com.mruruc.model.client.Address;
import com.mruruc.repository.CRUDRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AddressService implements CRUDRepository<Address,Long> {

    private Db db;
    public AddressService(Db db){
        this.db=db;
    }


    @Override
    public boolean save(Address address) throws SQLException {
        if(address == null){
            throw new NullPointerException("Provided Address IS NULL!");
        }
        try(PreparedStatement preparedStatement =
                    db.connection().prepareStatement(AddressSqlQuery.insertIntoAddress)){
            preparedStatement.setString(1,address.getCountry());
            preparedStatement.setString(2,address.getCity());
            preparedStatement.setString(3,address.getStreet());
            preparedStatement.setString(4,address.getZip());
            preparedStatement.setInt(5,address.getDoorNo());
            int i = preparedStatement.executeUpdate();
            return i == 1;
        }
    }

    @Override
    public List<Address> getAll() throws SQLException {
        List<Address> listOfAddress=new ArrayList<>();

        try(Statement statement=db.connection().createStatement()){
            ResultSet resultSet = statement.executeQuery(AddressSqlQuery.getAllAddress);
            while (resultSet.next()){
                listOfAddress.add(
                        new Address(
                              resultSet.getLong(1),
                              resultSet.getString(2),
                              resultSet.getString(3),
                              resultSet.getString(4),
                              resultSet.getString(5),
                              resultSet.getInt(6)
                        )
                );
            }
            resultSet.close();
        }
        return listOfAddress;
    }

    @Override
    public Optional<Address> findById(Long id) throws SQLException{
        if(id == null){
            throw new NullPointerException("Specified ID is NULL!");
        }
        if(!isExists(id)){
            throw new AddressNotFoundException("Address Not Found!");
        }
        try(PreparedStatement preparedStatement =
                    db.connection().prepareStatement(AddressSqlQuery.findAddress)) {
            preparedStatement.setLong(1,id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    Address address=new Address(
                            resultSet.getLong(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getInt(6)
                    );
                    return Optional.of(address);
                }
            }

        }
        return Optional.empty();
    }

    @Override
    public Address update(Long id,Address address) throws SQLException{
//        if(id == null){
//            throw new NullPointerException("Specified ID is NULL!");
//        }

        Objects.requireNonNull(id, "Specified ID is NULL!");

        if(address == null){
            throw new NullPointerException("Given Address is NULL!");
        }
        if(!isExists(id)){
            throw new AddressNotFoundException("Address NOT FOUND!");
        }

        try(PreparedStatement preparedStatement =
                    db.connection().prepareStatement(AddressSqlQuery.updateAddress)){

            preparedStatement.setString(1,address.getCountry());
            preparedStatement.setString(2,address.getCity());
            preparedStatement.setString(3,address.getStreet());
            preparedStatement.setString(4,address.getZip());
            preparedStatement.setInt(5,address.getDoorNo());
            preparedStatement.setLong(6,id);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                 if(resultSet.next()) {
                     return new Address(
                             resultSet.getLong(1),
                             resultSet.getString(2),
                             resultSet.getString(3),
                             resultSet.getString(4),
                             resultSet.getString(5),
                             resultSet.getInt(6)
                     );
                 }
                 throw new TransactionException("Transaction Exception");
            }
        }
    }

    @Override
    public Long delete(Long addressID) throws SQLException {
        if (addressID == null) {
            throw new NullPointerException("Address ID IS NULL!");
        }


        if (!isExists(addressID)) {
            throw new AddressNotFoundException("Specified Address does NOT EXISTS!");
        }

        try (PreparedStatement preparedStatement =
                     db.connection().prepareStatement(AddressSqlQuery.deleteAddress)) {
            preparedStatement.setLong(1, addressID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getLong(1);
                }
                return 0L;
            }
        }
    }

    @Override
    public boolean isExists(Long id) throws SQLException{
        try (PreparedStatement pstmt = db.connection().prepareStatement(AddressSqlQuery.isAddressExists)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }
}
