package com.entry.entrydsm.common.exception;

import com.entry.entrydsm.apply.dto.ValidationResult;

public class SubmitValidationException extends RuntimeException {

    private ValidationResult validationResult;

    public SubmitValidationException(ValidationResult validationResult) {
        this.validationResult = validationResult;
    }

    public ValidationResult getValidationResult() {
        return validationResult;
    }
}
