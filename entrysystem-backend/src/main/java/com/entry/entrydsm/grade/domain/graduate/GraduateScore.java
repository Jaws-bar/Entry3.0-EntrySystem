package com.entry.entrydsm.grade.domain.graduate;

import com.entry.entrydsm.grade.domain.Score;
import com.entry.entrydsm.grade.dto.GradeDTO;
import com.entry.entrydsm.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor
public class GraduateScore extends Score {

    @Column(nullable = false)
    private Double firstGrade;

    @Column(nullable = false)
    private Double secondGrade;

    @Column(nullable = false)
    private Double thirdGrade;

    @Column(nullable = false, length = 10)
    @NotNull
    @Min(value = 0)
    private Integer volunteerTime;

    @Column(nullable = false, length = 10)
    @NotNull
    @Min(value = 0)
    private Integer periodCut;

    @Column(nullable = false, length = 10)
    @NotNull
    @Min(value = 0)
    private Integer fullCut;

    @Column(nullable = false, length = 10)
    @NotNull
    @Min(value = 0)
    private Integer late;

    @Column(nullable = false, length = 10)
    @NotNull
    @Min(value = 0)
    private Integer earlyLeave;

    public GraduateScore(User user) {
        super(user, 0);
        this.earlyLeave = 0;
        this.late = 0;
        this.fullCut = 0;
        this.periodCut = 0;
        this.volunteerTime = 0;
        this.thirdGrade = 0.0;
        this.secondGrade = 0.0;
        this.firstGrade = 0.0;
    }

    // TODO: Calculate
    public void update(GradeDTO dto) {
        this.earlyLeave = dto.getEarlyLeave();
        this.late = dto.getLate();
        this.fullCut = dto.getFullCut();
        this.periodCut = dto.getPeriodCut();
        this.volunteerTime = dto.getVolunteerTime();
    }

    public void setFirstGrade(double firstGrade) {
        this.firstGrade = firstGrade;
        syncConversionScore();
    }

    private void syncConversionScore() {
        setConversionScore(firstGrade + secondGrade + thirdGrade);
    }

    public void setSecondGrade(double secondGrade) {
        this.secondGrade = secondGrade;
        syncConversionScore();
    }

    public void setThirdGrade(double thirdGrade) {
        this.thirdGrade = thirdGrade;
        syncConversionScore();
    }
}
