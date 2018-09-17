package com.entry.entrydsm.grade.domain.graduate;

import com.entry.entrydsm.common.domain.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@NoArgsConstructor
@Getter
public class GraduateGrade extends BaseTimeEntity {

    @EmbeddedId
    @JsonUnwrapped
    private GradeInfoId id;

    @Enumerated(EnumType.STRING)
    private Grade korean;
    @Enumerated(EnumType.STRING)
    private Grade social;
    @Enumerated(EnumType.STRING)
    private Grade history;
    @Enumerated(EnumType.STRING)
    private Grade math;
    @Enumerated(EnumType.STRING)
    private Grade science;
    @Enumerated(EnumType.STRING)
    private Grade tech;
    @Enumerated(EnumType.STRING)
    private Grade english;

    @Builder
    public GraduateGrade(GradeInfoId id, Grade korean, Grade social, Grade history, Grade math, Grade science, Grade tech, Grade english) {
        this.id = id;
        this.korean = korean;
        this.social = social;
        this.history = history;
        this.math = math;
        this.science = science;
        this.tech = tech;
        this.english = english;
    }
}
