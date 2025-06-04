package com.studyverse.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EquationSolverNotFoundException extends RuntimeException {
    public EquationSolverNotFoundException(String message) {
        super(message);
    }
}
