package com.entry.entrydsm.user.controller;

import com.entry.entrydsm.common.response.RestResponse;
import com.entry.entrydsm.support.AcceptanceTest;
import com.entry.entrydsm.user.domain.GraduateType;
import com.entry.entrydsm.user.domain.TempUser;
import com.entry.entrydsm.user.domain.TempUserRepository;
import com.entry.entrydsm.user.domain.User;
import com.entry.entrydsm.user.dto.SignupDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;


public class AuthControllerTest extends AcceptanceTest {
    private static final String SAFE_EMAIL = "bright_chan@dsm.hs.kr";
    private static final String UNSAFE_EMAIL = "unsafe";
    private static final String SAFE_PASSWORD = "password1234";
    private static final String UNSAFE_PASSWORD = "unsafe";

    @Autowired
    private TempUserRepository tempUserRepository;

    private TempUser tempUser;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        tempUser = tempUserRepository.save(new TempUser(SAFE_EMAIL, SAFE_PASSWORD));
    }

    @Test
    public void 회원가입() {
        ResponseEntity<RestResponse<TempUser>> response = postRequest("/api/signup", new SignupDTO(SAFE_EMAIL, SAFE_PASSWORD), tempUserTypeRef());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getData().getEmail()).isEqualTo(SAFE_EMAIL);
    }

    @Test
    public void 회원가입_이메일_NULL() {
        ResponseEntity<RestResponse<TempUser>> response = postRequest("/api/signup", new SignupDTO(null, SAFE_PASSWORD), tempUserTypeRef());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getErrors()).hasSize(1);
    }

    @Test
    public void 회원가입_비밀번호_NULL() {
        ResponseEntity<RestResponse<TempUser>> response = postRequest("/api/signup", new SignupDTO(SAFE_EMAIL, null), tempUserTypeRef());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getErrors()).hasSize(1);
    }

    @Test
    public void 회원가입_이메일_형식_안맞음() {
        ResponseEntity<RestResponse<TempUser>> response = postRequest("/api/signup", new SignupDTO(UNSAFE_EMAIL, SAFE_PASSWORD), tempUserTypeRef());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getErrors()).hasSize(1);
    }

    @Test
    public void 회원가입_비밀번호_형식_안맞음() {
        ResponseEntity<RestResponse<TempUser>> response = postRequest("/api/signup", new SignupDTO(SAFE_EMAIL, UNSAFE_PASSWORD), tempUserTypeRef());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getErrors()).hasSize(1);
    }

    @Test
    public void 회원가입_중복된_이메일() {
        ResponseEntity<RestResponse<TempUser>> response = postRequest("/api/signup", new SignupDTO(DEFAULT_USER_EMAIL, UNSAFE_PASSWORD), tempUserTypeRef());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getErrors()).hasSize(1);
    }

    @Test
    public void 회원가입_인증() {
        ResponseEntity<RestResponse<User>> response = getRequest(String.format("/api/signup/confirm/%s", tempUser.getCode()), userTypeRef());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getData().getGraduateType()).isEqualTo(GraduateType.WILL);
        assertThat(response.getBody().getData().getEmail()).isEqualTo(tempUser.getEmail());
    }

    @Test
    public void 회원가입_인증_실패() {
        ResponseEntity<RestResponse<User>> response = getRequest(String.format("/api/signup/confirm/%s", tempUser.getCode() + "A"), userTypeRef());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getErrors()).hasSize(1);
    }

    private ParameterizedTypeReference<RestResponse<User>> userTypeRef() {
        return new ParameterizedTypeReference<RestResponse<User>>() {
        };
    }

    private ParameterizedTypeReference<RestResponse<TempUser>> tempUserTypeRef() {
        return new ParameterizedTypeReference<RestResponse<TempUser>>() {
        };
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        tempUserRepository.deleteAll();
    }
}