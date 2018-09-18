package com.entry.entrydsm.grade.domain.graduate;

import com.entry.entrydsm.grade.domain.Score;
import com.entry.entrydsm.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

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
    private Integer volunteerTime;

    @Column(nullable = false, length = 10)
    private Integer periodCut;

    @Column(nullable = false, length = 10)
    private Integer fullCut;

    @Column(nullable = false, length = 10)
    private Integer late;

    @Column(nullable = false, length = 10)
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
}
