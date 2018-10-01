package com.entry.entrydsm.school.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
public class School {
    @Id
    private String code;

    @Column(nullable = false)
    private String government;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String fullName;

    @Builder
    public School(String code, String government, String name, String fullName) {
        this.code = code;
        this.government = government;
        this.name = name;
        this.fullName = fullName;
    }
}
