package com.entry.entrydsm.grade.dto;

import com.entry.entrydsm.grade.domain.graduate.Grade;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class GraduateGradeDTO {
    private Grade korean;
    private Grade social;
    private Grade history;
    private Grade math;
    private Grade science;
    private Grade tech;
    private Grade english;
}
