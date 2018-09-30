package com.entry.entrydsm.info.service;

import com.entry.entrydsm.info.domain.Admission;
import com.entry.entrydsm.info.domain.AdmissionDetail;
import com.entry.entrydsm.info.dto.ClassificationDTO;
import com.entry.entrydsm.info.dto.ClassificationResponse;
import com.entry.entrydsm.user.domain.AdditionalType;
import com.entry.entrydsm.user.domain.GraduateType;
import com.entry.entrydsm.user.domain.User;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ClassificationServiceTest {

    private static final int WILL_GRADUATE_YEAR = 2019;
    private ClassificationDTO dto;
    private ClassificationService classificationService;
    private User user;

    @Before
    public void setUp() throws Exception {
        classificationService = new ClassificationService();
        dto = ClassificationDTO.builder()
                .graduateType(GraduateType.WILL)
                .graduateYear(WILL_GRADUATE_YEAR)
                .admission(Admission.NORMAL)
                .admissionDetail(AdmissionDetail.NONE)
                .additionalType(AdditionalType.NONE)
                .region(false)
                .build();

        user = User.builder().build();
    }

    @Test
    public void 조회() {
        ClassificationResponse response = classificationService.getInfo(user);
        assertClassification(response, user);
    }

    @Test
    public void 수정_검정고시_아님() {
        User spyUser = spy(user);
        ClassificationResponse response = classificationService.putInfo(spyUser, dto);
        assertClassification(response, dto, spyUser);
        assertThat(spyUser.getGraduateInfo().getGraduateYear()).isEqualTo(dto.getGraduateYear());
        verify(spyUser, times(1)).updateGraduateClassification(dto);
    }

    @Test
    public void 수정_검정고시() {
        dto.setGraduateType(GraduateType.GED);
        User spyUser = spy(user);
        ClassificationResponse response = classificationService.putInfo(spyUser, dto);
        assertClassification(response, dto, spyUser);
        assertThat(spyUser.getGraduateInfo().getGraduateYear()).isEqualTo(dto.getGraduateYear());
        verify(spyUser, times(0)).updateGraduateClassification(dto);
    }

    private void assertClassification(ClassificationResponse response, ClassificationDTO dto, User user) {
        assertThat(response).isEqualToComparingOnlyGivenFields(dto,
                "graduateType", "admission", "admissionDetail", "additionalType", "region");
        assertThat(user).isEqualToComparingOnlyGivenFields(dto,
                "graduateType", "admission", "admissionDetail", "additionalType", "region");
    }

    private void assertClassification(ClassificationResponse response, User user) {
        assertThat(response).isEqualToComparingOnlyGivenFields(user,
                "graduateType", "admission", "admissionDetail", "additionalType", "region");
    }
}