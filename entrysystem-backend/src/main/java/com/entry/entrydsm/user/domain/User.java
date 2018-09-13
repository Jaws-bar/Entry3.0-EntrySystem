package com.entry.entrydsm.user.domain;

import com.entry.entrydsm.common.domain.BaseTimeEntity;
import com.entry.entrydsm.info.domain.Admission;
import com.entry.entrydsm.info.domain.AdmissionDetail;
import com.entry.entrydsm.info.domain.Info;
import com.entry.entrydsm.info.domain.graduate.GraduateInfo;
import com.entry.entrydsm.info.dto.ClassificationDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "user_id", length = 32)
    private String id;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Setter
    private GraduateType graduateType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Setter
    private Admission admission;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Setter
    private AdmissionDetail admissionDetail;

    @Column(nullable = false)
    @Setter
    private Boolean region;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Setter
    private AdditionalType additionalType;

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    private Info info;

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    private GraduateInfo graduateInfo;


    public User(TempUser tempUser) {
        this.email = tempUser.getEmail();
        this.password = tempUser.getPassword();
        this.graduateType = GraduateType.WILL;
        this.region = false;
        this.admission = Admission.NORMAL;
        this.admissionDetail = AdmissionDetail.NONE;
        this.additionalType = AdditionalType.NONE;
    }

    @Builder
    public User(String email, String password, GraduateType graduateType, Admission admission, AdmissionDetail admissionDetail, Boolean region, AdditionalType additionalType) {
        this.email = email;
        this.password = password;
        this.graduateType = (graduateType == null) ? GraduateType.WILL : graduateType;
        this.region = (region == null) ? false : region;
        this.admission = (admission == null) ? Admission.NORMAL : admission;
        this.admissionDetail = (this.admission == Admission.SOCIAL && admissionDetail != null) ? admissionDetail : AdmissionDetail.NONE;
        this.additionalType = (additionalType == null) ? AdditionalType.NONE : additionalType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", createdAt=" + getCreatedAt() +
                ", graduateType=" + graduateType +
                '}';
    }

    public boolean matchPassword(String password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password, this.password);
    }

    public void updateInfo(ClassificationDTO classificationDTO) {
        this.graduateType = classificationDTO.getGraduateType();
        this.region = classificationDTO.getRegion();
        this.admission = classificationDTO.getAdmission();
        this.admissionDetail = classificationDTO.getAdmissionDetail();
        if (classificationDTO.getGraduateType() != GraduateType.GED) {
            this.graduateInfo.updateClassification(classificationDTO);
        }
    }

    public void initialize() {
        this.info = new Info(this);
        this.graduateInfo = new GraduateInfo(this);
    }
}
