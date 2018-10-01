package com.entry.entrydsm.info.controller;

import com.entry.entrydsm.common.config.AuthRequired;
import com.entry.entrydsm.common.exception.RequestValidationException;
import com.entry.entrydsm.common.response.RestResponse;
import com.entry.entrydsm.common.service.CalculationProxy;
import com.entry.entrydsm.common.validate.ValidationUtil;
import com.entry.entrydsm.info.dto.InfoDTO;
import com.entry.entrydsm.info.dto.InfoResponse;
import com.entry.entrydsm.info.service.InfoService;
import com.entry.entrydsm.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/me/info")
public class InfoController {

    @Autowired
    private InfoService infoService;

    @PutMapping
    @AuthRequired
    @Transactional
    public RestResponse<InfoResponse> putInfo(User user, @RequestBody InfoDTO infoDTO) throws RequestValidationException {
        ValidationUtil.validate(infoDTO, user);
        CalculationProxy.calculateIfValid(user);
        return RestResponse.success(infoService.putInfo(user, infoDTO));
    }

    @GetMapping
    @AuthRequired(allowSubmitted = true)
    public RestResponse<InfoResponse> getInfo(User user) {
        return RestResponse.success(infoService.getInfo(user));
    }


}
