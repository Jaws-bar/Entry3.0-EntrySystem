package com.entry.entrydsm.info.dto;

import com.entry.entrydsm.info.domain.Admission;
import com.entry.entrydsm.info.domain.AdmissionDetail;
import com.entry.entrydsm.user.domain.AdditionalType;
import com.entry.entrydsm.user.domain.GraduateType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ClassificationDTO {
    @NotNull
    private GraduateType graduateType;

    @NotNull
    private Boolean region;

    @NotNull
    private Admission admission;

    @NotNull
    private AdmissionDetail admissionDetail;

    @NotNull
    private AdditionalType additionalType;

    private Integer graduateYear;

    @AssertTrue
    private boolean isValidGraduateYear() {
        if ((graduateType == GraduateType.GED) && graduateYear != null) {
            return false;
        }

        if (graduateType != GraduateType.GED && graduateYear == null) {
            return false;
        }

        if (graduateType == GraduateType.WILL && graduateYear != 2019) {
            return false;
        }

        if ((graduateType == GraduateType.DONE) && !(2019 > graduateYear && graduateYear >= 2009)) {
            return false;
        }

        return true;
    }

    @AssertTrue
    private boolean isValidAdmissionDetail() {
        if (admission != Admission.SOCIAL && admissionDetail != AdmissionDetail.NONE) {
            return false;
        }

        return true;
    }

    @Builder
    public ClassificationDTO(@NotNull GraduateType graduateType, @NotNull Boolean region, @NotNull Admission admission, @NotNull AdmissionDetail admissionDetail, @NotNull AdditionalType additionalType, Integer graduateYear) {
        this.graduateType = graduateType;
        this.region = region;
        this.admission = admission;
        this.admissionDetail = admissionDetail;
        this.additionalType = additionalType;
        this.graduateYear = graduateYear;
    }
}
