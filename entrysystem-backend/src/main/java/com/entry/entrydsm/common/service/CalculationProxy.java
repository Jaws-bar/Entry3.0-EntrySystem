package com.entry.entrydsm.common.service;

import com.entry.entrydsm.apply.service.validation.GedValidationService;
import com.entry.entrydsm.apply.service.validation.GraduateValidationService;
import com.entry.entrydsm.apply.service.validation.ValidationService;
import com.entry.entrydsm.apply.service.validation.ValidationServiceFactory;
import com.entry.entrydsm.user.domain.User;

public class CalculationProxy {

    public static void calculateIfValid(User user) {
        CalculationService calculationService =
                new CalculationServiceFactory(new GedCalculationService(), new GraduateCalculationService())
                        .getService(user);
        ValidationService validationService =
                new ValidationServiceFactory(new GedValidationService(), new GraduateValidationService())
                        .getService(user);
        if (validationService.validateGrade(user).isEmpty()) {
            calculationService.calculate(user);
        }
    }
}
