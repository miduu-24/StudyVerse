package com.studyverse.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class MoleculeNotFoundException extends RuntimeException {
    public MoleculeNotFoundException(String message) {
        super(message);
    }
}
