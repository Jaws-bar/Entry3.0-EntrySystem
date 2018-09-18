package com.entry.entrydsm.common.validate;

import com.entry.entrydsm.common.exception.ValidationException;
import com.entry.entrydsm.user.domain.GraduateType;
import com.entry.entrydsm.user.domain.User;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class ValidationUtil {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private static <T> void validate(T dto, Class<?>... groups) throws ValidationException {
        Set<ConstraintViolation<Object>> validationErrors = validator.validate(dto, groups);
        if (!validationErrors.isEmpty()) {
            throw new ValidationException(validationErrors);
        }
    }

    public static <T> void validate(T dto, User user) throws ValidationException {
        if (user.getGraduateType() == GraduateType.GED) {
            validate(dto, Ged.class);
            return;
        }
        if (user.getGraduateType() == GraduateType.WILL) {
            validate(dto, Graduate.Will.class);
            return;
        }
        if (user.getGraduateType() == GraduateType.DONE) {
            validate(dto, Graduate.Done.class);
            return;
        }

        validate(dto);
    }


}
