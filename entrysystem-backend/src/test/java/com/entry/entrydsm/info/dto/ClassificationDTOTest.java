package com.entry.entrydsm.info.dto;

import com.entry.entrydsm.info.domain.Admission;
import com.entry.entrydsm.info.domain.AdmissionDetail;
import com.entry.entrydsm.support.ValidationTest;
import com.entry.entrydsm.user.domain.AdditionalType;
import com.entry.entrydsm.user.domain.GraduateType;
import org.junit.Before;
import org.junit.Test;

public class ClassificationDTOTest extends ValidationTest {

    private static final int WILL_GRADUATE_YEAR = 2019;
    private static final int DONE_MAXIMUM_GRADUATE_YEAR = 2018;
    private static final int DONE_MINIMUM_GRADUATE_YEAR = 2009;

    private ClassificationDTO dto;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        dto = ClassificationDTO.builder()
                .graduateType(GraduateType.WILL)
                .graduateYear(WILL_GRADUATE_YEAR)
                .admission(Admission.NORMAL)
                .admissionDetail(AdmissionDetail.NONE)
                .additionalType(AdditionalType.NONE)
                .region(false)
                .build();
    }

    @Test
    public void 유효_졸업예정() {
        assertConstraintViolations(dto, 0);
    }

    @Test
    public void 졸업예정_유효하지_않은_졸업년도() {
        for (int i = WILL_GRADUATE_YEAR - 10; i < WILL_GRADUATE_YEAR + 10; i++) {
            dto.setGraduateYear(i);
            if (i == WILL_GRADUATE_YEAR) {
                assertConstraintViolations(dto, 0);
                continue;
            }
            assertConstraintViolations(dto, 1);
        }
    }

    @Test
    public void 사회통합_아님_세부_있음() {
        for (AdmissionDetail detail : AdmissionDetail.values()) {
            if (detail == AdmissionDetail.NONE) {
                continue;
            }
            dto.setAdmissionDetail(detail);
            assertConstraintViolations(dto, 1);
        }
    }

    @Test
    public void 검정고시_아님_졸업년도_없음() {
        dto.setGraduateYear(null);
        assertConstraintViolations(dto, 1);
        dto.setGraduateType(GraduateType.DONE);
        assertConstraintViolations(dto, 1);
    }

    @Test
    public void 검정고시_졸업년도_있음() {
        dto.setGraduateType(GraduateType.GED);
        assertConstraintViolations(dto, 1);
    }

    @Test
    public void 유효_졸업() {
        dto.setGraduateType(GraduateType.DONE);
        dto.setGraduateYear(DONE_MAXIMUM_GRADUATE_YEAR);
        assertConstraintViolations(dto, 0);
        dto.setGraduateYear(DONE_MINIMUM_GRADUATE_YEAR);
        assertConstraintViolations(dto, 0);
    }

    @Test
    public void 졸업_유효하지_않은_졸업년도() {
        dto.setGraduateType(GraduateType.DONE);
        for (int i = DONE_MINIMUM_GRADUATE_YEAR - 1; i <= DONE_MAXIMUM_GRADUATE_YEAR + 1; i++) {
            dto.setGraduateYear(i);
            if (DONE_MINIMUM_GRADUATE_YEAR <= i && i <= DONE_MAXIMUM_GRADUATE_YEAR) {
                assertConstraintViolations(dto, 0);
                continue;
            }
            assertConstraintViolations(dto, 1);
        }
    }

    @Test
    public void 유효_검정고시() {
        dto.setGraduateType(GraduateType.GED);
        dto.setGraduateYear(null);
        assertConstraintViolations(dto, 0);
    }
}