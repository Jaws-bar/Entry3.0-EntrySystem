package com.entry.entrydsm.info.domain.graduate;

import com.entry.entrydsm.common.domain.BaseTimeEntity;
import com.entry.entrydsm.info.dto.ClassificationDTO;
import com.entry.entrydsm.info.dto.InfoDTO;
import com.entry.entrydsm.school.domain.School;
import com.entry.entrydsm.user.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class GraduateInfo extends BaseTimeEntity {
    @Id
    @Column(length = 32)
    @JsonIgnore
    private String userId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @Column(length = 4, nullable = false)
    private Integer graduateYear;

    @ManyToOne
    @JoinColumn(name = "school_code")
    private School school;

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
    public GraduateInfo(User user, Integer graduateYear, School school, String schoolTel, Integer studentGrade, Integer studentClass, Integer studentNumber) {
        this.user = user;
        this.graduateYear = graduateYear;
        this.school = school;
        this.schoolTel = schoolTel;
        this.studentGrade = studentGrade;
        this.studentClass = studentClass;
        this.studentNumber = studentNumber;
    }

    public void updateClassification(ClassificationDTO classificationDTO) {
        this.graduateYear = classificationDTO.getGraduateYear();
    }

    public void update(InfoDTO infoDTO, School school) {
        this.school = school;
        this.schoolTel = infoDTO.getSchoolTel();
        this.studentClass = infoDTO.getStudentClass();
        this.studentNumber = infoDTO.getStudentNumber();
    }
}
