package com.entry.entrydsm.info.controller;

import com.entry.entrydsm.common.config.AuthRequired;
import com.entry.entrydsm.common.exception.ValidationException;
import com.entry.entrydsm.common.response.RestResponse;
import com.entry.entrydsm.common.validate.ValidationUtil;
import com.entry.entrydsm.info.dto.InfoDTO;
import com.entry.entrydsm.info.dto.InfoResponse;
import com.entry.entrydsm.info.service.InfoService;
import com.entry.entrydsm.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/me/info")
public class InfoController {

    @Autowired
    private InfoService infoService;

    @PutMapping
    @AuthRequired
    public RestResponse<InfoResponse> putInfo(User user, @RequestBody InfoDTO infoDTO) throws ValidationException {
        ValidationUtil.validate(infoDTO, user);
        return RestResponse.success(infoService.putInfo(user, infoDTO));
    }

    @GetMapping
    @AuthRequired
    public RestResponse<InfoResponse> getInfo(User user) {
        return RestResponse.success(infoService.getInfo(user));
    }


}
