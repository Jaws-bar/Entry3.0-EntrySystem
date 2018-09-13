package com.entry.entrydsm.info.service;

import com.entry.entrydsm.common.exception.BadRequestException;
import com.entry.entrydsm.common.exception.ResourceNotFoundException;
import com.entry.entrydsm.info.domain.Info;
import com.entry.entrydsm.info.domain.InfoRepository;
import com.entry.entrydsm.info.domain.graduate.GraduateInfo;
import com.entry.entrydsm.info.domain.graduate.GraduateInfoRepository;
import com.entry.entrydsm.info.dto.InfoDTO;
import com.entry.entrydsm.info.dto.InfoResponse;
import com.entry.entrydsm.school.domain.School;
import com.entry.entrydsm.user.domain.GraduateType;
import com.entry.entrydsm.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InfoService {

    @Autowired
    private InfoRepository infoRepository;

    @Autowired
    private GraduateInfoRepository graduateInfoRepository;

    @Autowired
    private com.entry.entrydsm.school.domain.SchoolRepository schoolRepository;

    public InfoResponse getInfo(User user) {
        Info info = infoRepository.findById(user.getId()).orElseThrow(ResourceNotFoundException::new);
        if (user.getGraduateType() == GraduateType.GED) {
            return new InfoResponse(user.getGraduateType(), info);
        }
        GraduateInfo graduateInfo = graduateInfoRepository.findById(user.getId()).orElseThrow(ResourceNotFoundException::new);
        return new InfoResponse(user.getGraduateType(), info, graduateInfo);
    }

    @Transactional
    public InfoResponse putInfo(User user, InfoDTO infoDTO) {
        Info info = infoRepository.findById(user.getId()).orElseThrow(ResourceNotFoundException::new);
        info.update(infoDTO);
        if (user.getGraduateType() == GraduateType.GED) {
            return new InfoResponse(user.getGraduateType(), info);
        }
        GraduateInfo graduateInfo = graduateInfoRepository.findById(user.getId()).orElseThrow(ResourceNotFoundException::new);
        graduateInfo.update(infoDTO, findSchool(infoDTO));
        return new InfoResponse(user.getGraduateType(), info, graduateInfo);
    }

    private School findSchool(InfoDTO infoDTO) {
        String schoolCode = infoDTO.getSchoolCode();
        if (schoolCode == null) {
            return null;
        }
        return schoolRepository.findById(schoolCode).orElseThrow(BadRequestException::new);
    }
}
