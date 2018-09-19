package com.entry.entrydsm.user.service;

import com.entry.entrydsm.common.exception.BadRequestException;
import com.entry.entrydsm.common.exception.ConflictException;
import com.entry.entrydsm.common.exception.UnauthorizedException;
import com.entry.entrydsm.common.response.JwtToken;
import com.entry.entrydsm.common.security.jwt.Jwt;
import com.entry.entrydsm.info.domain.graduate.GraduateInfoRepository;
import com.entry.entrydsm.mail.EmailService;
import com.entry.entrydsm.user.domain.User;
import com.entry.entrydsm.user.domain.UserRepository;
import com.entry.entrydsm.user.domain.tempuser.TempUser;
import com.entry.entrydsm.user.domain.tempuser.TempUserRepository;
import com.entry.entrydsm.user.dto.SigninDTO;
import com.entry.entrydsm.user.dto.SignupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.PostConstruct;
import javax.mail.SendFailedException;
import javax.validation.Valid;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private TempUserRepository tempUserRepository;

    @Autowired
    private GraduateInfoRepository graduateInfoRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private Jwt jwt;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.prefix}")
    private String prefix;

    @PostConstruct
    private void init() {
        this.prefix += " ";
    }

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
        User user = new User(tempUser);
        tempUserRepository.delete(tempUser);
        user.initialize();
        return userRepository.save(user);
    }

    public Optional<User> validateToken(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith(prefix)) {
            return Optional.empty();
        }
        try {
            return userRepository.findById(jwt.getUserId(authorizationHeader.substring(prefix.length())));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public JwtToken signin(SigninDTO dto) throws Exception {
        return jwt.createToken(userRepository.findByEmail(dto.getEmail())
                .filter(user -> user.matchPassword(dto.getPassword(), passwordEncoder))
                .orElseThrow(UnauthorizedException::new));
    }
}
