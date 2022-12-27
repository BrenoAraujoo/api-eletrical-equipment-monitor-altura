package com.api.domain.entities.exceptions;

public class EntityNotFoundException extends BusinessException{


    private static final long serialVersionUID = -4393127307677830666L;

    public EntityNotFoundException(String message) {
        super(message);
    }
}
