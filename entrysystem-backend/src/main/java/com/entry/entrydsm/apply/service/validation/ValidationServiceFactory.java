package com.entry.entrydsm.apply.service.validation;

import com.entry.entrydsm.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationServiceFactory {

    private final GedValidationService gedValidationService;

    private final GraduateValidationService graduateValidationService;

    @Autowired
    public ValidationServiceFactory(GedValidationService gedValidationService, GraduateValidationService graduateValidationService) {
        this.gedValidationService = gedValidationService;
        this.graduateValidationService = graduateValidationService;
    }

    public ValidationService getService(User user) {
        if (user.getGraduateType().isGed()) {
            return gedValidationService;
        }
        return graduateValidationService;
    }
}
