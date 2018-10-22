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
    private int receiptCode;

    private ScoreResponse(GraduateType graduateType, Score score, int receiptCode) {
        super(graduateType);
        this.conversionScore = score.getConversionScore();
        this.attendanceScore = score.getAttendanceScore();
        this.volunteerScore = score.getVolunteerScore();
        this.finalScore = score.getFinalScore();
        this.receiptCode = receiptCode;
    }

    public ScoreResponse(GraduateType graduateType, GedScore gedScore, int receiptCode) {
        this(graduateType, (Score) gedScore, receiptCode);
    }

    public ScoreResponse(GraduateType graduateType, GraduateScore graduateScore, int receiptCode) {
        this(graduateType, (Score) graduateScore, receiptCode);
        this.firstGrade = graduateScore.getFirstGrade();
        this.secondGrade = graduateScore.getSecondGrade();
        this.thirdGrade = graduateScore.getThirdGrade();
    }
}
