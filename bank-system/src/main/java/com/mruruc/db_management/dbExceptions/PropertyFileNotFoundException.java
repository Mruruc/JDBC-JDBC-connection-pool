package com.mruruc.db_management.dbExceptions;

public class PropertyFileNotFoundException extends RuntimeException{
    public PropertyFileNotFoundException(String message){
        super(message);
    }
}
