package com.entry.entrydsm.grade.dto;

import com.entry.entrydsm.common.response.WrappedResponse;
import com.entry.entrydsm.grade.domain.graduate.GraduateGrade;
import com.entry.entrydsm.user.domain.GraduateType;
import com.entry.entrydsm.user.domain.User;
import lombok.Getter;

import java.util.List;

@Getter
public class GradeResponse extends WrappedResponse {
    private final List<GraduateGrade> grades;
    private final Double grade;

    public GradeResponse(GraduateType graduateType) {
        super(graduateType);
        this.grades = null;
        this.grade = null;
    }

    public GradeResponse(User user, List<GraduateGrade> grades, Double grade) {
        super(user.getGraduateType());
        this.grades = grades;
        this.grade = grade;
    }

    public GradeResponse(User user, List<GraduateGrade> grades) {
        this(user, grades, null);
    }

    public GradeResponse(User user, Double grade) {
        this(user, null, grade);
    }

    public static GradeResponse of(User user) {
        if (user.getGraduateType() == GraduateType.GED) {
            return new GradeResponse(user, user.getGrades());
        }
        return new GradeResponse(user, user.getGrades());
    }
}
