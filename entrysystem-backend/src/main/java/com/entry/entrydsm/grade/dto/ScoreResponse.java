package com.entry.entrydsm.grade.dto;

import com.entry.entrydsm.common.response.WrappedResponse;
import com.entry.entrydsm.grade.domain.Score;
import com.entry.entrydsm.grade.domain.ged.GedScore;
import com.entry.entrydsm.grade.domain.graduate.GraduateScore;
import com.entry.entrydsm.user.domain.GraduateType;
import lombok.Getter;

@Getter
public class ScoreResponse extends WrappedResponse {
    private Double firstGrade;
    private Double secondGrade;
    private Double thirdGrade;
    private Double conversionScore;
    private Integer attendanceScore;
    private Double volunteerScore;
    private Double finalScore;

    private ScoreResponse(GraduateType graduateType, Score score) {
        super(graduateType);
        this.conversionScore = score.getConversionScore();
        this.attendanceScore = score.getAttendanceScore();
        this.volunteerScore = score.getVolunteerScore();
        this.finalScore = score.getFinalScore();
    }

    public ScoreResponse(GraduateType graduateType, GedScore gedScore) {
        this(graduateType, (Score) gedScore);
    }

    public ScoreResponse(GraduateType graduateType, GraduateScore graduateScore) {
        this(graduateType, (Score) graduateScore);
        this.firstGrade = graduateScore.getFirstGrade();
        this.secondGrade = graduateScore.getSecondGrade();
        this.thirdGrade = graduateScore.getThirdGrade();
    }
}
