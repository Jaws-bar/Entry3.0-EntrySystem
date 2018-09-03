package com.entry.entrydsm.grade.domain.graduate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

public class GradeInfoId implements Serializable {
    @Column(length = 32)
    private String userId;

    @Embedded
    private Semester semeter;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Subject subject;
}
