package com.entry.entrydsm.support;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public abstract class ValidationTest {

    private Validator validator;

    @Before
    public void setUp() throws Exception {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    protected <T> void assertConstraintViolations(T dto, int size) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(dto);
        constraintViolations.forEach(violation -> log.debug("violation : {}", violation.getMessage()));
        assertThat(constraintViolations.size()).isEqualTo(size);
    }
}
