package com.entry.entrydsm.info.domain.graduate;

import com.entry.entrydsm.user.domain.User;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class GraduateInfo {
    @Id
    @Column(length = 32)
    private String userId;

    @Column(length = 4, nullable = false)
    private Integer graduateYear;

    @Column(length = 32)
    private String schoolCode;

    @Column(length = 50, nullable = false)
    private String schoolName = "";

    @Column(length = 1, nullable = false)
    private Integer studentGrade = 3;

    @Column(length = 2)
    private Integer studentClass;

    @Column(length = 2)
    private Integer studentNumber;

    public GraduateInfo(User user) {
        this(user, 2019, null, "", 3, null, null);
    }

    @Builder
    public GraduateInfo(User user, Integer graduateYear, String schoolCode, String schoolName, Integer studentGrade, Integer studentClass, Integer studentNumber) {
        this.userId = user.getId();
        this.graduateYear = graduateYear;
        this.schoolCode = schoolCode;
        this.schoolName = schoolName;
        this.studentGrade = studentGrade;
        this.studentClass = studentClass;
        this.studentNumber = studentNumber;
    }
}
