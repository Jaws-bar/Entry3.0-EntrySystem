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
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@NoArgsConstructor
public class GraduateInfo extends BaseTimeEntity {
    private static final String SCHOOL_NUMBER_PATTERN = "^(\\d{10,11})?$";

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
    @NotNull(message = "학교 정보가 입력되지 않았습니다.")
    private School school;

    @Pattern(regexp = SCHOOL_NUMBER_PATTERN,
            message = "학교 연락처를 제대로 입력해주세요. (숫자 10~11자)")
    @Column(length = 15, nullable = false)
    private String schoolTel;

    @Column(length = 1, nullable = false, updatable = false)
    private Integer studentGrade;

    @Column(length = 2, nullable = false)
    @Min(value = 1, message = "반을 제대로 입력해주세요.")
    private Integer studentClass;

    @Column(length = 2, nullable = false)
    @Min(value = 1, message = "번호를 제대로 입력해주세요.")
    private Integer studentNumber;

    public GraduateInfo(User user) {
        this(user, 2019, null, "", 0, 0);
    }

    @Builder
    public GraduateInfo(User user, Integer graduateYear, School school, String schoolTel, Integer studentClass, Integer studentNumber) {
        this.user = user;
        this.graduateYear = graduateYear;
        this.school = school;
        this.schoolTel = schoolTel;
        this.studentGrade = 3;
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


    @JsonIgnore
    @AssertTrue(message = "관내 지원자는 관내 학교를, 관외 지원자는 관외 학교를 선택해주세요.")
    public boolean isValidSchoolForRegion() {
        if (school == null) {
            return true;
        }

        if (user.getRegion()) {
            return school.getGovernment().startsWith("대전");
        }

        return !school.getGovernment().startsWith("대전");
    }

}
