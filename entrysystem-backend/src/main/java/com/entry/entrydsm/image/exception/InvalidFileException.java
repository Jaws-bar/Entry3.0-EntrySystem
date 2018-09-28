package com.entry.entrydsm.image.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidFileException extends Exception {

    public InvalidFileException(String message) {
        super(message);
    }
}
