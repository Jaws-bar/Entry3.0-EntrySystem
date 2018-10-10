package com.entry.entrydsm.user.service;

import com.entry.entrydsm.common.exception.ConflictException;
import com.entry.entrydsm.common.response.JwtToken;
import com.entry.entrydsm.user.domain.User;
import com.entry.entrydsm.user.domain.UserRepository;
import com.entry.entrydsm.user.domain.tempuser.TempUser;
import com.entry.entrydsm.user.dto.SigninDTO;
import com.entry.entrydsm.user.dto.SignupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.SendFailedException;
import javax.validation.Valid;

@Service("test-auth-service")
public class TestAuthService implements AuthService {

    @Autowired
    private RealAuthService realAuthService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public TempUser signup(@Valid SignupDTO dto) throws SendFailedException {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new ConflictException("email", "이메일이 중복되었습니다.");
        }
        TempUser tempUser = dto.toTempUser(passwordEncoder);
        userRepository.save(new User(tempUser));
        return tempUser;
    }

    @Override
    @Transactional
    public User confirm(String code) {
        return realAuthService.confirm(code);
    }

    @Override
    @Transactional
    public JwtToken signin(SigninDTO dto) throws Exception {
        return realAuthService.signin(dto);
    }
}
