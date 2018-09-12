package com.entry.entrydsm.common.response;

import com.entry.entrydsm.user.domain.GraduateType;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;

@Getter
public class WrappedResponse<T> {
    private GraduateType graduateType;
    @JsonUnwrapped
    private T data;

    public WrappedResponse(GraduateType graduateType, T data) {
        this.graduateType = graduateType;
        this.data = data;
    }
}
