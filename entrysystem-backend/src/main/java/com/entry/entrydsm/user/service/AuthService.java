package com.entry.entrydsm.user.service;

import com.entry.entrydsm.common.exception.BadRequestException;
import com.entry.entrydsm.common.exception.ConflictException;
import com.entry.entrydsm.info.domain.Info;
import com.entry.entrydsm.info.domain.InfoRepository;
import com.entry.entrydsm.mail.EmailService;
import com.entry.entrydsm.user.domain.TempUser;
import com.entry.entrydsm.user.domain.TempUserRepository;
import com.entry.entrydsm.user.domain.User;
import com.entry.entrydsm.user.domain.UserRepository;
import com.entry.entrydsm.user.dto.SignupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.SendFailedException;
import javax.validation.Valid;

@Service
public class AuthService {

    @Autowired
    private TempUserRepository tempUserRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InfoRepository infoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public TempUser signup(@Valid @RequestBody SignupDTO dto) throws SendFailedException {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new ConflictException("email", "이메일이 중복되었습니다.");
        }
        tempUserRepository.deleteByEmail(dto.getEmail());
        tempUserRepository.flush();

        TempUser tempUser = tempUserRepository.save(dto.toTempUser(passwordEncoder));
        emailService.sendConfirmCode(dto.getEmail(), tempUser);

        return tempUser;
    }

    @Transactional
    public User confirm(String code) {
        TempUser tempUser = tempUserRepository.findById(code).orElseThrow(() -> new BadRequestException("올바르지 않은 인증 코드입니다."));
        User user = userRepository.save(new User(tempUser));
        tempUserRepository.delete(tempUser);

        initializeUser(user);
        return user;
    }

    private void initializeUser(User user) {
        infoRepository.save(new Info(user));

    }
}
