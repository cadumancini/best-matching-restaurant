package com.assessment.exceptions;

public class UserInputException extends IllegalArgumentException {
    public UserInputException(String message) {
        super(message);
    }
}
