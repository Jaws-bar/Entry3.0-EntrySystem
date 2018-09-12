package com.entry.entrydsm.common.validate;

import com.entry.entrydsm.common.exception.ValidationException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class ValidationUtil {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> void validate(T dto, Class<?>... groups) {
        Set<ConstraintViolation<Object>> validationErrors = validator.validate(dto, groups);
        if (!validationErrors.isEmpty()) {
            throw new ValidationException(validationErrors);
        }

    }
}
