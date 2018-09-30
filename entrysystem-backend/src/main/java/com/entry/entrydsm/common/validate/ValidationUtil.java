package com.entry.entrydsm.common.validate;

import com.entry.entrydsm.common.exception.RequestValidationException;
import com.entry.entrydsm.common.response.RestResponse;
import com.entry.entrydsm.user.domain.GraduateType;
import com.entry.entrydsm.user.domain.User;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ValidationUtil {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> void validate(T dto, Class<?>... groups) throws RequestValidationException {
        Set<ConstraintViolation<Object>> validationErrors = validator.validate(dto, groups);
        if (!validationErrors.isEmpty()) {
            throw new RequestValidationException(validationErrors);
        }
    }

    public static <T> void validate(T dto, User user) throws RequestValidationException {
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

    public static RestResponse.Error toError(ConstraintViolation err) {
        return new RestResponse.Error(err.getPropertyPath().toString(), err.getMessage());
    }

    public static List<RestResponse.Error> toErrors(Set<ConstraintViolation<Object>> errors) {
        return errors.stream().map(ValidationUtil::toError).collect(Collectors.toList());
    }
}
