package com.entry.entrydsm.grade.domain.graduate;

import com.entry.entrydsm.grade.domain.Grade;
import com.entry.entrydsm.user.User;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class GraduateGrade extends Grade {

    @Column(nullable = false)
    private Double firstGrade = 0.0;

    @Column(nullable = false)
    private Double secondGrade = 0.0;

    @Column(nullable = false)
    private Double thirdGrade = 0.0;

    @Column(nullable = false, length = 10)
    private Integer volunteerTime = 0;

    @Column(nullable = false, length = 10)
    private Integer periodCut = 0;

    @Column(nullable = false, length = 10)
    private Integer fullCut = 0;

    @Column(nullable = false, length = 10)
    private Integer late = 0;

    @Column(nullable = false, length = 10)
    private Integer earlyLeave = 0;

    public GraduateGrade(User user) {
        super(user.getId());
    }
}
