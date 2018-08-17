package com.entry.entrydsm.tempuser.controller;

import com.entry.entrydsm.common.security.Crypto;
import com.entry.entrydsm.tempuser.service.TempUserService;
import com.entry.entrydsm.tempuser.model.TempUserDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/user/temp")
public class TempUserController {

    @Autowired
    private Crypto crypto;
    @Autowired
    private TempUserService tempUserService;

    @ApiOperation(value = "회원가입")
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<Void> signup(@RequestBody TempUserDTO tempUserDTO) {
        tempUserDTO.setPassword(crypto.encode(tempUserDTO.getPassword()));
        tempUserDTO.setCode(UUID.randomUUID().toString().substring(0, 6));
        tempUserService.signup(tempUserDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //TODO : 이메일 재 발송 API 구현 필요


    // TODO : 회원 인증시 FK 참조 테이블 칼럼 생성
    @ApiOperation(value = "회원 인증코드 API")
    @RequestMapping(value = "/{code}", method = RequestMethod.POST)
    public ResponseEntity<Void> certification(@PathVariable String code) {
        tempUserService.validationTempUser(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
