package com.mruruc.service;

import com.mruruc.db_management.DbUtil;
import com.mruruc.db_management.dbconnection.Db;
import com.mruruc.db_management.sqlQueries.clientQueries.ClientSQLQuery;
import com.mruruc.exceptions.ClientAlreadyExistsException;
import com.mruruc.exceptions.ClientNotFoundException;
import com.mruruc.model.client.Client;
import com.mruruc.model.client.Gender;
import com.mruruc.repository.CRUDRepository;
import java.sql.*;
import java.sql.Date;
import java.util.*;


public class ClientService implements CRUDRepository<Client,Long> {
    private Db db;
    public ClientService(Db db) {
        this.db=db;
    }

    @Override
    public boolean save(Client client) throws SQLException {
        if(client == null){
            throw new NullPointerException("Client Instance Can Not Be NULL!");
        }
        if(isClientExistsBasedOnEmail(client.getEmail())){
            throw new ClientAlreadyExistsException("Client With : " +client.getEmail() + " Already Exists");
        }
        try(PreparedStatement pstmt =
                    db.connection().prepareStatement(ClientSQLQuery.insertIntoClient)){

            pstmt.setString(1,client.getFirstName());
            pstmt.setString(2,client.getLastName());
            pstmt.setDate(3, Date.valueOf(client.getDob()));
            pstmt.setString(4,client.getGender().toString());

            // convert List of phone to sql array
            Array array = DbUtil.convertListToSqlArray(db.connection(), client.getPhone(), "VARCHAR");
            pstmt.setArray(5,array);

            pstmt.setString(6, client.getEmail());
            pstmt.setLong(7,client.getAddressID());

            int i = pstmt.executeUpdate();
            return i == 1 ;
        }
    }

    @Override
    public List<Client> getAll() throws SQLException {
        List<Client> clients = new ArrayList<>();

        try (Statement statement = db.connection().createStatement();
             ResultSet rs = statement.executeQuery(ClientSQLQuery.selectAllClient)) {

            while (rs.next()) {
                clients.add(new Client(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4).toLocalDate(),
                        Gender.valueOf(rs.getString(5).toUpperCase()),
                        List.of((String[]) rs.getArray(6).getArray()),
                        rs.getString(7)
                ));
            }

        }
        return clients;
    }

    @Override
    public Optional<Client> findById(Long id) throws SQLException {
        if (id == null) {
            throw new NullPointerException("Specified ID is NULL!");
        }
        if(!isExists(id)){
            throw new ClientNotFoundException("Client Not Found Check Provided ID !");
        }
        try (PreparedStatement pstmt = db.connection().prepareStatement(ClientSQLQuery.findClientById)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Client client= new Client(
                            rs.getLong(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getDate(4).toLocalDate(),
                            Gender.valueOf(rs.getString(5).toUpperCase()),
                            List.of(rs.getString(6)),
                            rs.getString(7)
                    );
                    return Optional.of(client);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Client update(Long id, Client updatedClient) throws SQLException {
        if(updatedClient ==null){
            throw new NullPointerException("Client Instance IS NULL!");
        }
        if(isExists(id)){
            try (PreparedStatement pstmt = db.connection().prepareStatement(ClientSQLQuery.updateClient)) {

                pstmt.setString(1, updatedClient.getFirstName());
                pstmt.setString(2, updatedClient.getLastName());
                pstmt.setDate(3, Date.valueOf(updatedClient.getDob()));
                pstmt.setString(4, updatedClient.getGender().toString());
                pstmt.setArray(5, DbUtil.convertListToSqlArray(db.connection(),updatedClient.getPhone(),"VARCHAR"));
                pstmt.setString(6, updatedClient.getEmail());
                pstmt.setLong(7, updatedClient.getAddressID());
                pstmt.setLong(8, id);

                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    return new Client(
                            rs.getLong(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getDate(4).toLocalDate(),
                            Gender.valueOf(rs.getString(5).toUpperCase()),
                            List.of((String[]) rs.getArray(6).getArray()),
                            rs.getString(7),
                            rs.getLong(8)
                    );
                }
                rs.close();
            }
        }
        throw new ClientNotFoundException("Client Does NOT Exists!");
    }

    @Override
    public Long delete(Long id) throws SQLException {
        if (id == null) {
            throw new NullPointerException("Specified ID is NULL!");
        }
        if (!isExists(id)) {
            throw new ClientNotFoundException("CLIENT DOES NOT EXISTS!");
        }

        try (PreparedStatement preparedStatement = db.connection()
                .prepareStatement(ClientSQLQuery.deleteClient)) {
            preparedStatement.setLong(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
                return 0L;
            }
        }
    }

    @Override
    public boolean isExists(Long id) throws SQLException {
        try (PreparedStatement pstmt = db.connection().prepareStatement(ClientSQLQuery.isClientExists)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException exception){
            exception.printStackTrace();
            throw exception;
        }
    }

    public boolean isClientExistsBasedOnEmail(String emailAddress) throws SQLException{
        Objects.requireNonNull(emailAddress,"Email Address Can Not Be Null!");

        try(PreparedStatement preparedStatement
                    = db.connection().prepareStatement(ClientSQLQuery.checkClientBasedOnEmail)){
            preparedStatement.setString(1,emailAddress);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                return resultSet.next();
            }
        }

    }
}
