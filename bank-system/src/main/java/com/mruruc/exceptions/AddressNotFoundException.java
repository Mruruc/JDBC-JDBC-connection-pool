package com.mruruc.exceptions;

/**
 *<h1> In Case of the Address Not Found the AddressNotFound Exception throws.</h1>
 */
public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(String message) {
        super(message);
    }
}
