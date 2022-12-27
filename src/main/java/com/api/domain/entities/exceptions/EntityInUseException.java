package com.api.domain.entities.exceptions;

public class EntityInUseException extends BusinessException{
    public EntityInUseException(String message) {
        super(message);
    }
}
