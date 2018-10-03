package com.entry.entrydsm.common.service;

import com.entry.entrydsm.user.domain.GraduateType;
import com.entry.entrydsm.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CalculationServiceFactory {

    private final GedCalculationService gedCalculationService;

    private final GraduateCalculationService graduateCalculationService;

    @Autowired
    public CalculationServiceFactory(GedCalculationService gedCalculationService, GraduateCalculationService graduateCalculationService) {
        this.gedCalculationService = gedCalculationService;
        this.graduateCalculationService = graduateCalculationService;
    }


    public CalculationService getService(User user) {
        if (user.getGraduateType() == GraduateType.GED) {
            return gedCalculationService;
        }
        return graduateCalculationService;
    }
}
