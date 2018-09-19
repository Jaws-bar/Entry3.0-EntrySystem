package com.entry.entrydsm.user.service;

import com.entry.entrydsm.common.exception.BadRequestException;
import com.entry.entrydsm.common.exception.ConflictException;
import com.entry.entrydsm.info.domain.graduate.GraduateInfoRepository;
import com.entry.entrydsm.mail.EmailService;
import com.entry.entrydsm.user.domain.User;
import com.entry.entrydsm.user.domain.UserRepository;
import com.entry.entrydsm.user.domain.tempuser.TempUser;
import com.entry.entrydsm.user.domain.tempuser.TempUserRepository;
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

import javax.mail.SendFailedException;
import java.util.Optional;

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
    private GraduateInfoRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private TempUserRepository tempUserRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private EmailService emailService;

    private TempUser tempUser;

    @Before
    public void setUp() throws Exception {
        tempUser = new TempUser(DEFAULT_EMAIL, DEFAULT_PASSWORD);
        when(passwordEncoder.encode(anyString())).then(returnsFirstArg());
        when(tempUserRepository.save(any())).then(returnsFirstArg());
        when(passwordEncoder.matches(anyString(), anyString())).then(invocation -> invocation.getArgument(0).equals(invocation.getArgument(1)));
    }

    @Test
    public void 회원가입() throws SendFailedException {
        when(userRepository.existsByEmail(any())).thenReturn(false);
        TempUser tempUser = authService.signup(new SignupDTO(DEFAULT_EMAIL, DEFAULT_PASSWORD));
        assertThat(tempUser.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(passwordEncoder.matches(DEFAULT_PASSWORD, tempUser.getPassword())).isTrue();
    }

    @Test(expected = ConflictException.class)
    public void 회원가입_중복된_이메일() throws SendFailedException {
        when(userRepository.existsByEmail(any())).thenReturn(true);
        authService.signup(new SignupDTO(DEFAULT_EMAIL, DEFAULT_PASSWORD));
    }

    @Test
    public void 회원가입_인증() {
        when(tempUserRepository.findById(DEFAULT_CODE)).thenReturn(Optional.of(new TempUser(DEFAULT_EMAIL, DEFAULT_PASSWORD)));
        when(userRepository.save(any())).then(returnsFirstArg());

        User user = authService.confirm(DEFAULT_CODE);
        assertThat(user.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(user.getPassword()).isEqualTo(DEFAULT_PASSWORD);
    }

    @Test(expected = BadRequestException.class)
    public void 회원가입_인증_실패() {
        when(tempUserRepository.findById(DEFAULT_CODE)).thenReturn(Optional.empty());
        authService.confirm(DEFAULT_CODE);
    }

    @After
    public void tearDown() {
        tempUserRepository.deleteAll();
        userRepository.deleteAll();
    }
}