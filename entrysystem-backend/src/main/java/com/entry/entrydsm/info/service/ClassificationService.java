package com.entry.entrydsm.info.service;

import com.entry.entrydsm.common.exception.ResourceNotFoundException;
import com.entry.entrydsm.info.domain.graduate.GraduateInfo;
import com.entry.entrydsm.info.domain.graduate.GraduateInfoRepository;
import com.entry.entrydsm.info.dto.ClassificationDTO;
import com.entry.entrydsm.info.dto.ClassificationResponse;
import com.entry.entrydsm.user.domain.GraduateType;
import com.entry.entrydsm.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClassificationService {

    @Autowired
    private GraduateInfoRepository graduateInfoRepository;

    public ClassificationResponse getInfo(User user) {
        if (user.getGraduateType() == GraduateType.GED) {
            return ClassificationResponse.of(user);
        }
        GraduateInfo graduateInfo = graduateInfoRepository.findById(user.getId()).orElseThrow(ResourceNotFoundException::new);
        return ClassificationResponse.of(user, graduateInfo);
    }


    @Transactional
    public ClassificationResponse putInfo(User user, ClassificationDTO classificationDTO) {
        user.updateInfo(classificationDTO);
        if (classificationDTO.getGraduateType() != GraduateType.GED) {
            GraduateInfo info = graduateInfoRepository.findById(user.getId()).orElseThrow(ResourceNotFoundException::new);
            info.updateClassification(classificationDTO);
            return ClassificationResponse.of(user, info);
        }
        return ClassificationResponse.of(user);
    }
}
