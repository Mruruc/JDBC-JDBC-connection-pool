package com.mruruc.exceptions;

public class TableAlreadyExists extends RuntimeException{
    public TableAlreadyExists(String message){
        super(message);
    }
}
