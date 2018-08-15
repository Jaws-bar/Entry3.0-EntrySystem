package com.entry.entrydsm.grade.domain;

import com.entry.entrydsm.grade.domain.ged.GedGrade;
import com.entry.entrydsm.grade.domain.graduate.GraduateGrade;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@JsonSubTypes({
        @JsonSubTypes.Type(value = GedGrade.class, name = "GED"),
        @JsonSubTypes.Type(value = GraduateGrade.class, name = "GRADUATE"),
})
@MappedSuperclass
public abstract class Grade {
    @Id
    @Column(length = 32)
    private String userId;

    @Column(nullable = false)
    private Double conversionScore;

    @Column(nullable = false, length = 10)
    private Integer attendanceScore;

    @Column(nullable = false)
    private Double volunteerScore;

    @Column(nullable = false)
    private Double finalScore;

    public Grade(String userId) {
        this.userId = userId;
    }
}
