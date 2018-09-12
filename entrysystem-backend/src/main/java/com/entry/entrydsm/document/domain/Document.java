package com.entry.entrydsm.document.domain;

import com.entry.entrydsm.common.domain.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Id;

public class Document extends BaseTimeEntity {
    @Id
    @Column(length = 32)
    @JsonIgnore
    private String userId;

    @Column(length = 1600, nullable = false)
    private String introduce;

    @Column(length = 1600, nullable = false)
    private String studyPlan;
}
