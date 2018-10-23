package com.entry.entrydsm.mail;

import com.entry.entrydsm.user.domain.tempuser.TempUser;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.mail.SendFailedException;

@Service
@Profile({"test"})
public class MockMailService implements EmailService {
    @Override
    public void sendMessage(String to, String title, String body) throws SendFailedException {
    }

    @Override
    public void sendConfirmCode(String email, TempUser tempUser) {
    }

    @Override
    public void sendPasswordResetCode(String email, String generatePasswordResetCode) {

    }
}
