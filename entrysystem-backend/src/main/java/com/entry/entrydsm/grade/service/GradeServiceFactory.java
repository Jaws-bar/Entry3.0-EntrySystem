package com.entry.entrydsm.grade.service;

import com.entry.entrydsm.user.domain.GraduateType;
import com.entry.entrydsm.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GradeServiceFactory {

    @Autowired
    private GedGradeService gedGradeService;

    @Autowired
    private GraduateGradeService graduateGradeService;

    public GradeService getService(User user) {
        if (user.getGraduateType() == GraduateType.GED) {
            return gedGradeService;
        }
        return graduateGradeService;
    }
}
