package com.entry.entrydsm.common.response;

import com.entry.entrydsm.user.domain.GraduateType;
import lombok.Getter;

@Getter
public class WrappedResponse {
    private GraduateType graduateType;

    public WrappedResponse(GraduateType graduateType) {
        this.graduateType = graduateType;
    }
}
