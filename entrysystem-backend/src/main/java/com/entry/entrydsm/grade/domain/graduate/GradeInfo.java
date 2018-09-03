package com.entry.entrydsm.grade.domain.graduate;

import javax.persistence.*;

@Entity
public class GradeInfo {

    @EmbeddedId
    private GradeInfoId infoId;

    @Column(nullable = false)
    private Boolean isPass = true;

    @Enumerated(EnumType.STRING)
    private Score score = null;

}
