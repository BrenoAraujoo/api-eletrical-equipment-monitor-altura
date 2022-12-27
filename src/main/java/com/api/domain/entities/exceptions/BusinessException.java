package com.api.domain.entities.exceptions;

public class BusinessException extends  RuntimeException{


    private static final long serialVersionUID = -125686622746559064L;

    public BusinessException(String message){
        super(message);
    }
}
