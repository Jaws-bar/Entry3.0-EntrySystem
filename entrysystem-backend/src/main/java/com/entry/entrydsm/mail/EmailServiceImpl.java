package com.entry.entrydsm.mail;

import com.entry.entrydsm.user.domain.tempuser.TempUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@EnableAsync
@Service
@Profile({"production", "local"})
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${url}")
    private String baseUrl;

    @Async
    @Override
    public void sendMessage(String to, String title, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(title);
        message.setText(body);
        mailSender.send(message);
    }

    @Async
    @Override
    public void sendConfirmCode(String email, TempUser tempUser) {
        sendMessage(email, "[대덕소프트웨어마이스터고등학교 입학전형시스템] 회원가입 인증 링크입니다.", "다음 링크를 클릭하세요.\n" +
                String.format("%s/%s", baseUrl, tempUser.getCode()));
    }
}
