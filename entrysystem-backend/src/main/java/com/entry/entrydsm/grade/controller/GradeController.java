package com.entry.entrydsm.grade.controller;

import com.entry.entrydsm.common.config.AuthRequired;
import com.entry.entrydsm.common.exception.RequestValidationException;
import com.entry.entrydsm.common.response.RestResponse;
import com.entry.entrydsm.grade.dto.GradeDTO;
import com.entry.entrydsm.grade.dto.GradeResponse;
import com.entry.entrydsm.grade.service.GradeServiceFactory;
import com.entry.entrydsm.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/me/grade")
public class GradeController {

    @Autowired
    private GradeServiceFactory gradeServiceFactory;

    @GetMapping
    @AuthRequired(allowSubmitted = true)
    public RestResponse<GradeResponse> get(User user) {
        return RestResponse.success(gradeServiceFactory.getService(user).get(user));
    }

    @AuthRequired
    @PutMapping
    public RestResponse<GradeResponse> put(User user, @RequestBody GradeDTO dto) throws RequestValidationException {
        return RestResponse.success(gradeServiceFactory.getService(user).put(user, dto));
    }

}
