package com.entry.entrydsm.grade.service;

import com.entry.entrydsm.common.exception.RequestValidationException;
import com.entry.entrydsm.grade.dto.GradeDTO;
import com.entry.entrydsm.grade.dto.GradeResponse;
import com.entry.entrydsm.user.domain.User;

public interface GradeService {

    default GradeResponse get(User user) {
        return new GradeResponse(user, user.getGrades(), user.getGraduateScore(), user.getGedScore());
    }

    GradeResponse put(User user, GradeDTO dto) throws RequestValidationException;
}
