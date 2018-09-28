package com.entry.entrydsm.apply.controller;

import com.entry.entrydsm.apply.dto.ValidationResult;
import com.entry.entrydsm.apply.service.validation.ValidationServiceFactory;
import com.entry.entrydsm.common.config.AuthRequired;
import com.entry.entrydsm.common.response.RestResponse;
import com.entry.entrydsm.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/me/validate")
public class ValidationController {

    @Autowired
    private ValidationServiceFactory serviceFactory;

    @GetMapping
    @AuthRequired(allowSubmitted = true)
    public RestResponse<ValidationResult> validate(User user) {
        return RestResponse.success(serviceFactory.getService(user).validate(user));
    }
}
