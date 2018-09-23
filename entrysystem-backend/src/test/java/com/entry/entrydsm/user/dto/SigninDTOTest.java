package com.entry.entrydsm.user.dto;

import com.entry.entrydsm.support.ValidationTest;
import org.junit.Test;

public class SigninDTOTest extends ValidationTest {
    private static final String SOME_EMAIL = "some email";
    private static final String SOME_PASSWORD = "some password";

    @Test
    public void valid() {
        assertConstraintViolations(new SigninDTO(SOME_EMAIL, SOME_PASSWORD), 0);
    }

    @Test
    public void email_NULL() {
        assertConstraintViolations(new SigninDTO(null, SOME_PASSWORD), 1);
    }

    @Test
    public void passowrd_NULL() {
        assertConstraintViolations(new SigninDTO(SOME_EMAIL, null), 1);
    }
}