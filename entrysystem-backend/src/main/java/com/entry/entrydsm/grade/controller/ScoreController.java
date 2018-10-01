package com.entry.entrydsm.grade.controller;

import com.entry.entrydsm.common.config.AuthRequired;
import com.entry.entrydsm.common.response.RestResponse;
import com.entry.entrydsm.common.service.CalculationProxy;
import com.entry.entrydsm.grade.dto.ScoreResponse;
import com.entry.entrydsm.grade.service.ScoreService;
import com.entry.entrydsm.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/me/score")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @GetMapping
    @Transactional
    @AuthRequired(allowSubmitted = true)
    public RestResponse<ScoreResponse> getScore(User user) {
        CalculationProxy.calculateIfValid(user);
        return RestResponse.success(scoreService.getScore(user));
    }
}
