package com.entry.entrydsm.grade.domain.ged;

import com.entry.entrydsm.grade.domain.Score;
import com.entry.entrydsm.grade.dto.GradeDTO;
import com.entry.entrydsm.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@NoArgsConstructor
@Getter
public class GedScore extends Score {
    @Column(nullable = false)
    @Max(100)
    @Min(0)
    private Double grade;

    public GedScore(User user) {
        super(user, 15);
        this.grade = 0.0;
    }

    public void update(GradeDTO dto) {
        this.grade = dto.getGrade();
    }
}
