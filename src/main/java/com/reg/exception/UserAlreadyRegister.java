package com.reg.exception;

public class UserAlreadyRegister extends RuntimeException {
    private String message;

    public UserAlreadyRegister(String message) {
        super(message); 
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
