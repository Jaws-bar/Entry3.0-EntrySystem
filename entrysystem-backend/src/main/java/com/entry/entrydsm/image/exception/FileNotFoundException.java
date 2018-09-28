package com.entry.entrydsm.image.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileNotFoundException extends Exception {

    public FileNotFoundException() {
    }

    public FileNotFoundException(Throwable cause) {
        super(cause);
    }
}
