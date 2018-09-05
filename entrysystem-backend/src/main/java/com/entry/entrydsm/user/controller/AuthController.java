package com.entry.entrydsm.user.controller;

import com.entry.entrydsm.common.response.RestResponse;
import com.entry.entrydsm.user.domain.TempUser;
import com.entry.entrydsm.user.dto.SignupDTO;
import com.entry.entrydsm.user.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public RestResponse<TempUser> signup(@Valid @RequestBody SignupDTO dto) {
        return RestResponse.success(authService.signup(dto));
    }
}
