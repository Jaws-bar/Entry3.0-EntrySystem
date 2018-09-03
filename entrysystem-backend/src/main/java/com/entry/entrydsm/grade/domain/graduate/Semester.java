package com.entry.entrydsm.grade.domain.graduate;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Semester {
    @Column(nullable = false)
    private Integer semester;

    public Semester() {
    }

    public Semester(Integer semester) {
        if (semester < 1 || semester > 6) throw new IllegalArgumentException();
        this.semester = semester;
    }
}
