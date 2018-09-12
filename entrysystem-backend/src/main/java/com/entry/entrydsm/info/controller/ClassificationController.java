package com.entry.entrydsm.info.controller;

import com.entry.entrydsm.common.config.AuthRequired;
import com.entry.entrydsm.common.response.RestResponse;
import com.entry.entrydsm.info.dto.ClassificationDTO;
import com.entry.entrydsm.info.dto.ClassificationResponse;
import com.entry.entrydsm.info.service.ClassificationService;
import com.entry.entrydsm.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.Validator;

@RestController
@RequestMapping("/api/me/classification")
public class ClassificationController {
    @Autowired
    private Validator validator;

    @Autowired
    private ClassificationService classificationService;

    @GetMapping
    @AuthRequired
    public RestResponse<ClassificationResponse> getInfo(User user) {
        return RestResponse.success(classificationService.getInfo(user));
    }

    @PutMapping
    @AuthRequired
    public RestResponse<ClassificationResponse> putInfo(@Valid @RequestBody ClassificationDTO classificationDTO, User user) {
//        ValidationUtil.validate(classificationDTO, Ged.class);
        return RestResponse.success(classificationService.putInfo(user, classificationDTO));
    }

}
