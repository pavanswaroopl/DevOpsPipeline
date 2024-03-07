package com.example.devopsPipeline.exception;

public class UserAlreadyExistException extends Exception{
    
    public UserAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }
}
