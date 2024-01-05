package com.mruruc.exceptions;


public class PropertyFileNotFoundException extends RuntimeException {
    public PropertyFileNotFoundException(String message){
        super(message);
    }
}
