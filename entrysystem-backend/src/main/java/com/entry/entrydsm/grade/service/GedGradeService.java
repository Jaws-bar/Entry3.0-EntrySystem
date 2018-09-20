package com.entry.entrydsm.grade.service;

import com.entry.entrydsm.common.exception.ValidationException;
import com.entry.entrydsm.common.validate.ValidationUtil;
import com.entry.entrydsm.grade.domain.ged.GedScore;
import com.entry.entrydsm.grade.dto.GradeDTO;
import com.entry.entrydsm.grade.dto.GradeResponse;
import com.entry.entrydsm.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GedGradeService implements GradeService {

    @Override
    @Transactional
    public GradeResponse put(User user, GradeDTO dto) throws ValidationException {
        ValidationUtil.validate(dto, user);
        GedScore gedScore = user.getGedScore();
        gedScore.update(dto);

        // TODO: calculate
        return new GradeResponse(user, user.getGrades(), user.getGraduateScore(), gedScore);
    }
}
