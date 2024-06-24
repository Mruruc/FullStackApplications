package com.bidAndWin.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String usrNotFound) {
        super(usrNotFound);
    }
}
