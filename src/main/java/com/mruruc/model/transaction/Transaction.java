package com.mruruc.model.transaction;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class Transaction {
    private Long id;
    private TransactionType type;
    private Double amount;
    private LocalDate date;
    private LocalTime time;
    private UUID accountId;
    private Long clientId;

    public Transaction(Long id, TransactionType type, Double amount, LocalDate date, LocalTime time, UUID accountId, Long clientId) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.accountId = accountId;
        this.clientId = clientId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", type=" + type +
                ", amount=" + amount +
                ", date=" + date +
                ", time=" + time +
                ", accountId=" + accountId +
                ", clientId=" + clientId +
                '}';
    }
}
