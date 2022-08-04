package com.api.exceptions;

public class ResourceNotFoundException extends  RuntimeException{

    private static final long serialVersionUID = -158139024097310949L;

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
