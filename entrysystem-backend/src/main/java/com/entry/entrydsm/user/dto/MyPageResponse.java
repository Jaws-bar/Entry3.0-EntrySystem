package com.entry.entrydsm.user.dto;

import com.entry.entrydsm.apply.domain.ApplyStatus;
import com.entry.entrydsm.apply.dto.ValidationResult;
import com.entry.entrydsm.common.response.WrappedResponse;
import com.entry.entrydsm.user.domain.GraduateType;
import lombok.Getter;

@Getter
public class MyPageResponse extends WrappedResponse {

    private final ValidationResult validationResult;
    private final ApplyStatus applyStatus;

    public MyPageResponse(GraduateType graduateType, ValidationResult validationResult, ApplyStatus applyStatus) {
        super(graduateType);
        this.validationResult = validationResult;
        this.applyStatus = applyStatus;
    }
}
