package com.entry.entrydsm.mypage.controller;

import com.entry.entrydsm.apply.service.validation.ValidationServiceFactory;
import com.entry.entrydsm.common.config.AuthRequired;
import com.entry.entrydsm.common.response.RestResponse;
import com.entry.entrydsm.user.domain.User;
import com.entry.entrydsm.user.dto.MyPageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/me/mypage")
public class MyPageController {

    @Autowired
    private ValidationServiceFactory validationServiceFactory;

    @GetMapping
    @AuthRequired
    public RestResponse<MyPageResponse> getMyPage(User user) {
        return RestResponse.success(new MyPageResponse(user.getGraduateType(),
                validationServiceFactory.getService(user).validate(user),
                user.getApplyStatus()));
    }

}
