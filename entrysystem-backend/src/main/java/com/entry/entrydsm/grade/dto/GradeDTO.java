package com.entry.entrydsm.grade.dto;

import com.entry.entrydsm.common.validate.Ged;
import com.entry.entrydsm.common.validate.Graduate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class GradeDTO {
    @NotNull(groups = Ged.class)
    @Size(max = 100, groups = Ged.class)
    private Double grade;

    @NotNull(groups = Graduate.class)
    @Size(min = 5, max = 5, groups = Graduate.Will.class)
    @Size(min = 6, max = 6, groups = Graduate.Done.class)
    private List<GraduateGradeDTO> grades;

    @NotNull(groups = Graduate.class)
    @Min(value = 0, groups = Graduate.class)
    private Integer volunteerTime;

    @NotNull(groups = Graduate.class)
    @Min(value = 0, groups = Graduate.class)
    private Integer periodCut;

    @NotNull(groups = Graduate.class)
    @Min(value = 0, groups = Graduate.class)
    private Integer fullCut;

    @NotNull(groups = Graduate.class)
    @Min(value = 0, groups = Graduate.class)
    private Integer late;

    @NotNull(groups = Graduate.class)
    @Min(value = 0, groups = Graduate.class)
    private Integer earlyLeave;
}
