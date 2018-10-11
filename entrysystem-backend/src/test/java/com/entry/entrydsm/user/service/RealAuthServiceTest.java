package com.entry.entrydsm.user.service;

import com.entry.entrydsm.common.exception.BadRequestException;
import com.entry.entrydsm.common.exception.ConflictException;
import com.entry.entrydsm.common.exception.UnauthorizedException;
import com.entry.entrydsm.common.response.JwtToken;
import com.entry.entrydsm.common.security.jwt.Jwt;
import com.entry.entrydsm.mail.EmailService;
import com.entry.entrydsm.user.domain.User;
import com.entry.entrydsm.user.domain.UserRepository;
import com.entry.entrydsm.user.domain.tempuser.TempUser;
import com.entry.entrydsm.user.domain.tempuser.TempUserRepository;
import com.entry.entrydsm.user.dto.SigninDTO;
import com.entry.entrydsm.user.dto.SignupDTO;
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
public class RealAuthServiceTest {

    private static final String DEFAULT_EMAIL = "test@test.com";
    private static final String DEFAULT_PASSWORD = "password1234";
    private static final String DEFAULT_CODE = "code1234!@#$";

    @InjectMocks
    private RealAuthService authService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private TempUserRepository tempUserRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EmailService emailService;

    @Mock
    private Jwt jwt;

    private SigninDTO signinDTO;

    @Before
    public void setUp() throws Exception {
        signinDTO = new SigninDTO(DEFAULT_EMAIL, DEFAULT_PASSWORD);
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
    public void 회원가입_인증() throws Exception {
        when(tempUserRepository.findById(DEFAULT_CODE)).thenReturn(Optional.of(new TempUser(DEFAULT_EMAIL, DEFAULT_PASSWORD)));
        when(userRepository.save(any())).then(returnsFirstArg());
        when(jwt.createToken(any())).thenAnswer(invocation -> new JwtToken(((User) invocation.getArgument(0)).getEmail(), null));
        JwtToken token = authService.confirm(DEFAULT_CODE);
        assertThat(token.getAccessToken()).isEqualTo(DEFAULT_EMAIL);
    }

    @Test(expected = BadRequestException.class)
    public void 회원가입_인증_실패() throws Exception {
        when(tempUserRepository.findById(DEFAULT_CODE)).thenReturn(Optional.empty());
        authService.confirm(DEFAULT_CODE);
    }

    @Test
    public void 로그인() throws Exception {
        User user = new User();
        when(userRepository.findByEmail(signinDTO.getEmail())).thenReturn(Optional.of(user));
        when(jwt.createToken(any())).thenAnswer(invocation -> new JwtToken(invocation.getArgument(0).toString(), null));
        when(passwordEncoder.matches(signinDTO.getPassword(), null)).thenReturn(true);
        JwtToken token = authService.signin(signinDTO);
        assertThat(token.getAccessToken()).isEqualTo(user.toString());
    }

    @Test(expected = UnauthorizedException.class)
    public void 로그인_실패_이메일_존재하지_않음() throws Exception {
        when(userRepository.findByEmail(signinDTO.getEmail())).thenReturn(Optional.empty());
        authService.signin(signinDTO);
    }

    @Test(expected = UnauthorizedException.class)
    public void 로그인_실패_비밀번호_불일치() throws Exception {
        when(userRepository.findByEmail(signinDTO.getEmail())).thenReturn(Optional.of(new User()));
        when(passwordEncoder.matches(signinDTO.getPassword(), null)).thenReturn(false);
        authService.signin(signinDTO);
    }

}