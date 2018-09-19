package com.entry.entrydsm.document.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
public class DocumentDTO {

    @NotNull
    @Length(max = 1600)
    private String introduce;

    @NotNull
    @Length(max = 1600)
    private String studyPlan;
}
