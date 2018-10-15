package com.entry.entrydsm.grade.domain.ged;

import com.entry.entrydsm.grade.domain.Score;
import com.entry.entrydsm.grade.dto.GradeDTO;
import com.entry.entrydsm.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
public class GedScore extends Score {
    @Column(nullable = false)
    @Range(min = 60, max = 100, message = "검정고시 평균 점수는 60 ~ 100점 사이어야 합니다.")
    private Double grade;

    public GedScore(User user) {
        super(user, 15);
        this.grade = 0.0;
    }

    public void update(GradeDTO dto) {
        this.grade = dto.getGrade();
    }
}
