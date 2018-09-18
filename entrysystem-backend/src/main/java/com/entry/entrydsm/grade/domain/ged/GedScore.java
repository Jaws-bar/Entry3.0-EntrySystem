package com.entry.entrydsm.grade.domain.ged;

import com.entry.entrydsm.grade.domain.Score;
import com.entry.entrydsm.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
public class GedScore extends Score {
    @Column(nullable = false)
    private Double grade;

    public GedScore(User user) {
        super(user, 15);
        grade = 0.0;
    }
}
