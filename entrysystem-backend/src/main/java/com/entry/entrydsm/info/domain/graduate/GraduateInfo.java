package com.entry.entrydsm.info.domain.graduate;

import com.entry.entrydsm.common.domain.BaseTimeEntity;
import com.entry.entrydsm.info.dto.ClassificationDTO;
import com.entry.entrydsm.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class GraduateInfo extends BaseTimeEntity {
    @Id
    @Column(length = 32)
    private String userId;

    @Column(length = 4, nullable = false)
    private Integer graduateYear;

    @Column(length = 32)
    private String schoolCode;

    @Column(length = 15, nullable = false)
    private String schoolTel;

    @Column(length = 1, nullable = false)
    private Integer studentGrade = 3;

    @Column(length = 2, nullable = false)
    private Integer studentClass;

    @Column(length = 2, nullable = false)
    private Integer studentNumber;

    public GraduateInfo(User user) {
        this(user, 2019, null, "", 3, 0, 0);
    }

    @Builder
    public GraduateInfo(User user, Integer graduateYear, String schoolCode, String schoolTel, Integer studentGrade, Integer studentClass, Integer studentNumber) {
        this.userId = user.getId();
        this.graduateYear = graduateYear;
        this.schoolCode = schoolCode;
        this.schoolTel = schoolTel;
        this.studentGrade = studentGrade;
        this.studentClass = studentClass;
        this.studentNumber = studentNumber;
    }

    public void updateClassification(ClassificationDTO classificationDTO) {
        this.graduateYear = classificationDTO.getGraduateYear();
    }
}
