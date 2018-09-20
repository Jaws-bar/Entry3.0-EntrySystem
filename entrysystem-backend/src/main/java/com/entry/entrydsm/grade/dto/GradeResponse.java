package com.entry.entrydsm.grade.dto;

import com.entry.entrydsm.common.response.WrappedResponse;
import com.entry.entrydsm.grade.domain.ged.GedScore;
import com.entry.entrydsm.grade.domain.graduate.GraduateGrade;
import com.entry.entrydsm.grade.domain.graduate.GraduateScore;
import com.entry.entrydsm.user.domain.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GradeResponse extends WrappedResponse {
    private final List<GraduateGrade> grades;
    private final Double grade;
    private final Integer volunteerTime;
    private final Integer periodCut;
    private final Integer fullCut;
    private final Integer late;
    private final Integer earlyLeave;

    public GradeResponse(User user, List<GraduateGrade> grades, GraduateScore graduateScore, GedScore gedScore) {
        super(user.getGraduateType());
        this.grade = gedScore.getGrade();
        this.volunteerTime = graduateScore.getVolunteerTime();
        this.grades = grades;
        this.periodCut = graduateScore.getPeriodCut();
        this.fullCut = graduateScore.getFullCut();
        this.late = graduateScore.getLate();
        this.earlyLeave = graduateScore.getEarlyLeave();
    }

    public GradeResponse(User user, List<GraduateGrade> grades, GraduateScore graduateScore) {
        super(user.getGraduateType());
        this.grade = null;
        this.volunteerTime = graduateScore.getVolunteerTime();
        this.grades = grades;
        this.periodCut = graduateScore.getPeriodCut();
        this.fullCut = graduateScore.getFullCut();
        this.late = graduateScore.getLate();
        this.earlyLeave = graduateScore.getEarlyLeave();
    }

    public GradeResponse(User user, GedScore score) {
        super(user.getGraduateType());
        this.grade = score.getGrade();
        this.volunteerTime = null;
        this.grades = null;
        this.periodCut = null;
        this.fullCut = null;
        this.earlyLeave = null;
        this.late = null;
    }
}
