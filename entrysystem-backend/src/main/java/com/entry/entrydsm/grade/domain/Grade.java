package com.entry.entrydsm.grade.domain;

import com.entry.entrydsm.grade.domain.ged.GedGrade;
import com.entry.entrydsm.grade.domain.graduate.GraduateGrade;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@JsonSubTypes({
        @JsonSubTypes.Type(value = GedGrade.class, name = "GED"),
        @JsonSubTypes.Type(value = GraduateGrade.class, name = "GRADUATE"),
})
@MappedSuperclass
@NoArgsConstructor
public abstract class Grade {
    @Id
    @Column(length = 32)
    private String userId;

    @Column(nullable = false)
    private Double conversionScore = 0.0;

    @Column(length = 10)
    private Integer attendanceScore = null;

    @Column(nullable = false)
    private Double volunteerScore = 0.0;

    @Column(nullable = false)
    private Double finalScore = 0.0;

    public Grade(String userId) {
        this.userId = userId;
    }
}
