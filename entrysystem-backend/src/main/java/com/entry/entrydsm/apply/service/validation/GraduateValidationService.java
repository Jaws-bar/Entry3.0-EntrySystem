package com.entry.entrydsm.apply.service.validation;

import com.entry.entrydsm.apply.dto.ValidationResult;
import com.entry.entrydsm.common.exception.ValidationException;
import com.entry.entrydsm.common.response.RestResponse;
import com.entry.entrydsm.common.validate.ValidationUtil;
import com.entry.entrydsm.grade.domain.graduate.GraduateGrade;
import com.entry.entrydsm.grade.domain.graduate.GraduateScore;
import com.entry.entrydsm.info.domain.graduate.GraduateInfo;
import com.entry.entrydsm.user.domain.GraduateType;
import com.entry.entrydsm.user.domain.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GraduateValidationService extends BaseValidationService implements ValidationService {
    private static int WILL_SEMESTER_COUNT = 5;
    private static int DONE_SEMESTER_COUNT = 6;

    @Override
    public ValidationResult validate(User user) {
        return ValidationResult.builder()
                .classificationErrors(validateClassification(user))
                .infoErrors(validateInfo(user))
                .gradeErrors(validateGrade(user))
                .documentErrors(validateDocument(user))
                .build();
    }

    private List<RestResponse.Error> validateGrade(User user) {
        List<RestResponse.Error> errors = validateScore(user);
        if (user.getGraduateType() == GraduateType.WILL) {
            errors.addAll(validateGradeWill(user));
            return errors;
        }
        errors.addAll(validateGradeDone(user));
        return errors;
    }

    private List<RestResponse.Error> validateScore(User user) {
        GraduateScore graduateScore = user.getGraduateScore();
        try {
            ValidationUtil.validate(graduateScore);
        } catch (ValidationException e) {
            return ValidationUtil.toErrors(e.getValidationErrors());
        }
        return new ArrayList<>();
    }

    private List<RestResponse.Error> validateGradeWill(User user) {
        List<GraduateGrade> grades = user.getGrades();
        List<RestResponse.Error> errors = new ArrayList<>();

        if (!gradeInputCheck(grades, WILL_SEMESTER_COUNT)) {
            errors.add(new RestResponse.Error("성적 입력이 완료되지 않았습니다."));
        }

        if (grades.get(WILL_SEMESTER_COUNT - 1).isSkipped()) {
            errors.add(new RestResponse.Error("3학년 1학기 성적은 필수입니다."));
        }

        return errors;
    }

    private List<RestResponse.Error> validateGradeDone(User user) {
        List<GraduateGrade> grades = user.getGrades();
        List<RestResponse.Error> errors = new ArrayList<>();

        if (!gradeInputCheck(grades, DONE_SEMESTER_COUNT)) {
            errors.add(new RestResponse.Error("성적 입력이 완료되지 않았습니다."));
        }

        // TODO: Check validation logic
        if (grades.get(DONE_SEMESTER_COUNT - 1).isSkipped() && grades.get(DONE_SEMESTER_COUNT - 2).isSkipped()) {
            errors.add(new RestResponse.Error("3학년 1, 2학기 성적은 필수입니다."));
        }

        return errors;
    }

    private boolean gradeInputCheck(List<GraduateGrade> grades, int semesterCount) {
        return grades.stream().limit(semesterCount).allMatch(GraduateGrade::isInput);
    }

    @Override
    public List<RestResponse.Error> validateInfo(User user) {
        List<RestResponse.Error> errors = super.validateInfo(user);
        GraduateInfo graduateInfo = user.getGraduateInfo();

        try {
            ValidationUtil.validate(graduateInfo);
        } catch (ValidationException e) {
            errors.addAll(ValidationUtil.toErrors(e.getValidationErrors()));
        }

        return errors;
    }

}
