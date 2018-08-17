package com.entry.entrydsm.tempuser.service;

import com.entry.entrydsm.apply.ApplyStatus;
import com.entry.entrydsm.apply.ApplyStatusRepository;
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
import com.entry.entrydsm.tempuser.model.TempUser;
import com.entry.entrydsm.tempuser.model.TempUserDTO;
import com.entry.entrydsm.tempuser.model.TempUserRepository;
import com.entry.entrydsm.user.User;
import com.entry.entrydsm.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TempUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TempUserRepository tempUserRepository;
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
    @Autowired
    private EmailServiceImpl emailService;

    public void signup(TempUserDTO tempUserDTO) {
        if (userRepository.existsUserByEmail(String.valueOf(tempUserDTO.getEmail())) ||
                tempUserRepository.existsTempUserByEmail(String.valueOf(tempUserDTO.getEmail()))) {
            throw new ForbiddenException("Already registered email");
        }
        emailService.sendMessage(tempUserDTO.getEmail(), "EntryDSM 인증 메일", "인증 코드 : " + tempUserDTO.getCode());
        tempUserRepository.save(tempUserDTO.toEntity());
    }

    public void validationTempUser(String code) {
        TempUser tempUser = tempUserRepository.findByCode(code)
                .orElseThrow(() -> new BadRequestException("Not Found Code"));
        User user = new User(tempUser);

        userRepository.save(user);
        graduateInfoRepository.save(new GraduateInfo(user));
        applyStatusRepository.save(new ApplyStatus(user));
        infoRepository.save(new Info(user));
        gedGradeRepository.save(new GedGrade(user));
        graduateGradeRepository.save(new GraduateGrade(user));
        tempUserRepository.delete(tempUser);
    }
}
