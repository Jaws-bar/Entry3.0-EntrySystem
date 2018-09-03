package com.entry.entrydsm.school.domain;

import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class School {
    @Id
    private String code;

    @Column(nullable = false)
    private String goverment;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String schoolRegion;

    @Builder
    public School(String code, String goverment, String name, String schoolRegion) {
        this.code = code;
        this.goverment = goverment;
        this.name = name;
        this.schoolRegion = schoolRegion;
    }
}
