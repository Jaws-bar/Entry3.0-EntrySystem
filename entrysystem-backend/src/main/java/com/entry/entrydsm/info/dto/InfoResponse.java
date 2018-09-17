package com.entry.entrydsm.info.dto;

import com.entry.entrydsm.common.response.WrappedResponse;
import com.entry.entrydsm.info.domain.Info;
import com.entry.entrydsm.info.domain.graduate.GraduateInfo;
import com.entry.entrydsm.user.domain.GraduateType;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;

@Getter
public class InfoResponse extends WrappedResponse {
    @JsonUnwrapped
    private GraduateInfo graduateInfo;

    @JsonUnwrapped
    private Info info;

    public InfoResponse(GraduateType graduateType, Info info) {
        super(graduateType);
        this.info = info;
    }

    public InfoResponse(GraduateType graduateType, Info info, GraduateInfo graduateInfo) {
        this(graduateType, info);
        this.graduateInfo = graduateInfo;
    }
}
