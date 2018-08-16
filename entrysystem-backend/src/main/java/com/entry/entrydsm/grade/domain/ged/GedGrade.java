package com.entry.entrydsm.grade.domain.ged;

import com.entry.entrydsm.grade.domain.Grade;
import com.entry.entrydsm.user.User;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class GedGrade extends Grade {
    @Column(nullable = false)
    private Double grade;

    public GedGrade(User user) {
        super(user.getId());
    }
}
