package com.example.quanlysieuthi.exceptions;

public class ResourceNotAcceptException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public ResourceNotAcceptException(String message){
        super(message);
    }
}
