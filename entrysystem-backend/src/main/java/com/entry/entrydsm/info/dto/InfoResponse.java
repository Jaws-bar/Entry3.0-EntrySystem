package com.entry.entrydsm.info.dto;

import com.entry.entrydsm.common.response.WrappedResponse;
import com.entry.entrydsm.info.domain.Info;
import com.entry.entrydsm.info.domain.graduate.GraduateInfo;
import com.entry.entrydsm.user.domain.GraduateType;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;

@Getter
public class InfoResponse extends WrappedResponse<Info> {
    @JsonUnwrapped
    private GraduateInfo graduateInfo;

    public InfoResponse(GraduateType graduateType, Info info) {
        super(graduateType, info);
    }

    public InfoResponse(GraduateType graduateType, Info info, GraduateInfo graduateInfo) {
        super(graduateType, info);
        this.graduateInfo = graduateInfo;
    }
}
