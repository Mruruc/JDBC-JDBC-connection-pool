package com.mruruc.model.account;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class BankAccount {
    private UUID id;
    private BigDecimal balance;
    private LocalDateTime openDate;
    private LocalDateTime closeDate;
    private Long clientID;

    public BankAccount(BigDecimal balance, LocalDateTime openDate,
                       LocalDateTime closeDate, Long clientID) {
        this.balance = balance;
        this.openDate = openDate;
        this.closeDate = closeDate;
        this.clientID=clientID;
    }

    public BankAccount(UUID uuid, BigDecimal balance, Long clientID){
        this.balance=balance;
        this.clientID=clientID;
    }

    public BankAccount(UUID id, BigDecimal balance, LocalDateTime openDate, LocalDateTime closeDate, Long clientID) {
        this.id = id;
        this.balance = balance;
        this.openDate = openDate;
        this.closeDate = closeDate;
        this.clientID = clientID;
    }

    public Long getClientID() {
        return clientID;
    }

    public void setClientID(Long clientID) {
        this.clientID = clientID;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDateTime getOpenDate() {
        return openDate;
    }

    public void setOpenDate(LocalDateTime openDate) {
        this.openDate = openDate;
    }

    public LocalDateTime getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(LocalDateTime closeDate) {
        this.closeDate = closeDate;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", balance=" + balance +
                ", openDate=" + openDate +
                ", closeDate=" + closeDate +
                ", clientID=" + clientID +
                '}';
    }
}
