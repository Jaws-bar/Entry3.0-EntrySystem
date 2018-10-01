package com.entry.entrydsm.info.controller;

import com.entry.entrydsm.apply.service.validation.ValidationServiceFactory;
import com.entry.entrydsm.common.config.AuthRequired;
import com.entry.entrydsm.common.response.RestResponse;
import com.entry.entrydsm.common.service.CalculationProxy;
import com.entry.entrydsm.common.service.CalculationServiceFactory;
import com.entry.entrydsm.info.dto.ClassificationDTO;
import com.entry.entrydsm.info.dto.ClassificationResponse;
import com.entry.entrydsm.info.service.ClassificationService;
import com.entry.entrydsm.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/me/classification")
public class ClassificationController {

    @Autowired
    private ValidationServiceFactory validationServiceFactory;

    @Autowired
    private CalculationServiceFactory calculationServiceFactory;

    @Autowired
    private ClassificationService classificationService;

    @GetMapping
    @AuthRequired(allowSubmitted = true)
    public RestResponse<ClassificationResponse> getClassification(User user) {
        return RestResponse.success(classificationService.getInfo(user));
    }

    @PutMapping
    @AuthRequired
    @Transactional
    public RestResponse<ClassificationResponse> putClassification(@Valid @RequestBody ClassificationDTO classificationDTO, User user) {
        ClassificationResponse putResult = classificationService.putInfo(user, classificationDTO);
        CalculationProxy.calculateIfValid(user);
        return RestResponse.success(putResult);

    }

}
