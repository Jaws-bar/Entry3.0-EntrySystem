package com.entry.entrydsm.grade.domain.ged;

import com.entry.entrydsm.grade.domain.Grade;
import com.entry.entrydsm.user.domain.User;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class GedGrade extends Grade {
    @Column(nullable = false)
    private Double grade = 0.0;

    public GedGrade(User user) {
        super(user.getId());
    }
}
