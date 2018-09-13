package com.entry.entrydsm.info.service;

import com.entry.entrydsm.info.domain.graduate.GraduateInfoRepository;
import com.entry.entrydsm.info.dto.ClassificationDTO;
import com.entry.entrydsm.info.dto.ClassificationResponse;
import com.entry.entrydsm.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClassificationService {

    @Autowired
    private GraduateInfoRepository graduateInfoRepository;

    public ClassificationResponse getInfo(User user) {
        return ClassificationResponse.of(user);
    }


    @Transactional
    public ClassificationResponse putInfo(User user, ClassificationDTO classificationDTO) {
        user.updateInfo(classificationDTO);
        return ClassificationResponse.of(user);
    }
}
