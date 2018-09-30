package com.entry.entrydsm.info.controller;

import com.entry.entrydsm.common.response.RestResponse;
import com.entry.entrydsm.info.domain.Admission;
import com.entry.entrydsm.info.domain.AdmissionDetail;
import com.entry.entrydsm.info.dto.ClassificationDTO;
import com.entry.entrydsm.info.dto.ClassificationResponse;
import com.entry.entrydsm.support.AcceptanceTest;
import com.entry.entrydsm.user.domain.AdditionalType;
import com.entry.entrydsm.user.domain.GraduateType;
import com.entry.entrydsm.user.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class ClassificationControllerTest extends AcceptanceTest {

    private static final String CLASSIFICATION_URL = "/api/me/classification";
    private static final int WILL_GRADUATE_YEAR = 2019;
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
    public void 조회() throws Exception {
        ResponseEntity<RestResponse<ClassificationResponse>> response = getRequestWithAuth(CLASSIFICATION_URL, classificationTypeRef());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getData()).isEqualToComparingOnlyGivenFields(defaultUser(),
                "graduateType", "admission", "admissionDetail", "additionalType", "region");
    }

    @Test
    public void 조회_비로그인() {
        ResponseEntity<RestResponse<ClassificationResponse>> response = getRequest(CLASSIFICATION_URL, classificationTypeRef());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void 수정() throws Exception {
        ResponseEntity<RestResponse<ClassificationResponse>> response = putRequestWithAuth(CLASSIFICATION_URL, dto, classificationTypeRef());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertClassification(response.getBody().getData(), dto, defaultUser());
    }

    @Test
    public void 수정_검정고시_졸업년도_있음() throws Exception {
        dto.setGraduateType(GraduateType.GED);
        ResponseEntity<RestResponse<ClassificationResponse>> response = putRequestWithAuth(CLASSIFICATION_URL, dto, classificationTypeRef());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void 수정_검정고시_아님_졸업년도_없음() throws Exception {
        dto.setGraduateYear(null);
        ResponseEntity<RestResponse<ClassificationResponse>> response = putRequestWithAuth(CLASSIFICATION_URL, dto, classificationTypeRef());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void 수정_사회통합_아님_세부_있음() throws Exception {
        dto.setAdmissionDetail(AdmissionDetail.BENEFICIARY);
        ResponseEntity<RestResponse<ClassificationResponse>> response = putRequestWithAuth(CLASSIFICATION_URL, dto, classificationTypeRef());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void 수정_졸업예정_유효하지_않은_졸업년도() throws Exception {
        dto.setGraduateYear(WILL_GRADUATE_YEAR + 1);
        ResponseEntity<RestResponse<ClassificationResponse>> response = putRequestWithAuth(CLASSIFICATION_URL, dto, classificationTypeRef());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void 수정_졸업_유효하지_않은_졸업년도_높음() throws Exception {
        dto.setGraduateType(GraduateType.DONE);
        dto.setGraduateYear(WILL_GRADUATE_YEAR);
        ResponseEntity<RestResponse<ClassificationResponse>> response = putRequestWithAuth(CLASSIFICATION_URL, dto, classificationTypeRef());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void 수정_졸업_유효하지_않은_졸업년도_낮음() throws Exception {
        dto.setGraduateType(GraduateType.DONE);
        dto.setGraduateYear(DONE_MINIMUM_GRADUATE_YEAR - 1);
        ResponseEntity<RestResponse<ClassificationResponse>> response = putRequestWithAuth(CLASSIFICATION_URL, dto, classificationTypeRef());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void 수정_비로그인() {
        ResponseEntity<RestResponse<ClassificationResponse>> response = putRequest(CLASSIFICATION_URL, dto, classificationTypeRef());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    private void assertClassification(ClassificationResponse response, ClassificationDTO dto, User user) {
        assertThat(response).isEqualToComparingOnlyGivenFields(dto,
                "graduateType", "admission", "admissionDetail", "additionalType", "region");
        assertThat(user).isEqualToComparingOnlyGivenFields(dto,
                "graduateType", "admission", "admissionDetail", "additionalType", "region");
    }

    private ParameterizedTypeReference<RestResponse<ClassificationResponse>> classificationTypeRef() {
        return new ParameterizedTypeReference<RestResponse<ClassificationResponse>>() {
        };
    }
}