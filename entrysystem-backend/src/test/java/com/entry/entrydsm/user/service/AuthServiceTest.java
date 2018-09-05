package com.entry.entrydsm.user.service;

import com.entry.entrydsm.common.exception.ConflictException;
import com.entry.entrydsm.user.domain.TempUser;
import com.entry.entrydsm.user.domain.TempUserRepository;
import com.entry.entrydsm.user.domain.UserRepository;
import com.entry.entrydsm.user.dto.SignupDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AuthServiceTest {

    private static final String DEFAULT_EMAIL = "test@test.com";
    private static final String DEFAULT_PASSWORD = "password1234";
    private static final String DEFAULT_CODE = "code1234!@#$";

    @InjectMocks
    private AuthService authService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private TempUserRepository tempUserRepository;
    @Mock
    private UserRepository userRepository;
    private TempUser tempUser;

    @Before
    public void setUp() throws Exception {
        tempUser = new TempUser(DEFAULT_EMAIL, DEFAULT_PASSWORD);
        when(passwordEncoder.encode(anyString())).then(returnsFirstArg());
        when(tempUserRepository.save(any())).then(returnsFirstArg());
        when(passwordEncoder.matches(anyString(), anyString())).then(invocation -> invocation.getArgument(0).equals(invocation.getArgument(1)));
    }

    @Test
    public void 회원가입() {
        when(userRepository.existsByEmail(any())).thenReturn(false);
        TempUser tempUser = authService.signup(new SignupDTO(DEFAULT_EMAIL, DEFAULT_PASSWORD));
        assertThat(tempUser.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(passwordEncoder.matches(DEFAULT_PASSWORD, tempUser.getPassword())).isTrue();
    }

    @Test(expected = ConflictException.class)
    public void 회원가입_중복된_이메일() {
        when(userRepository.existsByEmail(any())).thenReturn(true);
        authService.signup(new SignupDTO(DEFAULT_EMAIL, DEFAULT_PASSWORD));
    }

    @After
    public void tearDown() {
        tempUserRepository.deleteAll();
        userRepository.deleteAll();
    }
}