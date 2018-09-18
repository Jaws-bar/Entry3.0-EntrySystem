package com.entry.entrydsm.grade.service;

import com.entry.entrydsm.common.exception.ValidationException;
import com.entry.entrydsm.common.validate.ValidationUtil;
import com.entry.entrydsm.grade.domain.graduate.GraduateGrade;
import com.entry.entrydsm.grade.domain.graduate.GraduateScore;
import com.entry.entrydsm.grade.dto.GradeDTO;
import com.entry.entrydsm.grade.dto.GradeResponse;
import com.entry.entrydsm.grade.dto.GraduateGradeDTO;
import com.entry.entrydsm.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GraduateGradeService implements GradeService {
    @Override
    public GradeResponse get(User user) {
        return new GradeResponse(user, user.getGrades(), user.getGraduateScore());
    }

    @Override
    @Transactional
    public GradeResponse put(User user, GradeDTO dto) throws ValidationException {
        ValidationUtil.validate(dto, user);
        List<GraduateGrade> grades = user.getGrades();
        List<GraduateGradeDTO> graduateGradeDTOs = dto.getGrades();
        GraduateScore graduateScore = user.getGraduateScore();

        for (int i = 0; i < graduateGradeDTOs.size(); i++) {
            grades.get(i).update(graduateGradeDTOs.get(i));
        }

        graduateScore.update(dto);
        return new GradeResponse(user, grades, graduateScore);
    }
}
