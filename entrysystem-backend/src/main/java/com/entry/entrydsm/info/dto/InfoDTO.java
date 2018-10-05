package com.entry.entrydsm.info.dto;

import com.entry.entrydsm.common.validate.Ged;
import com.entry.entrydsm.common.validate.Graduate;
import com.entry.entrydsm.info.domain.Sex;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class InfoDTO {

    private static final String PHONE_NUMBER_PATTERN = "^(\\d{11})?$";
    private static final String SCHOOL_NUMBER_PATTERN = "^(\\d{10,11})?$";
    private static final String ZIP_CODE_PATTERN = "^(\\d{5})?$";

    @NotNull(groups = {Graduate.class, Ged.class})
    @Size(max = 20, groups = {Graduate.class, Ged.class})
    private String name;

    private Sex sex;

    @NotNull(groups = {Graduate.class, Ged.class})
    private LocalDate birth;

    @NotNull(groups = {Graduate.class})
    @Min(value = 0, groups = {Graduate.class})
    private Integer studentClass;

    @NotNull(groups = {Graduate.class})
    @Min(value = 0, groups = {Graduate.class})
    private Integer studentNumber;

    private String schoolCode;

    @NotNull(groups = {Graduate.class})
    @Pattern(regexp = SCHOOL_NUMBER_PATTERN, groups = {Graduate.class})
    private String schoolTel;

    @NotNull(groups = {Graduate.class, Ged.class})
    @Size(max = 20, groups = {Graduate.class, Ged.class})
    private String parentName;

    @NotNull(groups = {Graduate.class, Ged.class})
    @Pattern(regexp = PHONE_NUMBER_PATTERN, groups = {Graduate.class, Ged.class})
    private String parentTel;

    @NotNull(groups = {Graduate.class, Ged.class})
    @Pattern(regexp = PHONE_NUMBER_PATTERN, groups = {Graduate.class, Ged.class})
    private String myTel;

    @NotNull(groups = {Graduate.class, Ged.class})
    @Pattern(regexp = ZIP_CODE_PATTERN, groups = {Graduate.class, Ged.class})
    private String zipCode;

    @NotNull(groups = {Graduate.class, Ged.class})
    @Size(max = 100, groups = {Graduate.class, Ged.class})
    private String addressBase;

    @NotNull(groups = {Graduate.class, Ged.class})
    @Size(max = 50, groups = {Graduate.class, Ged.class})
    private String addressDetail;

    @Builder
    public InfoDTO(String name, Sex sex, LocalDate birth, Integer studentClass, Integer studentNumber, String schoolCode, String schoolTel, String parentName, String parentTel, String myTel, String zipCode, String addressBase, String addressDetail) {
        this.name = name;
        this.sex = sex;
        this.birth = birth;
        this.studentClass = studentClass;
        this.studentNumber = studentNumber;
        this.schoolCode = schoolCode;
        this.schoolTel = schoolTel;
        this.parentName = parentName;
        this.parentTel = parentTel;
        this.myTel = myTel;
        this.zipCode = zipCode;
        this.addressBase = addressBase;
        this.addressDetail = addressDetail;
    }
}
