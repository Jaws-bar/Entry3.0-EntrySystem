package com.entry.entrydsm.user.dto;

import com.entry.entrydsm.support.ValidationTest;
import org.junit.Test;

public class SignupDTOTest extends ValidationTest {
    private static final String SAFE_EMAIL = "bright_chan@dsm.hs.kr";
    private static final String UNSAFE_EMAIL = "unsafe";
    private static final String SAFE_PASSWORD = "password1234";
    private static final String UNSAFE_PASSWORD = "unsafe";

    @Test
    public void 이메일_NULL() {
        assertConstraintViolations(new SignupDTO(null, SAFE_PASSWORD), 1);
    }

    @Test
    public void 비밀번호_NULL() {
        assertConstraintViolations(new SignupDTO(SAFE_EMAIL, null), 1);
    }

    @Test
    public void 이메일_형식_안맞음() {
        assertConstraintViolations(new SignupDTO(UNSAFE_EMAIL, SAFE_PASSWORD), 1);
    }

    @Test
    public void 비밀번호_형식_안맞음() {
        assertConstraintViolations(new SignupDTO(SAFE_EMAIL, UNSAFE_PASSWORD), 1);
    }


}