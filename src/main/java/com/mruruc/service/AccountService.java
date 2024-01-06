package com.mruruc.service;

import com.mruruc.db_management.sqlQueries.AccountQueries.AccountSqlQuery;
import com.mruruc.exceptions.TransactionException;
import com.mruruc.model.account.BankAccount;
import com.mruruc.repository.CRUDRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.mruruc.db_management.dbconnection.DataSource.getConnection;

public class AccountService implements CRUDRepository<BankAccount, UUID> {

    public AccountService(){

    }
    @Override
    public boolean save(BankAccount bankAccount) throws SQLException {
        if(bankAccount == null){
            throw new NullPointerException("BankAccount Instance Is NULL!");
        }
        try(PreparedStatement preparedStatement
                    = getConnection().prepareStatement(AccountSqlQuery.createNewBankAccount)){

            preparedStatement.setBigDecimal(1,bankAccount.getBalance());
            preparedStatement.setTimestamp(2,Timestamp.valueOf(bankAccount.getOpenDate()));
          //  preparedStatement.setTimestamp(4, Timestamp.valueOf(bankAccount.getCloseDate()));
            preparedStatement.setNull(3,Types.TIMESTAMP);
            preparedStatement.setLong(4,bankAccount.getClientID());

            return preparedStatement.executeUpdate() == 1;
        }
    }

    @Override
    public List<BankAccount> getAll() throws SQLException{
        List<BankAccount> bankAccounts=new ArrayList<>();
        try(PreparedStatement preparedStatement =
                    getConnection().prepareStatement(AccountSqlQuery.getCreatedAccountList)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bankAccounts.add(
                        new BankAccount(
                                resultSet.getObject(1, UUID.class),
                                resultSet.getBigDecimal(2),
                                resultSet.getTimestamp(3).toLocalDateTime(),
                                resultSet.getTimestamp(4) != null ? resultSet.getTimestamp(4).toLocalDateTime() : null,
                                resultSet.getLong(5)
                        )
                );
            }
            resultSet.close();
        }
        return bankAccounts;
    }

    @Override
    public Optional<BankAccount> findById(UUID uuid) throws SQLException {
        if(uuid == null){
            throw new NullPointerException("Specified ID is Null!");
        }

        try(PreparedStatement preparedStatement =
                getConnection().prepareStatement(AccountSqlQuery.getAccount)){

            preparedStatement.setObject(1,uuid);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return Optional.of(
                            new BankAccount(
                                    resultSet.getObject(1, UUID.class),
                                    resultSet.getBigDecimal(2),
                                    resultSet.getTimestamp(3).toLocalDateTime(),
                                    resultSet.getTimestamp(4) != null ? resultSet.getTimestamp(4).toLocalDateTime() : null,
                                    resultSet.getLong(5)
                            )
                    );
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public BankAccount update(UUID uuid, BankAccount bankAccount) throws SQLException {
        if(uuid == null){
            throw new NullPointerException("Specified ID Is NULL!");
        }
        if(bankAccount == null){
            throw new NullPointerException("Account Does NOT Exist!");
        }

        try(PreparedStatement preparedStatement =
                getConnection().prepareStatement(AccountSqlQuery.updateAccount)){

            preparedStatement.setBigDecimal(1,bankAccount.getBalance());

            if (bankAccount.getCloseDate() == null) {
                preparedStatement.setNull(2, Types.TIMESTAMP);
            } else {
                preparedStatement.setTimestamp(2, Timestamp.valueOf(bankAccount.getCloseDate()));
            }


            preparedStatement.setObject(3,bankAccount.getId());
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    bankAccount.setBalance(resultSet.getBigDecimal(2));
                    bankAccount.setCloseDate(
                            resultSet.getTimestamp(4) != null ? resultSet.getTimestamp(4).toLocalDateTime() : null
                    );
                    return bankAccount;
                }
                throw new TransactionException("Transaction Exception!");
            }
        }
    }


    @Override
    public UUID delete(UUID id) throws SQLException{
        if(id == null){
            throw new NullPointerException("Specified ID Is NULL!");
        }

        try(PreparedStatement preparedStatement=
                getConnection().prepareStatement(AccountSqlQuery.deleteBankAccount)){
            preparedStatement.setObject(1,id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if( resultSet.next()) {
                    return resultSet.getObject(1, UUID.class);
                }
                return null;
            }
        }
    }

    @Override
    public boolean isExists(UUID uuid) throws SQLException {
        try (PreparedStatement preparedStatement =getConnection()
                .prepareStatement(AccountSqlQuery.isExists)) {

            preparedStatement.setObject(1, uuid);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        }
    }


}
