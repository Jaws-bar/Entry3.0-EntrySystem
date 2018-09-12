package com.entry.entrydsm.info.dto;

import com.entry.entrydsm.info.domain.Admission;
import com.entry.entrydsm.info.domain.AdmissionDetail;
import com.entry.entrydsm.info.domain.graduate.GraduateInfo;
import com.entry.entrydsm.user.domain.AdditionalType;
import com.entry.entrydsm.user.domain.GraduateType;
import com.entry.entrydsm.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ClassificationResponse {
    private GraduateType graduateType;
    private Admission admission;
    private AdmissionDetail admissionDetail;
    private boolean region;
    private Integer graduateYear;
    private AdditionalType additionalType;

    @Builder
    protected ClassificationResponse(GraduateType graduateType, Admission admission, AdmissionDetail admissionDetail, boolean region, Integer graduateYear, AdditionalType additionalType) {
        this.graduateType = graduateType;
        this.admission = admission;
        this.admissionDetail = admissionDetail;
        this.region = region;
        this.graduateYear = graduateYear;
        this.additionalType = additionalType;
    }

    public static ClassificationResponse of(User user, GraduateInfo graduateInfo) {
        return preBuild(user)
                .graduateYear(graduateInfo.getGraduateYear())
                .build();
    }

    public static ClassificationResponse of(User user) {
        return preBuild(user)
                .build();
    }

    private static ClassificationResponseBuilder preBuild(User user) {
        return ClassificationResponse.builder()
                .graduateType(user.getGraduateType())
                .admission(user.getAdmission())
                .admissionDetail(user.getAdmissionDetail())
                .region(user.getRegion())
                .additionalType(user.getAdditionalType());
    }
}
