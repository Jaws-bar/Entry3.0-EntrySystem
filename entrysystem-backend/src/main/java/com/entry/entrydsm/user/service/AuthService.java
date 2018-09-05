package com.entry.entrydsm.user.service;

import com.entry.entrydsm.common.exception.ConflictException;
import com.entry.entrydsm.user.domain.TempUser;
import com.entry.entrydsm.user.domain.TempUserRepository;
import com.entry.entrydsm.user.domain.UserRepository;
import com.entry.entrydsm.user.dto.SignupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Service
public class AuthService {

    @Autowired
    private TempUserRepository tempUserRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public TempUser signup(@Valid @RequestBody SignupDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new ConflictException("email", "이메일이 중복되었습니다.");
        }
        tempUserRepository.deleteByEmail(dto.getEmail());
        tempUserRepository.flush();
        return tempUserRepository.save(dto.toTempUser(passwordEncoder));
    }
}
