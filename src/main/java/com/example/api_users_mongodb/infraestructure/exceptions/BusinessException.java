package com.example.api_users_mongodb.infraestructure.exceptions;

public class BusinessException extends RuntimeException {
    
    public BusinessException(String message){
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }
    
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
