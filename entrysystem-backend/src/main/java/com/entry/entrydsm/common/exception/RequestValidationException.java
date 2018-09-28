package com.entry.entrydsm.common.exception;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class RequestValidationException extends Exception {
    private final Set<ConstraintViolation<Object>> validationErrors;

    public RequestValidationException(Set<ConstraintViolation<Object>> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public Set<ConstraintViolation<Object>> getValidationErrors() {
        return validationErrors;
    }
}
