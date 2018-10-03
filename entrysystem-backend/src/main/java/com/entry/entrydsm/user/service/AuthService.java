package com.entry.entrydsm.user.service;

import com.entry.entrydsm.common.response.JwtToken;
import com.entry.entrydsm.user.domain.User;
import com.entry.entrydsm.user.domain.tempuser.TempUser;
import com.entry.entrydsm.user.dto.SigninDTO;
import com.entry.entrydsm.user.dto.SignupDTO;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.SendFailedException;
import javax.validation.Valid;

public interface AuthService {

    TempUser signup(@Valid @RequestBody SignupDTO dto) throws SendFailedException;

    User confirm(String code);

    JwtToken signin(SigninDTO dto) throws Exception;
}
