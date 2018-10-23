package com.entry.entrydsm.mail;

import com.entry.entrydsm.user.domain.tempuser.TempUser;

import javax.mail.SendFailedException;

public interface EmailService {
    void sendMessage(String to, String title, String body) throws SendFailedException;

    void sendConfirmCode(String email, TempUser tempUser);

    void sendPasswordResetCode(String email, String generatePasswordResetCode);
}
