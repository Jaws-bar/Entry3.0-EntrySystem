package com.entry.entrydsm.tempuser;

import com.entry.entrydsm.apply.ApplyStatus;
import com.entry.entrydsm.apply.ApplyStatusRepository;
import com.entry.entrydsm.common.security.Crypto;
import com.entry.entrydsm.exception.BadRequestException;
import com.entry.entrydsm.exception.ForbiddenException;
import com.entry.entrydsm.grade.domain.ged.GedGrade;
import com.entry.entrydsm.grade.domain.ged.GedGradeRepository;
import com.entry.entrydsm.grade.domain.graduate.GraduateGrade;
import com.entry.entrydsm.grade.domain.graduate.GraduateGradeRepository;
import com.entry.entrydsm.graduate.info.GraduateInfo;
import com.entry.entrydsm.graduate.info.GraduateInfoRepository;
import com.entry.entrydsm.info.domain.Info;
import com.entry.entrydsm.info.domain.InfoRepository;
import com.entry.entrydsm.mail.EmailServiceImpl;
import com.entry.entrydsm.user.User;
import com.entry.entrydsm.user.UserRepository;
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
    private UserRepository userRepository;
    @Autowired
    private TempUserRepository tempUserRepository;
    @Autowired
    private Crypto crypto;
    @Autowired
    private EmailServiceImpl emailService;
    @Autowired
    private GraduateInfoRepository graduateInfoRepository;
    @Autowired
    private ApplyStatusRepository applyStatusRepository;
    @Autowired
    private InfoRepository infoRepository;
    @Autowired
    private GedGradeRepository gedGradeRepository;
    @Autowired
    private GraduateGradeRepository graduateGradeRepository;

    @ApiOperation(value = "회원가입")
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<Void> signup(@RequestBody TempUserDTO tempUserDTO) {
        if (userRepository.existsUserByEmail(String.valueOf(tempUserDTO.getEmail())) || tempUserRepository.existsTempUserByEmail(String.valueOf(tempUserDTO.getEmail()))) {
            throw new ForbiddenException("Already registered email");
        }
        tempUserDTO.setPassword(crypto.encode(tempUserDTO.getPassword()));
        tempUserDTO.setCode(UUID.randomUUID().toString().substring(0, 6));
        emailService.sendMessage(tempUserDTO.getEmail(), "EntryDSM 인증 메일", "인증 코드 : " + tempUserDTO.getCode());
        tempUserRepository.save(tempUserDTO.toEntity());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //TODO : 이메일 재 발송 API 구현 필요


    // TODO : 회원 인증시 FK 참조 테이블 칼럼 생성
    @ApiOperation(value = "회원 인증코드 API")
    @RequestMapping(value = "/{code}", method = RequestMethod.POST)
    public ResponseEntity<Void> certification(@PathVariable String code) {
        TempUser tempUser = tempUserRepository.findByCode(code).orElseThrow(() -> new BadRequestException("Not Found Code"));
        User user = new User(tempUser);
        userRepository.save(user);
        graduateInfoRepository.save(new GraduateInfo(user));
        applyStatusRepository.save(new ApplyStatus(user));
        infoRepository.save(new Info(user));
        gedGradeRepository.save(new GedGrade(user));
        graduateGradeRepository.save(new GraduateGrade(user));
        tempUserRepository.delete(tempUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
