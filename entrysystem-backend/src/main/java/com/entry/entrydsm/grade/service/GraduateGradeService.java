package com.entry.entrydsm.grade.service;

import com.entry.entrydsm.grade.dto.GradeResponse;
import com.entry.entrydsm.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class GraduateGradeService implements GradeService {
    @Override
    public GradeResponse get(User user) {
        return new GradeResponse(user, user.getGrades(), user.getGraduateScore());
    }
}
