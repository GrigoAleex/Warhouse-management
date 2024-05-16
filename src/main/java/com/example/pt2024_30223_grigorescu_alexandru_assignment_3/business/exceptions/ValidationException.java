package com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.exceptions;

public class ValidationException extends Exception {
    public ValidationException(String errorMessage) {
        super(errorMessage);
    }
}
