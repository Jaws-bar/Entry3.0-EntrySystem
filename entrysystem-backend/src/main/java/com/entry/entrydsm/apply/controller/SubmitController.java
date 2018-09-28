package com.entry.entrydsm.apply.controller;

import com.entry.entrydsm.apply.domain.ApplyStatus;
import com.entry.entrydsm.apply.dto.ValidationResult;
import com.entry.entrydsm.apply.service.validation.ValidationServiceFactory;
import com.entry.entrydsm.common.config.AuthRequired;
import com.entry.entrydsm.common.exception.SubmitValidationException;
import com.entry.entrydsm.common.response.RestResponse;
import com.entry.entrydsm.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/submit")
public class SubmitController {

    @Autowired
    private ValidationServiceFactory serviceFactory;

    @PostMapping
    @AuthRequired
    @Transactional
    public RestResponse<ApplyStatus> submit(User user) {
        ValidationResult validationResult = serviceFactory.getService(user).validate(user);
        if (!validationResult.isValid()) {
            throw new SubmitValidationException(validationResult);
        }
        user.submit();
        return RestResponse.success(user.getApplyStatus());
    }
}
