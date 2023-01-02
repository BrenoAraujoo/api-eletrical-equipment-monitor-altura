package com.api.exceptionhandler;


import lombok.Getter;

@Getter
public enum ProblemType {

    ERROR_RESOURCE_NOT_FOUND("Resource not found"),
    ERROR_RESOURCE_IN_USE("Resource in use"),
    ERROR_ILLEGIBLE_MESSAGE("Illegible Message"),
    ERROR_INVALID_DATA("Invalid data");

    private String title;

    ProblemType(String title) {
        this.title = title;
    }
}
