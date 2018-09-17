package com.entry.entrydsm.grade.controller;

import com.entry.entrydsm.common.config.AuthRequired;
import com.entry.entrydsm.common.response.RestResponse;
import com.entry.entrydsm.grade.dto.GradeResponse;
import com.entry.entrydsm.user.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/me/grade")
public class GradeController {

    @AuthRequired
    @GetMapping
    public RestResponse<GradeResponse> get(User user) {
        return RestResponse.success(GradeResponse.of(user));
    }

}
